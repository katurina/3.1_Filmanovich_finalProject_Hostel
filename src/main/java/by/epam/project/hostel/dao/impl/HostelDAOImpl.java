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


public class HostelDAOImpl implements HostelDAO {
    private static final ConnectionProvider connectionProvider = ConnectionProvider.getInstance();


    private static final String SELECT_HOSTEL_BY_ID = "SELECT  stars,  imgPath,  name,  country,  city,  description,  address,  t.hostel_id FROM hostel  INNER JOIN thostel t ON hostel.id = t.hostel_id INNER JOIN language l ON t.language_id = l.id WHERE l.language = ? AND hostel.id = ?";

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
            throw new DAOException("error during get hostel by id", e);
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
}
