package by.epam.project.hostel.service;

import by.epam.project.hostel.entity.User;
import by.epam.project.hostel.entity.pagination.Page;
import by.epam.project.hostel.service.exception.ServiceException;

public interface UserService {
    User singIn(String login, String password) throws ServiceException;

    User adminSignIn(String login, String password) throws ServiceException;

    Page<User> getUsersPage(int currentPage) throws ServiceException;

    void registerUser(String name, String surname, String login, String password, String email, String number) throws ServiceException;

    void updateUser(int id, String role, int banned) throws ServiceException;
}
