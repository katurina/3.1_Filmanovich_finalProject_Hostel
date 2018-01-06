package by.epam.project.hostel.controller.command.impl.booking;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.controller.command.impl.user.EditUserCommand;
import by.epam.project.hostel.controller.constant.Constant;
import by.epam.project.hostel.controller.pagination.PageWrapper;
import by.epam.project.hostel.entity.Booking;
import by.epam.project.hostel.entity.User;
import by.epam.project.hostel.entity.pagination.Page;
import by.epam.project.hostel.service.BookingService;
import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class GetBookingsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(EditUserCommand.class);
    private static final BookingService bookingService = ServiceFactory.getInstance().getBookingService();

    //    todo current page
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(Constant.User.USER);
        int userId = user.getId();
        int currentPage = request.getParameter(Constant.Page.CURRENT_PAGE) == null ?
                1 : Integer.valueOf(request.getParameter(Constant.Page.CURRENT_PAGE));
        try {
            List<Booking> userBooking = bookingService.getUserBooking(userId, currentPage);
            int totalRowCount = bookingService.getTotalRowCount();
            Page<Booking> page = PageWrapper.wrapList(userBooking, currentPage, totalRowCount);
            request.setAttribute(Constant.Page.PAGE, page);
        } catch (ServiceException e) {
            logger.error("error during getting user bookings", e);
        }
    }
}
