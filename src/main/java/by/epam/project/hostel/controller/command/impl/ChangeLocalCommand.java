package by.epam.project.hostel.controller.command.impl;

import by.epam.project.hostel.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.project.hostel.controller.constant.Constant.Page.LOCAL;

public class ChangeLocalCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ChangeLocalCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(LOCAL, request.getParameter(LOCAL));
        String currentPage = String.valueOf(request.getSession().getAttribute("url"));

        try {
            response.sendRedirect(currentPage);
        } catch (IOException e) {
            LOGGER.error("error during changing local",e);
        }
    }
}
