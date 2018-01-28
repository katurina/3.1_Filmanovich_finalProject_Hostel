package by.epam.project.hostel.dao.db.transaction;

import by.epam.project.hostel.dao.EntityDAO;
import by.epam.project.hostel.dao.db.connection.ConnectionProvider;
import by.epam.project.hostel.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    public Transaction beginTransaction(EntityDAO... daos) throws DAOException {
        Connection connection = ConnectionProvider.getInstance().takeConnection();
        try {
            connection.setAutoCommit(false);
            for (EntityDAO dao : daos) {
                dao.setConnection(connection);
            }
            return new Transaction(connection);
        } catch (SQLException e) {
            throw new DAOException("error during begin transaction", e);
        }
    }

}
