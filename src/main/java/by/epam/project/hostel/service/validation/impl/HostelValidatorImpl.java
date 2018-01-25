package by.epam.project.hostel.service.validation.impl;

import by.epam.project.hostel.entity.Hostel;
import by.epam.project.hostel.service.exception.ValidationException;
import by.epam.project.hostel.service.validation.Validator;

public class HostelValidatorImpl implements Validator<Hostel> {


    @Override
    public void validate(Hostel entity) throws ValidationException {

        validate(entity.getAddress(), entity.getCity(), entity.getCountry(), entity.getDescription(), entity.getImgPath(), entity.getName());

        if (entity.getStars() > 5 || entity.getStars() < 1) {
            throw new ValidationException("not correct count of stars = " + entity.getStars());
        }
    }
}
