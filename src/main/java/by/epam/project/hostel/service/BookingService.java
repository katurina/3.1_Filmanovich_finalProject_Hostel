package by.epam.project.hostel.service;

import by.epam.project.hostel.entity.Booking;
import by.epam.project.hostel.service.exception.ServiceException;

import java.util.List;

public interface BookingService extends EntityService {
    List<Booking> getUserBooking(int userId, int currentPage) throws ServiceException;

    void deleteBookingById(Integer bookingId) throws ServiceException;

}
