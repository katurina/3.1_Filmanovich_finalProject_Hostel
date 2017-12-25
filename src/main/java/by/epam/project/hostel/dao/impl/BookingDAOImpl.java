package by.epam.project.hostel.dao.impl;

import by.epam.project.hostel.dao.BookingDAO;
import by.epam.project.hostel.dao.db.connection.ConnectionProvider;

import java.util.Date;

public class BookingDAOImpl implements BookingDAO {
    private static final ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

    @Override
    public void bookRoom(double nightPrice, Date startDay, Date lastDay, boolean payed, Date bookDay, double finalCost, int userId, int guestroomId) {

    }
}
