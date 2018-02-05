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
import javax.servlet.http.Part;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static by.epam.project.hostel.controller.constant.Constant.Guestroom.ID;

public class AddPicturesGuestroomCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddPicturesGuestroomCommand.class);

    private static final String FILE = "file";
    private static final String PICTURE_UPLOAD_PATH = "/img/guestroom/";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer guestroomId = Integer.valueOf(request.getParameter(ID));
        Part filePart = request.getPart(FILE);
        String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_hhmmss")) + filePart.getSubmittedFileName();
        String mimeType = request.getServletContext().getMimeType(filename);
        String pathname = request.getServletContext().getRealPath("") + PICTURE_UPLOAD_PATH;
        ImgLoader.loadImageJpg(filePart, filename, mimeType, pathname);
        String imgPath = PICTURE_UPLOAD_PATH + filename;
        try {
            ServiceFactory.getInstance().getGuestroomService().addImage(guestroomId, imgPath);
            response.sendRedirect("/admin/edit_guestroom?id=" + guestroomId);
        } catch (ServiceException e) {
            logger.error("error during adding image ", e);
        }
    }
}
