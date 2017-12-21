package by.epam.project.hostel.service.validation.impl;

import by.epam.project.hostel.entity.User;
import by.epam.project.hostel.service.exception.UserEmptyParamServiceException;
import by.epam.project.hostel.service.exception.ValidationException;
import by.epam.project.hostel.service.validation.Validator;


public class ValidatorUserImpl implements Validator<User> {

    @Override
    public void validate(User entity) throws UserEmptyParamServiceException {
//        todo
    }


    public void validateID(int id) throws ValidationException {
        if (id < 1) {
            throw new ValidationException("error during validation id: " + id);
        }
    }

    public void validateBanned(int banned) throws ValidationException {
        if (banned > 1 || banned < 0) {
            throw new ValidationException("error during validation user ban situation: " + banned);
        }
    }

    public void validateRole(String role) throws ValidationException {
        if (!role.toUpperCase().equals("ADMIN") && !role.toUpperCase().equals("USER")) {
            throw new ValidationException("error during validation user role");
        }

    }
}
