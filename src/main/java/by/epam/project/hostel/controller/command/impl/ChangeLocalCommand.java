package by.epam.project.hostel.controller.command.impl;

import by.epam.project.hostel.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocalCommand implements Command {

    private static final String LOCAL = "local";
    private static final String CURRENT_PAGE = "current-page";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)  {
        request.getSession(true).setAttribute(LOCAL,request.getParameter(LOCAL));
        try {
            String currentPage = String.valueOf(request.getParameter(CURRENT_PAGE));
            request.getRequestDispatcher(currentPage).forward(request,response);// не всегда такое поможет
            // результат отображения страницы зависит от выборки данных, которые на нее пришли
            // п ты просто переходишь на jsp , без перевыборки
        } catch (ServletException | IOException e) {
            e.printStackTrace();//а это что тут такое затесалось??
        }
    }
}
