package by.epam.project.hostel.service.validation.impl;

import by.epam.project.hostel.entity.User;
import by.epam.project.hostel.service.exception.EmptyParamServiceException;
import by.epam.project.hostel.service.exception.ValidationException;
import by.epam.project.hostel.service.validation.Validator;


public class UserValidatorImpl implements Validator<User> {

    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";


    public void validateBanned(int banned) throws ValidationException {
        if (banned > 1 || banned < 0) {
            throw new ValidationException("error during validation user ban situation: " + banned);
        }
    }

    public void validateRole(String role) throws ValidationException {
        if (!role.toUpperCase().equals(ADMIN) && !role.toUpperCase().equals(USER)) {
            throw new ValidationException("error during validation user role");
        }
    }

    public void validate(User user) throws ValidationException {
        if (user == null) {
            throw new EmptyParamServiceException("error during validation parameter user is empty");
        }
        validateID(user.getId());
        validate(user.getEmail(), user.getLogin(), user.getName(), user.getNumber(), user.getPassword(), user.getSurname());
    }

}
