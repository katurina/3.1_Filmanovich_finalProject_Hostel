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

import static by.epam.project.hostel.controller.constant.Constant.ADMIN_ENTRY;
import static by.epam.project.hostel.controller.constant.Constant.ADMIN_SIGN_IN;
import static by.epam.project.hostel.controller.constant.Constant.FALSE;
import static by.epam.project.hostel.controller.constant.Constant.LOGIN;
import static by.epam.project.hostel.controller.constant.Constant.PASSWORD;
import static by.epam.project.hostel.controller.constant.Constant.TRUE;
import static by.epam.project.hostel.controller.constant.Constant.USER;
import static by.epam.project.hostel.controller.constant.Constant.VALID_PARAM;

public class LoginAdminCommand implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String isValid = FALSE;
        String forwardPage = ADMIN_ENTRY;
        try {
            User user = ServiceFactory.getInstance().getUserService().adminSignIn(login, password);
            if (user != null) {
                request.getSession().setAttribute(USER, user);
                isValid = TRUE;
            } else {
                forwardPage = ADMIN_SIGN_IN;
            }
        } catch (UserEmptyParamServiceException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
//           todo
            e.printStackTrace();
        }
        try {
            request.getSession().setAttribute(VALID_PARAM, isValid);
            request.getRequestDispatcher(forwardPage).forward(request, response);
        } catch (ServletException | IOException e) {
//           todo
            e.printStackTrace();
        }
    }
}
