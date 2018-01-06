package by.epam.project.hostel.dao.impl;

import by.epam.project.hostel.dao.GuestroomDAO;
import by.epam.project.hostel.dao.db.connection.ConnectionProvider;
import by.epam.project.hostel.dao.exception.ConnectionPoolException;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Guestroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.project.hostel.controller.pagination.PageWrapper.MAX_ENTRIES_PER_PAGE;

public class GuestroomDAOImpl extends EntityDAOImpl implements GuestroomDAO {

    private static final ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
    private static final String SELECT_ROOM_BY_ID = "SELECT guestrooms.id,  night_price,  tv,  wifi,  bath,  capacity,  description, hostel_id FROM guestrooms  INNER JOIN tguestrooms t ON guestrooms.id = t.guestrooms_id   INNER JOIN language l ON t.language_id = l.id WHERE guestrooms.id = ? AND language = ?";
    private static final String SELECT_ROOMS_BY_HOSTEL_ID_LIMIT = "SELECT  guestrooms.id,  night_price,  tv,  wifi,  bath,  capacity,  description ,hostel_id FROM guestrooms INNER JOIN tguestrooms t ON guestrooms.id = t.guestrooms_id INNER JOIN language l ON t.language_id = l.id WHERE l.language=? AND hostel_id=? LIMIT ?,?";
    private static final String SELECT_IMAGES_PATH = "SELECT file FROM picture WHERE guestrooms_id = ?";

    @Override
    public List<Guestroom> getGuestroomsByHostelId(int id, String language, int page) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ROOMS_BY_HOSTEL_ID_LIMIT)) {
            ps.setString(1, language);
            ps.setInt(2, id);
            ps.setInt(3, page);
            ps.setInt(4, MAX_ENTRIES_PER_PAGE);
            try (ResultSet rs = ps.executeQuery()) {
                List<Guestroom> guestrooms = new ArrayList<>();
                while (rs.next()) {
                    guestrooms.add(createGuestroom(rs));
                }
                for (Guestroom guestroom : guestrooms) {
                    guestroom.setImgPath(getGuestroomImage(guestroom.getId(), connection));
                }
                return guestrooms;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during getting guestrooms by hostel id = " + id + ", page = " + page, e);
        }
    }

    @Override
    public Guestroom getGuestRoomById(int id, String language) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ROOM_BY_ID)) {
            ps.setInt(1, id);
            ps.setString(2, language);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                Guestroom guestroom = createGuestroom(rs);
                guestroom.setImgPath(getGuestroomImage(id, connection));
                return guestroom;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during getting guestrooms by id = " + id, e);
        }
    }

    @Override
    protected String getTableName() {
        return "guestrooms";
    }

    private int getNumberOfPages(List<Guestroom> guestrooms) {
        int numberOfPages = guestrooms.size() / MAX_ENTRIES_PER_PAGE;
        if (guestrooms.size() % MAX_ENTRIES_PER_PAGE == 0) {
            return numberOfPages;
        } else {
            return numberOfPages + 1;
        }
    }

    //todo this method remade in inner join
    private List<String> getGuestroomImage(int id, Connection connection) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_IMAGES_PATH)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                List<String> images = new ArrayList<>();
                while (rs.next()) {
                    images.add(rs.getString(1));
                }
                return images;
            }
        } catch (SQLException e) {
            throw new DAOException("error during getting guestroom images", e);
        }
    }

    private Guestroom createGuestroom(ResultSet rs) throws SQLException {
        Guestroom guestroom = new Guestroom();
        guestroom.setId(rs.getInt(1));
        guestroom.setNightPrice(rs.getDouble(2));
        guestroom.setTv(rs.getInt(3));
        guestroom.setWifi(rs.getInt(4));
        guestroom.setBath(rs.getInt(5));
        guestroom.setCapacity(rs.getInt(6));
        guestroom.setDescription(rs.getString(7));
        guestroom.setHostelId(rs.getInt(8));
        return guestroom;
    }

}
