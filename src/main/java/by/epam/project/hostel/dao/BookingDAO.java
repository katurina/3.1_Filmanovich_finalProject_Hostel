package by.epam.project.hostel.dao;

import java.util.Date;

public interface BookingDAO {
    void bookRoom(double nightPrice, Date startDay,
                  Date lastDay, boolean payed,
                  Date bookDay, double finalCost,
                  int userId, int guestroomId);
}
