package by.epam.project.hostel.controller.command.impl;

import by.epam.project.hostel.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.project.hostel.controller.constant.Constant.User.USER;

public class LogOutCommand implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(USER);
        }
        try {
            response.sendRedirect("admin_sign_in.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
