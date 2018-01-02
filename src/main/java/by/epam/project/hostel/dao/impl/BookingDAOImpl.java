package by.epam.project.hostel.dao.impl;

import by.epam.project.hostel.dao.BookingDAO;
import by.epam.project.hostel.dao.db.connection.ConnectionProvider;
import by.epam.project.hostel.dao.exception.ConnectionPoolException;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static by.epam.project.hostel.controller.pagination.PageWrapper.MAX_ENTRIES_PER_PAGE;

public class BookingDAOImpl implements BookingDAO {
    private static final ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
    private static final String SELECT_BOOKINGS_BY_USER_ID_LIMIT = "SELECT  bookings.id AS booking_id,  guestrooms_id,  bookings.night_price,  start_day,  last_day,  payed,  book_day, all_price FROM bookings INNER JOIN guestrooms g ON bookings.guestrooms_id = g.id WHERE user_id = ? LIMIT ?,?";

    @Override
    public void bookRoom(double nightPrice, Date startDay, Date lastDay, boolean payed, Date bookDay, double finalCost, int userId, int guestroomId) {

    }

    @Override
    public List<Booking> getUserBookings(int userId, int currentPage) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_BOOKINGS_BY_USER_ID_LIMIT)) {
            ps.setInt(1, userId);
            ps.setInt(2, currentPage);
            ps.setInt(3, MAX_ENTRIES_PER_PAGE);
            try (ResultSet rs = ps.getResultSet()) {
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

    private Booking createBooking(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setId(rs.getInt(1));
        booking.setGuestroomId(rs.getInt(2));
        booking.setNightPrice(rs.getDouble(3));
        booking.setStartDay(rs.getDate(4));
        booking.setLastDay(rs.getDate(5));
        booking.setPayed(rs.getInt(6));
        booking.setBookDay(rs.getDate(7));
        booking.setFinalCost(rs.getDouble(8));
        return booking;
    }
}
