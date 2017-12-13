package by.epam.project.hostel.service.validation.impl;

import by.epam.project.hostel.entity.User;
import by.epam.project.hostel.service.exception.UserEmptyParamServiceException;
import by.epam.project.hostel.service.validation.Validator;


public class ValidatorUserImpl implements Validator<User> {

    @Override
    public void validate(User entity) throws UserEmptyParamServiceException {

    }
}
