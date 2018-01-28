package by.epam.project.hostel.dao.db.transaction;

import by.epam.project.hostel.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {

    private Connection connection;

    Transaction(Connection connection) {
        this.connection = connection;
    }

    public void commit() throws DAOException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException("error during commit transaction", e);
        }
    }

    public void rollback() throws DAOException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DAOException("error during rollback", e);
        }
    }

    public void endTransaction() throws DAOException {
        try {
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            throw new DAOException("error during end transaction", e);
        }
    }
}
