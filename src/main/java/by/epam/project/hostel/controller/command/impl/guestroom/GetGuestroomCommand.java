package by.epam.project.hostel.controller.command.impl.guestroom;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.controller.constant.Constant;
import by.epam.project.hostel.entity.Guestroom;
import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetGuestroomCommand implements Command {

    private static final Logger logger = LogManager.getLogger(GetGuestroomCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter(Constant.Guestroom.ID));
        String language = (String) request.getSession().getAttribute(Constant.Page.LOCAL);
        try {
            Guestroom guestroom = ServiceFactory.getInstance().getGuestroomService().getGuestroomById(id, language);
            request.setAttribute(Constant.Guestroom.GUESTROOM, guestroom);
        } catch (ServiceException e) {
            logger.error("error during getting guestroom by id = " + id, e);
        }

    }
}
