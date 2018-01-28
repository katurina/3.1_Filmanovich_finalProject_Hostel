package by.epam.project.hostel.controller.command.impl.hostel;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.controller.command.impl.guestroom.DeleteGuestroomByIdCommand;
import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.project.hostel.controller.constant.Constant.Hostel.ID;

public class DeleteHostelByIdCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DeleteGuestroomByIdCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer hostelId = Integer.valueOf(request.getParameter(ID));
        try {
            ServiceFactory.getInstance().getHostelService().deleteHostelById(hostelId);
            response.sendRedirect("/admin/admin_hostels");
        } catch (ServiceException e) {
            response.sendRedirect("/admin/admin_hostels");
            logger.error("error during delete hostel by id", e);
        }
    }
}
