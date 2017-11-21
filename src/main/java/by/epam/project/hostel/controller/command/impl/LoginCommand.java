package by.epam.project.hostel.controller.command.impl;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.entity.User;
import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements Command {

    private static final String PASSWORD = "password";
    private static final String LOGIN = "login";
    private static final String USER = "user";
    private static final String INDEX_JSP = "index.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        try {
            User user = ServiceFactory.getInstance().getUserService().singIn(login, password);
            request.getSession().setAttribute(USER, user);
            request.getRequestDispatcher(INDEX_JSP).forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
}
