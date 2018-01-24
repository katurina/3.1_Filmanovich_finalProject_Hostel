package by.epam.project.hostel.service.impl;

import by.epam.project.hostel.dao.DAOFactory;
import by.epam.project.hostel.dao.HostelDAO;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Hostel;
import by.epam.project.hostel.service.HostelService;
import by.epam.project.hostel.service.exception.ServiceException;
import by.epam.project.hostel.service.exception.ValidationException;
import by.epam.project.hostel.service.validation.Validator;
import by.epam.project.hostel.service.validation.impl.HostelValidatorImpl;

import java.util.List;

public class HostelServiceImpl implements HostelService {
    private static final Validator<Hostel> validator = new HostelValidatorImpl();
    private static final HostelDAO hostelDAO = DAOFactory.getInstance().getHostelDAO();

    @Override
    public Hostel getHostelById(int id, String language) throws ValidationException {
        validator.validateID(id);
        validator.validate(language);
        try {
            return hostelDAO.getHostelById(id, language);
        } catch (DAOException e) {
            throw new ValidationException("error during validation getting hostel by id", e);
        }
    }

    @Override
    public List<Hostel> getHostels(String language) throws ServiceException {
        validator.validate(language);
        try {
            return hostelDAO.getHostels(language);
        } catch (DAOException e) {
            throw new ServiceException("error during getting whole hostels");
        }
    }

    @Override
    public void deleteHostelById(Integer hostelId) throws ServiceException {
        validator.validateID(hostelId);
        try {
            return hostelDAO.deleteHostelById(hostelId);
        } catch (DAOException e) {
            throw new ServiceException("error during delete hostel by id = " + hostelId, e);
        }
    }

    @Override
    public int getTotalRowCount() throws ServiceException {
        try {
            return hostelDAO.getTotalRowCount();
        } catch (DAOException e) {
            throw new ServiceException("error during getting hostel's total row count", e);
        }
    }
}
