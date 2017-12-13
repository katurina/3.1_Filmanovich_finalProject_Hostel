package by.epam.project.hostel.service.impl;

import by.epam.project.hostel.dao.DAOFactory;
import by.epam.project.hostel.dao.UserDAO;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.User;
import by.epam.project.hostel.service.UserService;
import by.epam.project.hostel.service.exception.ServiceException;
import by.epam.project.hostel.service.exception.ValidationException;
import by.epam.project.hostel.service.validation.Validator;
import by.epam.project.hostel.service.validation.impl.ValidatorUserImpl;

public class UserServiceImpl implements UserService {

    private static final UserDAO USER_DAO = DAOFactory.getInstance().getUserDAO();
    private static final String ADMIN = "admin";
    private static final Validator<User> VALIDATOR = new ValidatorUserImpl();


    @Override
    public User singIn(String login, String password) throws ServiceException {
        try {
            VALIDATOR.validate(login, password);
            return USER_DAO.signIn(login, password);
        } catch (DAOException e) {
            throw new ValidationException("user validation failed", e);
        }
    }

    @Override
    public User adminSignIn(String login, String password) throws ServiceException {
        VALIDATOR.validate(login, password);
        try {
            User admin = USER_DAO.signIn(login, password);
            if (admin.getRole().equals(ADMIN)) {
                return admin;
            } else {
                return null;
            }
        } catch (DAOException e) {
            throw new ServiceException("admin's signing in failed", e);
        }
    }

    @Override
    public void registerUser(String name, String surname, String login, String password, String email, String number) throws ServiceException {
        try {
            VALIDATOR.validate(name, surname, login, password, email, number);
            USER_DAO.registration(name, surname, login, password, email, number);
        } catch (DAOException e) {
            throw new ServiceException("registration failed", e);
        }
    }


}
