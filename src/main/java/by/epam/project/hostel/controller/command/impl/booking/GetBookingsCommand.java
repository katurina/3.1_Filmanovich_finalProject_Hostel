package by.epam.project.hostel.controller.command.impl.booking;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.controller.command.impl.user.EditUserCommand;
import by.epam.project.hostel.controller.constant.Constant;
import by.epam.project.hostel.entity.User;
import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetBookingsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(EditUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(Constant.User.USER);
        int userId = user.getId();
        int currentPage = Integer.valueOf(request.getParameter(Constant.Page.CURRENT_PAGE));
        try {
            ServiceFactory.getInstance().getBookingService().getUserBooking(userId, currentPage);
        } catch (ServiceException e) {
            LOGGER.error("error during getting user bookings", e);
        }
    }
}
