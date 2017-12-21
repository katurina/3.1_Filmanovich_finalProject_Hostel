package by.epam.project.hostel.controller.command.impl;

import by.epam.project.hostel.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.project.hostel.controller.constant.Constant.PageJSP.ADMIN_SIGN_IN;
import static by.epam.project.hostel.controller.constant.Constant.User.USER;

public class LogoutCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LogoutCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(USER);
        }
        try {
            response.sendRedirect(ADMIN_SIGN_IN);
        } catch (IOException e) {
            LOGGER.error("error during sendRedirect in logout command", e);
        }
    }
}
