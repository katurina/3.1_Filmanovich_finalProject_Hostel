package by.epam.project.hostel.service;

import by.epam.project.hostel.entity.User;
import by.epam.project.hostel.service.exception.ServiceException;

public interface UserService {
    User singIn(String login, String password) throws ServiceException;
    void registerUser(String name, String surname, String login, String password, String email) throws ServiceException;
}
