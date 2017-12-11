package by.epam.project.hostel.controller;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.controller.command.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final String COMMAND = "command";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND);
        Command command = CommandFactory.getInstance().getCommand(commandName);
        try {
            command.execute(request, response);
        } catch (CommandException e) {
            e.printStackTrace();// выучи обработку исключительных ситуаций
            // осознай, когда ты гасишь исключение - то что взамен ты должен сделать
            // представь, что это исключение произошло - какой ответ твое веб-приложение пошлет клиенту в этом случае
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND);
        Command command = CommandFactory.getInstance().getCommand(commandName);
        try {
            command.execute(request, response);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
}
