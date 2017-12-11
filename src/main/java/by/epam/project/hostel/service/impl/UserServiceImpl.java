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

    @Override
    public User singIn(String login, String password) throws ServiceException {
        try {
            if (login == null || login.isEmpty()) {// валидацию выноси отдельно - в метод или класс, иначе реальная она у тебя замусорит весь код
                throw new UserEmptyParamServiceException("login is empty");
            }
            if (password == null|| password.isEmpty()) {
                throw new UserEmptyParamServiceException("password is empty");
            }
            return USER_DAO.signIn(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);// свои сообщения - помним про них
        }
    }

    @Override
    public void registerUser(String name, String surname, String login, String password, String email) throws ServiceException {
        try {// а валидация куда пропала?
            USER_DAO.registration(name, surname, login, password, email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
