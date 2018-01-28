package by.epam.project.hostel.dao.impl;

import by.epam.project.hostel.dao.db.connection.ConnectionProvider;
import by.epam.project.hostel.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseDAO implements by.epam.project.hostel.dao.EntityDAO {

    protected Connection connection;



    public int getTotalRowCount() throws DAOException {
        String selectCount = "SELECT COUNT(*) FROM " + getTableName();
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
        try (Connection connection = connectionProvider.takeConnection()) {
            Statement ps = connection.createStatement();
            ResultSet rs = ps.executeQuery(selectCount);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new DAOException("Error during getting total count from table " + getTableName(), e);
        }
    }

    protected abstract String getTableName();

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
