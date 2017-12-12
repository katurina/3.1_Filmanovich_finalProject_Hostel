package by.epam.project.hostel.dao;

import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.User;

public interface UserDAO {
    User signIn(String login, String password) throws DAOException;

    void deleteUser(String login) throws DAOException;

    void registration(String name, String surname, String login, String password, String email, String number) throws DAOException;
}
