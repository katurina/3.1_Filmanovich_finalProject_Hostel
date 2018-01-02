package by.epam.project.hostel.controller.command.impl.user;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.controller.constant.Constant;
import by.epam.project.hostel.controller.pagination.PageWrapper;
import by.epam.project.hostel.entity.User;
import by.epam.project.hostel.entity.pagination.Page;
import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.UserService;
import by.epam.project.hostel.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class GetUsersCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(GetUsersCommand.class);
    private static final String CURRENT_PAGE = "current-page";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory instance = ServiceFactory.getInstance();
        UserService userService = instance.getUserService();
        try {
            int currentPage = Integer.valueOf(request.getParameter(CURRENT_PAGE));
            List<User> users = userService.getUsers(currentPage);
            int totalRowCount = userService.getTotalRowCount();
            Page<User> usersPage = PageWrapper.wrapList(users, currentPage, totalRowCount);
            request.setAttribute(Constant.PAGE, usersPage);
        } catch (ServiceException e) {
            LOGGER.error("error during getting page with users", e);
        }

    }
}
