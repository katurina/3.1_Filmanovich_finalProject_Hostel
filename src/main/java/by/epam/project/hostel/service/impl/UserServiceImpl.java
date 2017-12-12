package by.epam.project.hostel.service.impl;

import by.epam.project.hostel.dao.DAOFactory;
import by.epam.project.hostel.dao.UserDAO;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.User;
import by.epam.project.hostel.service.UserService;
import by.epam.project.hostel.service.exception.ServiceException;
import by.epam.project.hostel.service.exception.UserEmptyParamServiceException;

public class UserServiceImpl implements UserService {

    private static final UserDAO USER_DAO = DAOFactory.getInstance().getUserDAO();
    private static final String ADMIN = "admin";

    @Override
    public User singIn(String login, String password) throws ServiceException {
        try {
            validate(login, password);
            return USER_DAO.signIn(login, password);
        } catch (DAOException e) {
            throw new ServiceException("validation failed", e);
        }
    }

    @Override
    public User adminSignIn(String login, String password, String role) throws ServiceException {
        validate(login, password);
        validateRole(role);
        try {
            return USER_DAO.signIn(login, password);
        } catch (DAOException e) {
            throw new ServiceException("admin's signing in failed", e);
        }
    }

    @Override
    public void registerUser(String name, String surname, String login, String password, String email, String number) throws ServiceException {
        try {
            validate(name, surname, login, password, email, number);
            USER_DAO.registration(name, surname, login, password, email, number);
        } catch (DAOException e) {
            throw new ServiceException("registration failed", e);
        }
    }

    private void validate(String... param) throws UserEmptyParamServiceException {
        if (param != null) {
            for (String parm : param) {
                if (parm == null || parm.isEmpty()) {
                    throw new UserEmptyParamServiceException("empty param" + parm);
                }
            }
        } else {
            throw new UserEmptyParamServiceException("not contains params");
        }
    }

    private void validateRole(String role) throws UserEmptyParamServiceException {
        if (role == null || role.isEmpty() || !role.equals(ADMIN)) {
            throw new UserEmptyParamServiceException("role isn't admin: " + role);
        }
    }
}
