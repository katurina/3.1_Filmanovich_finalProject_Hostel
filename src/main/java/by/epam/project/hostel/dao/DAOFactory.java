package by.epam.project.hostel.dao;

import by.epam.project.hostel.dao.impl.UserDAOImpl;

public class DAOFactory {
    private final static DAOFactory instance = new DAOFactory();
    private UserDAO userDAO = new UserDAOImpl();

    static{

    }

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
