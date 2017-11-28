package by.epam.project.hostel.dao;

import by.epam.project.hostel.dao.exception.NoDBDriverFound;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private static final ConnectionProvider instance = new ConnectionProvider();

    private static final String DRIVER_DB = "com.mysql.jdbc.Driver";
    private static final String URL_DB = "jdbc:mysql://localhost:3306/hostel";
    private static final String USER_DB = "root";
    private static final String PASSWORD_DB = "root";

    private ConnectionProvider(){}

    public static ConnectionProvider getInstance() {
        return instance;
    }

    public void init() throws NoDBDriverFound {
        try {
            Class.forName(DRIVER_DB);
        } catch (ClassNotFoundException e) {
            throw new NoDBDriverFound("Driver not found: "+DRIVER_DB,e);
        }
    }


    public Connection takeConnection() throws SQLException {
        return DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
    }
}
