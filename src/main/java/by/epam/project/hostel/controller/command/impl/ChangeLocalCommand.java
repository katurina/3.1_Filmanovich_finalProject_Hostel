package by.epam.project.hostel.controller.command.impl;

import by.epam.project.hostel.controller.command.Command;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.project.hostel.controller.constant.Constant.PageJSP.CURRENT_PAGE;
import static by.epam.project.hostel.controller.constant.Constant.PageJSP.LOCAL;

public class ChangeLocalCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ChangeLocalCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession(true).setAttribute(LOCAL, request.getParameter(LOCAL));
        try {
            String currentPage = String.valueOf(request.getParameter(CURRENT_PAGE));
            request.getRequestDispatcher(currentPage).forward(request, response);// не всегда такое поможет
            // результат отображения страницы зависит от выборки данных, которые на нее пришли
            // п ты просто переходишь на jsp , без перевыборки
        } catch (ServletException | IOException e) {
            LOGGER.error("error in change local command", e);
        }
    }
}
