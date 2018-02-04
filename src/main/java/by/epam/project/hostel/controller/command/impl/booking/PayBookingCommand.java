package by.epam.project.hostel.controller.command.impl.booking;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.project.hostel.controller.constant.Constant.Booking.ID;

public class PayBookingCommand implements Command {

    private static final Logger logger = LogManager.getLogger(PayBookingCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer bookingId = Integer.valueOf(request.getParameter(ID));
        try {
            ServiceFactory.getInstance().getBookingService().payBooking(bookingId);
            response.sendRedirect("/user/bookings");
        } catch (ServiceException e) {
            logger.error("error during pay booking", e);
        }
    }
}
