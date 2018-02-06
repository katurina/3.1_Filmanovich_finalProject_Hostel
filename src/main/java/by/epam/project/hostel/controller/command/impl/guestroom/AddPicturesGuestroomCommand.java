package by.epam.project.hostel.controller.command.impl.guestroom;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.controller.img.loader.ImgLoader;
import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.project.hostel.controller.constant.Constant.Guestroom.ID;
import static by.epam.project.hostel.controller.constant.Constant.MESSAGE;

public class AddPicturesGuestroomCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddPicturesGuestroomCommand.class);


    private static final String PICTURE_UPLOAD_PATH = "/img/guestroom/";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer guestroomId = Integer.valueOf(request.getParameter(ID));
        String imgPath = ImgLoader.getImgPath(request, PICTURE_UPLOAD_PATH);
        try {
            ServiceFactory.getInstance().getGuestroomService().addImage(guestroomId, imgPath);
            response.sendRedirect("/admin/edit_guestroom?id=" + guestroomId);
        } catch (ServiceException e) {
            request.setAttribute(MESSAGE, "local.error.during.adding.image");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            logger.error("error during adding image ", e);
        }
    }
}
