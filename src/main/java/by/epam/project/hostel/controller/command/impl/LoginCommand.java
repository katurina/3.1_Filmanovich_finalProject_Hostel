package by.epam.project.hostel.controller.command.impl;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.entity.User;
import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.exception.ServiceException;
import by.epam.project.hostel.service.exception.UserEmptyParamServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements Command {

    private static final String PASSWORD = "password";
    private static final String LOGIN = "login";
    private static final String USER = "user";
    private static final String INDEX_JSP = "index.jsp";
    private static final String EMPTY_PARAM = "emptparam";
    private static final String LOGIN_JSP = "login.jsp";
    private static final String TRUE = "true";
    private static final String FALSE = "false";
    private static final String VALID_PARAM = "validParam";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        try {
            User user = ServiceFactory.getInstance().getUserService().singIn(login, password);
            request.getSession().setAttribute(USER, user);
            request.getSession().setAttribute(VALID_PARAM, TRUE);
            request.getRequestDispatcher(INDEX_JSP).forward(request, response);
        } catch (UserEmptyParamServiceException e) {
            e.printStackTrace();
            request.getSession().setAttribute(VALID_PARAM, LoginCommand.FALSE);
            try {
                request.getRequestDispatcher(LOGIN_JSP).forward(request,response);
            } catch (ServletException | IOException e1) {
                e1.printStackTrace();
            }
        } catch (ServiceException | ServletException | IOException e) {
            e.printStackTrace();
        }


    }
}
