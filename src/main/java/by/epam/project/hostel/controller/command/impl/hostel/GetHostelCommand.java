package by.epam.project.hostel.controller.command.impl.hostel;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.controller.command.impl.user.EditUserCommand;
import by.epam.project.hostel.controller.constant.Constant;
import by.epam.project.hostel.entity.Hostel;
import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.project.hostel.controller.constant.Constant.Page.LOCAL;

public class GetHostelCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(EditUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter(Constant.Hostel.ID));
        String language = (String) request.getSession().getAttribute(LOCAL);

        try {
            Hostel hostel = ServiceFactory.getInstance().getHostelService().getHostelById(id, language);
            request.setAttribute(Constant.Hostel.HOSTEL, hostel);
        } catch (ValidationException e) {
            LOGGER.error("error during getting hostel", e);
        }


    }
}
