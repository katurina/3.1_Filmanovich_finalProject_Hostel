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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.project.hostel.controller.constant.Constant.Exception.ERROR_LOGIN_PARAM;
import static by.epam.project.hostel.controller.constant.Constant.Exception.TRUE;
import static by.epam.project.hostel.controller.constant.Constant.Page.CURRENT_PAGE;
import static by.epam.project.hostel.controller.constant.Constant.Page.INDEX_JSP;


public class GetBookingsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(EditUserCommand.class);
    private static final BookingService bookingService = ServiceFactory.getInstance().getBookingService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(Constant.User.USER);
        if (user == null) {
            request.setAttribute(ERROR_LOGIN_PARAM, TRUE);
            try {
                request.getRequestDispatcher(INDEX_JSP).forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error("error during forward to index.jsp from get when user == null", e);
            }
        } else {
            int userId = user.getId();
            String pageParam = request.getParameter(CURRENT_PAGE);
            int currentPage = (pageParam == null || pageParam.isEmpty()) ? 1 : Integer.valueOf(pageParam);
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
}
