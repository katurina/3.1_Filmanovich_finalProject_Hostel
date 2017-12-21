package by.epam.project.hostel.service;

import by.epam.project.hostel.dao.db.connection.ConnectionProvider;
import by.epam.project.hostel.dao.exception.ConnectionPoolException;
import by.epam.project.hostel.service.exception.ServiceInitException;
import by.epam.project.hostel.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

    private UserService userService = new UserServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public void init() throws ServiceInitException {
        try {
            connectionProvider.initPoolData();
        } catch (ConnectionPoolException e) {
            throw new ServiceInitException("ConnectionPool not initialized: ", e);
        }
    }
}
