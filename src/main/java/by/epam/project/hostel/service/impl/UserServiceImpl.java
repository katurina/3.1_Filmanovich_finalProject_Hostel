package by.epam.project.hostel.service.impl;

import by.epam.project.hostel.dao.DAOFactory;
import by.epam.project.hostel.dao.UserDAO;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.User;
import by.epam.project.hostel.entity.pagination.Page;
import by.epam.project.hostel.service.UserService;
import by.epam.project.hostel.service.exception.ServiceException;
import by.epam.project.hostel.service.exception.ValidationException;
import by.epam.project.hostel.service.validation.Validator;
import by.epam.project.hostel.service.validation.impl.ValidatorUserImpl;

public class UserServiceImpl implements UserService {

    private static final UserDAO USER_DAO = DAOFactory.getInstance().getUserDAO();
    private static final Validator<User> VALIDATOR = new ValidatorUserImpl();


    @Override
    public User singIn(String login, String password) throws ServiceException {
        try {
            VALIDATOR.validate(login, password);
            User user = USER_DAO.signIn(login, password);
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
        VALIDATOR.validate(login, password);
        try {
            User admin = USER_DAO.signIn(login, password);
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
    public Page<User> getUsersPage(int currentPage) throws ServiceException {
        try {
            return USER_DAO.getPageWithUsers(currentPage);
        } catch (DAOException e) {
            throw new ServiceException("fetching user's page failed, current page: " + currentPage, e);
        }
    }

    @Override
    public void registerUser(String name, String surname, String login, String password, String email, String number) throws ServiceException {

        VALIDATOR.validate(name, surname, login, password, email, number);

        try {
            USER_DAO.registration(name, surname, login, password, email, number);
        } catch (DAOException e) {
            throw new ServiceException("registration failed", e);
        }
    }

    @Override
    public void updateUser(int id, String role, int banned) throws ServiceException {
        ValidatorUserImpl validatorUser = new ValidatorUserImpl();
        try {
            validatorUser.validateID(id);
            validatorUser.validateBanned(banned);
            validatorUser.validateRole(role);
            USER_DAO.editUser(id, role, banned);
        } catch (ValidationException | DAOException e) {
            throw new ServiceException("updateUser failed", e);
        }
    }


}
