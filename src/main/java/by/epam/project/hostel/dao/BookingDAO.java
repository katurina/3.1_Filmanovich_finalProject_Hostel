package by.epam.project.hostel.dao;

import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingDAO extends EntityDAO{
    void bookRoom(
            double nightPrice, LocalDate startDay, LocalDate lastDay, boolean payed, LocalDate bookDay,
            double finalCost, int userId, int guestroomId);

    List<Booking> getUserBookings(int userId, int currentPage) throws DAOException;
}
