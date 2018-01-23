package by.epam.project.hostel.controller.command.impl.booking;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static by.epam.project.hostel.controller.constant.Constant.Booking.GUESTROOM_ID;
import static by.epam.project.hostel.controller.constant.Constant.Booking.LAST_DATE;
import static by.epam.project.hostel.controller.constant.Constant.Booking.START_DATE;
import static by.epam.project.hostel.controller.constant.Constant.Page.LOCAL;
import static by.epam.project.hostel.controller.constant.Constant.User.USER;

public class BookingRoomCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = (String) request.getSession().getAttribute(LOCAL);

        User user = (User) request.getSession().getAttribute(USER);
        Integer roomId = Integer.valueOf(request.getParameter(GUESTROOM_ID));
        Date lastDate = (Date) request.getAttribute(LAST_DATE);
        Date startDay = (Date) request.getAttribute(START_DATE);
    }
}
