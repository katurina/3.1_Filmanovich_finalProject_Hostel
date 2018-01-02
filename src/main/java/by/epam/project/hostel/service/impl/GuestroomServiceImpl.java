package by.epam.project.hostel.service.impl;

import by.epam.project.hostel.dao.DAOFactory;
import by.epam.project.hostel.dao.GuestroomDAO;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.service.GuestroomService;
import by.epam.project.hostel.service.exception.ServiceException;

public class GuestroomServiceImpl implements GuestroomService {

    private static final GuestroomDAO guestroomDAO = DAOFactory.getInstance().getGuestroomDAO();

    @Override
    public int getTotalRowCount() throws ServiceException {
        try {
            return guestroomDAO.getTotalRowCount();
        } catch (DAOException e) {
            throw new ServiceException("error during getting guestroom's total row count", e);
        }
    }
}
