package by.epam.project.hostel.dao.impl;

import by.epam.project.hostel.dao.HostelDAO;
import by.epam.project.hostel.dao.db.connection.ConnectionProvider;
import by.epam.project.hostel.dao.exception.ConnectionPoolException;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Hostel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static by.epam.project.hostel.controller.constant.Constant.Language.EN;
import static by.epam.project.hostel.controller.constant.Constant.Language.RU;


public class HostelDAOImpl extends BaseDAO implements HostelDAO {
    private static final ConnectionProvider connectionProvider = ConnectionProvider.getInstance();


    private static final String SELECT_HOSTEL_BY_ID = "SELECT  stars,  imgPath,  name,  country,  city,  description,  address,  t.hostel_id FROM hostel  INNER JOIN thostel t ON hostel.id = t.hostel_id INNER JOIN language l ON t.language_id = l.id WHERE l.language = ? AND hostel.id = ?";
    private static final String SELECT_HOSTEL_BY_ROOM_ID = "SELECT  stars,  imgPath,  name,  country,  city,  description,  address,  t.hostel_id FROM hostel  INNER JOIN thostel t ON hostel.id = t.hostel_id INNER JOIN language l ON t.language_id = l.id  INNER JOIN guestrooms g ON hostel.id = g.hostel_id WHERE g.id = ? AND l.language = ?";
    private static final String SELECT_ALL_HOSTELS = "SELECT stars,  imgPath,  name,  country,  city,  description,  address,  t.hostel_id FROM hostel INNER JOIN thostel t ON hostel.id = t.hostel_id INNER JOIN language l ON t.language_id = l.id WHERE language=?";
    private static final String DELETE_HOSTEL_BY_ID = "DELETE FROM hostel WHERE id = ?";
    private static final String INSERT_THOSTEL = "INSERT INTO thostel(language_id, hostel_id, country, city, description, address) VALUES (?,?,?,?,?,?)";
    private static final String DELETE_EN_RU_HOSTEL_PART = "DELETE FROM thostel WHERE hostel_id = ?";
    private static final String INSERT_HOSTEL = "INSERT INTO hostel (stars, imgPath, name) VALUES (?,?,?)";

    @Override
    public Hostel getHostelById(int id, String language) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_HOSTEL_BY_ID)) {
            ps.setString(1, language);
            ps.setInt(2, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                return createHostel(rs);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during getting hostel by id", e);
        }
    }

    @Override
    public Hostel getHostelByRoomId(int id, String language) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_HOSTEL_BY_ROOM_ID)) {
            ps.setInt(1, id);
            ps.setString(2, language);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                return createHostel(rs);
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during get hostel by room id", e);
        }
    }

    @Override
    public List<Hostel> getHostels(String language) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_HOSTELS)) {
            ps.setString(1, language);
            try (ResultSet rs = ps.executeQuery()) {
                List<Hostel> hostels = new ArrayList<>();
                while (rs.next()) {
                    hostels.add(createHostel(rs));
                }
                return hostels;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during getting hostels", e);
        }
    }

    @Override
    public void deleteHostelById(Integer hostelId) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_HOSTEL_BY_ID)) {
            ps.setInt(1, hostelId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error during delete hostel by id ", e);
        }
    }

    @Override
    public int addHostelTransaction(Map<String, Hostel> hostel) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_HOSTEL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, hostel.get(RU).getStars());
            ps.setString(2, hostel.get(RU).getImgPath());
            ps.setString(3, hostel.get(RU).getName());
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new DAOException("cannot get id of inserting hostel");
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during inserting hostel", e);
        }
    }


    @Override
    public void addHostelLanguageParamsTransaction(Map<String, Hostel> hostel, int hostelId) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_THOSTEL)) {
            ps.setInt(1, 1);
            ps.setInt(2, hostelId);
            ps.setString(3, hostel.get(EN).getCountry());
            ps.setString(4, hostel.get(EN).getCity());
            ps.setString(5, hostel.get(EN).getDescription());
            ps.setString(6, hostel.get(EN).getAddress());
            ps.executeUpdate();
            ps.setInt(1, 2);
            ps.setInt(2, hostelId);
            ps.setString(3, hostel.get(RU).getCountry());
            ps.setString(4, hostel.get(RU).getCity());
            ps.setString(5, hostel.get(RU).getDescription());
            ps.setString(6, hostel.get(RU).getAddress());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error during adding hostel's params into thostel", e);
        }
    }

    @Override
    public void deleteHostelDescriptionTransaction(Integer hostelId) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_EN_RU_HOSTEL_PART)) {
            ps.setInt(1, hostelId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error during delete hostel descriptions", e);
        }
    }

    private Hostel createHostel(ResultSet rs) throws SQLException {
        Hostel hostel = new Hostel();
        hostel.setStars(rs.getInt(1));
        hostel.setImgPath(rs.getString(2));
        hostel.setName(rs.getString(3));
        hostel.setCountry(rs.getString(4));
        hostel.setCity(rs.getString(5));
        hostel.setDescription(rs.getString(6));
        hostel.setAddress(rs.getString(7));
        hostel.setId(rs.getInt(8));
        return hostel;
    }

    @Override
    protected String getTableName() {
        return "hostel";
    }
}
