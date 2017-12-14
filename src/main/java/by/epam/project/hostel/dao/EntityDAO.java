package by.epam.project.hostel.dao;

import by.epam.project.hostel.dao.connetionDB.ConnectionProvider;
import by.epam.project.hostel.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface EntityDAO {

    ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
    String SELECT_COUNT = "SELECT COUNT(*) FROM ";

    default int getTotalRowCount(String tableName) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection()) {
            Statement ps = connection.createStatement();
            ResultSet rs = ps.executeQuery(SELECT_COUNT + tableName);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new DAOException("Error during getting total count from table " + tableName, e);
        }
    }
}