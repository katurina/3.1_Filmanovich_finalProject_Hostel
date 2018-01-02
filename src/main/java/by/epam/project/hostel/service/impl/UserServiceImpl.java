package by.epam.project.hostel.service.impl;

import by.epam.project.hostel.dao.DAOFactory;
import by.epam.project.hostel.dao.UserDAO;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.User;
import by.epam.project.hostel.service.UserService;
import by.epam.project.hostel.service.exception.ServiceException;
import by.epam.project.hostel.service.exception.ValidationException;
import by.epam.project.hostel.service.validation.Validator;
import by.epam.project.hostel.service.validation.impl.UserValidatorImpl;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
    private static final Validator<User> validator = new UserValidatorImpl();


    @Override
    public User singIn(String login, String password) throws ServiceException {
        try {
            validator.validate(login, password);
            User user = userDAO.signIn(login, password);
            if (user != null && User.Role.USER.equals(user.getRole())) {
                return user;
            } else {
                return null;
            }
        } catch (DAOException e) {
            throw new ValidationException("user validation failed", e);
        }
    }

    @Override
    public User adminSignIn(String login, String password) throws ServiceException {
        validator.validate(login, password);
        try {
            User admin = userDAO.signIn(login, password);
            if (admin != null && User.Role.ADMIN.equals(admin.getRole())) {
                return admin;
            } else {
                return null;
            }
        } catch (DAOException e) {
            throw new ServiceException("admin's signing in failed", e);
        }
    }

    @Override
    public List<User> getUsers(int currentPage) throws ServiceException {
        try {
            return userDAO.getUsers(currentPage);
        } catch (DAOException e) {
            throw new ServiceException("fetching user's page failed, current page: " + currentPage, e);
        }
    }

    @Override
    public void registerUser(String name, String surname, String login, String password, String email, String number) throws ServiceException {
        validator.validate(name, surname, login, password, email, number);
        try {
            userDAO.register(name, surname, login, password, email, number);
        } catch (DAOException e) {
            throw new ServiceException("registration failed", e);
        }
    }

    @Override
    public void updateUser(int id, String role, int banned) throws ServiceException {
        UserValidatorImpl validatorUser = new UserValidatorImpl();
        try {
            validatorUser.validateID(id);
            validatorUser.validateBanned(banned);
            validatorUser.validateRole(role);
            userDAO.editUser(id, role, banned);
        } catch (ValidationException | DAOException e) {
            throw new ServiceException("updateUser failed", e);
        }
    }

    @Override
    public int getTotalRowCount() throws ServiceException {
        try {
            return userDAO.getTotalRowCount();
        } catch (DAOException e) {
            throw new ServiceException("error during getting user's total row count", e);
        }
    }
}
