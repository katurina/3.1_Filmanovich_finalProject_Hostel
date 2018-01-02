package by.epam.project.hostel.service.validation.impl;

import by.epam.project.hostel.entity.User;
import by.epam.project.hostel.service.exception.EmptyParamServiceException;
import by.epam.project.hostel.service.exception.ValidationException;
import by.epam.project.hostel.service.validation.Validator;


public class ValidatorUserImpl implements Validator<User> {

    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";

    @Override
    public void validate(User entity) throws EmptyParamServiceException {
//        todo
    }

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
}
