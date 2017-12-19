package by.epam.project.hostel.controller.command.impl;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.controller.constant.Constant;
import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.UserService;
import by.epam.project.hostel.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.project.hostel.controller.constant.Constant.PageJSP.ADMIN_ADMIN_USERS;
import static by.epam.project.hostel.controller.constant.Constant.User.BANNED;
import static by.epam.project.hostel.controller.constant.Constant.User.ROLE;


public class EditUserCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(EditUserCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.getInteger(request.getParameter(Constant.User.ID));
        String role = request.getParameter(ROLE);
        String banned = request.getParameter(BANNED);
        ServiceFactory instance = ServiceFactory.getInstance();
        UserService userService = instance.getUserService();
        try {
            userService.updateUser(id, role, Integer.getInteger(banned));
            request.getRequestDispatcher(ADMIN_ADMIN_USERS).forward(request, response);
        } catch (ServiceException e) {
            LOGGER.error("error during editing user", e);
        }

    }
}
