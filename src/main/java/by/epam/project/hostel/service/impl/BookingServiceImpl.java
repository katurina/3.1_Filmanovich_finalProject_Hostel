package by.epam.project.hostel.service.impl;

import by.epam.project.hostel.dao.BookingDAO;
import by.epam.project.hostel.dao.DAOFactory;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Booking;
import by.epam.project.hostel.service.BookingService;
import by.epam.project.hostel.service.exception.ServiceException;
import by.epam.project.hostel.service.validation.Validator;
import by.epam.project.hostel.service.validation.impl.BookingValidatorImpl;

import java.util.List;

public class BookingServiceImpl implements BookingService {

    private static final BookingDAO bookingDAO = DAOFactory.getInstance().getBookingDAO();
    private static final Validator<Booking> validator = new BookingValidatorImpl();

    @Override
    public int getTotalRowCount() throws ServiceException {
        try {
            return bookingDAO.getTotalRowCount();
        } catch (DAOException e) {
            throw new ServiceException("error during getting booking's total row count", e);
        }
    }

    @Override
    public List<Booking> getUserBooking(int userId, int currentPage) throws ServiceException {
        validator.validateID(userId);
        validator.validateCurrentPage(currentPage);
        try {
            return bookingDAO.getUserBookings(userId, currentPage);
        } catch (DAOException e) {
            throw new ServiceException("error during getting user's bookings", e);
        }
    }
}
