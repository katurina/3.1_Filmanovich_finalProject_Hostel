package by.epam.project.hostel.controller.command.impl.user;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.entity.User;
import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.project.hostel.controller.constant.Constant.Page.ERROR;
import static by.epam.project.hostel.controller.constant.Constant.Page.INDEX_JSP;
import static by.epam.project.hostel.controller.constant.Constant.User.LOGIN;
import static by.epam.project.hostel.controller.constant.Constant.User.PASSWORD;
import static by.epam.project.hostel.controller.constant.Constant.User.USER;


public class LoginCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Boolean error = true;
        try {
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            User user = ServiceFactory.getInstance().getUserService().singIn(login, password);
            if (user != null) {
                request.getSession().setAttribute(USER, user);
                error = false;
            }
        } catch (ServiceException e) {
            LOGGER.error("error during login command", e);
        }
        try {
            request.setAttribute(ERROR, error);
            request.getRequestDispatcher(INDEX_JSP).forward(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.error("error during forward in login command", e);
        }
    }
}
