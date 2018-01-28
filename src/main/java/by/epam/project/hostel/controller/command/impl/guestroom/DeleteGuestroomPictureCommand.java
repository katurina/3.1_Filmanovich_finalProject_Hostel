package by.epam.project.hostel.controller.command.impl.guestroom;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.project.hostel.controller.constant.Constant.Guestroom.ID;

public class DeleteGuestroomPictureCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DeleteGuestroomPictureCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer imgId = Integer.valueOf(request.getParameter(ID));
        try {
            ServiceFactory.getInstance().getGuestroomService().deleteImageById(imgId);
        } catch (ServiceException e) {
            logger.error("error during delete image", e);
        }
    }
}
