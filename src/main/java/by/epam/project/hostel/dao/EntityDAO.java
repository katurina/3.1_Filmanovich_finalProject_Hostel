package by.epam.project.hostel.dao;

import by.epam.project.hostel.dao.connetionDB.ConnectionProvider;
import by.epam.project.hostel.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityDAO {

    ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
    String SELECT_COUNT = "SELECT COUNT(*) FROM ?";

    default int getTotalRowCount(String tableName) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_COUNT);
            ps.setString(1, tableName);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new DAOException("Error during getting total count from table " + tableName, e);
        }
    }
}
