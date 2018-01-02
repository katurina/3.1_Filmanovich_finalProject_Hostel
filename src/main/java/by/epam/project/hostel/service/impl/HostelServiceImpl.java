package by.epam.project.hostel.service.impl;

import by.epam.project.hostel.dao.DAOFactory;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Hostel;
import by.epam.project.hostel.service.HostelService;
import by.epam.project.hostel.service.exception.ValidationException;
import by.epam.project.hostel.service.validation.Validator;
import by.epam.project.hostel.service.validation.impl.ValidatorHostelImpl;

public class HostelServiceImpl implements HostelService {
    private static final Validator<Hostel> validator = new ValidatorHostelImpl();

    @Override
    public Hostel getHostelById(int id, String language) throws ValidationException {
        try {
            validator.validateID(id);
            validator.validate(language);
            return DAOFactory.getInstance().getHostelDAO().getHostelById(id, language);
        } catch (DAOException e) {
            throw new ValidationException("error during validation getting hostel by id", e);
        }
    }
}
