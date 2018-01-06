package by.epam.project.hostel.service.impl;

import by.epam.project.hostel.dao.DAOFactory;
import by.epam.project.hostel.dao.GuestroomDAO;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Guestroom;
import by.epam.project.hostel.service.GuestroomService;
import by.epam.project.hostel.service.exception.ServiceException;
import by.epam.project.hostel.service.validation.Validator;
import by.epam.project.hostel.service.validation.impl.GuestroomValidatorImpl;

public class GuestroomServiceImpl implements GuestroomService {

    private static final GuestroomDAO guestroomDAO = DAOFactory.getInstance().getGuestroomDAO();
    private static final Validator<Guestroom> validator = new GuestroomValidatorImpl();

    @Override
    public Guestroom getGuestroomById(int id, String language) throws ServiceException {
        validator.validateID(id);
        validator.validate(language);
        try {
            return guestroomDAO.getGuestRoomById(id, language);
        } catch (DAOException e) {
            throw new ServiceException("error during getting guestroom by id = " + id, e);
        }
    }

    @Override
    public int getTotalRowCount() throws ServiceException {
        try {
            return guestroomDAO.getTotalRowCount();
        } catch (DAOException e) {
            throw new ServiceException("error during getting guestroom's total row count", e);
        }
    }
}
