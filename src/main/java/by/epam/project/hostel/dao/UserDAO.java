package by.epam.project.hostel.dao;

import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.User;
import by.epam.project.hostel.entity.pagination.Page;

public interface UserDAO extends EntityDAO{
    User signIn(String login, String password) throws DAOException;

    void deleteUser(String login) throws DAOException;

    Page<User> getPageWithUsers(int page) throws DAOException;

    void editUser(int id, String role, int banned) throws DAOException;

    void registration(String name, String surname, String login, String password, String email, String number) throws DAOException;
}
