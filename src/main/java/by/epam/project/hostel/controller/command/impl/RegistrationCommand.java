package by.epam.project.hostel.controller.command.impl;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String INDEX_JSP = "index.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String email = request.getParameter(EMAIL);
        try {
            ServiceFactory.getInstance().getUserService().registerUser(name, surname, login, password, email);
            response.sendRedirect(INDEX_JSP);
        } catch (ServiceException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);// что??????????????????
            // ну что за фигню ты здесь понаписывал
        }
    }
}
