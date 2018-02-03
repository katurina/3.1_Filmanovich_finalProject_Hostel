package by.epam.project.hostel.dao.impl;

import by.epam.project.hostel.dao.BookingDAO;
import by.epam.project.hostel.dao.exception.ConnectionPoolException;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static by.epam.project.hostel.controller.pagination.PageWrapper.MAX_ENTRIES_PER_PAGE;

public class BookingDAOImpl extends BaseDAO implements BookingDAO {
    private static final String SELECT_BOOKINGS_BY_USER_ID_LIMIT = "SELECT  bookings.id AS booking_id,  guestrooms_id,  bookings.night_price,  start_day,  last_day,  payed,  book_day, all_price FROM bookings INNER JOIN guestrooms g ON bookings.guestrooms_id = g.id WHERE user_id = ? LIMIT ?,?";
    private static final String DELETE_BOOKING_BY_ID = "DELETE FROM bookings WHERE id = ?";

    @Override
    public void bookRoom(double nightPrice, LocalDate startDay, LocalDate lastDay, boolean payed, LocalDate bookDay, double finalCost, int userId, int guestroomId) {

    }


    @Override
    public List<Booking> getUserBookings(int userId, int currentPage) throws DAOException {
        try (Connection connection = provider.connection();
             PreparedStatement ps = connection.prepareStatement(SELECT_BOOKINGS_BY_USER_ID_LIMIT)) {
            ps.setInt(1, userId);
            ps.setInt(2, (currentPage - 1) * MAX_ENTRIES_PER_PAGE);
            ps.setInt(3, MAX_ENTRIES_PER_PAGE);
            try (ResultSet rs = ps.executeQuery()) {
                List<Booking> bookings = new ArrayList<>();
                while (rs.next()) {
                    bookings.add(createBooking(rs));
                }
                return bookings;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during getting user's bookings", e);
        }
    }

    @Override
    public void deleteBookingById(Integer bookingId) throws DAOException {
        try (Connection connection = provider.connection();
             PreparedStatement ps = connection.prepareStatement(DELETE_BOOKING_BY_ID)) {
            ps.setInt(1, bookingId);
            ps.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during deleting booking by id = " + bookingId, e);
        }
    }

    private Booking createBooking(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setId(rs.getInt(1));
        booking.setGuestroomId(rs.getInt(2));
        booking.setNightPrice(rs.getBigDecimal(3));
        booking.setStartDay(rs.getDate(4).toLocalDate());
        booking.setLastDay(rs.getDate(5).toLocalDate());
        booking.setPayed(rs.getInt(6));
        booking.setBookDay(rs.getDate(7).toLocalDate());
        booking.setFinalCost(rs.getBigDecimal(8));
        booking.setNightsCount(Period.between(booking.getStartDay(), booking.getLastDay()).getDays());
        return booking;
    }

    @Override
    protected String getTableName() {
        return "bookings";
    }

}
