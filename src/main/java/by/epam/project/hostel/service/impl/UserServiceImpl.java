package by.epam.project.hostel.service.impl;

import by.epam.project.hostel.dao.DAOFactory;
import by.epam.project.hostel.dao.UserDAO;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.User;
import by.epam.project.hostel.service.UserService;
import by.epam.project.hostel.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

    private static final UserDAO USER_DAO = DAOFactory.getInstance().getUserDAO();

    @Override
    public User singIn(String login, String password) throws ServiceException {
        try {
            return USER_DAO.signIn(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void registerUser(String name, String surname, String login, String password, String email) throws ServiceException {
        try {
            USER_DAO.registration(name, surname, login, password, email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
