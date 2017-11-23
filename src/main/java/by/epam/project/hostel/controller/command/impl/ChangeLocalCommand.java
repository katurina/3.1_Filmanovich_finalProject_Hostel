package by.epam.project.hostel.controller.command.impl;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.controller.command.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocalCommand implements Command {

    private static final String LOCAL = "local";
    private static final String CURRENT_PAGE = "current-page";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        request.getSession(true).setAttribute(LOCAL,request.getParameter(LOCAL));
        try {
            String currentPage = String.valueOf(request.getParameter(CURRENT_PAGE));
            request.getRequestDispatcher(currentPage).forward(request,response);
        } catch (ServletException | IOException e) {
            throw new CommandException(e);
        }
    }
}
