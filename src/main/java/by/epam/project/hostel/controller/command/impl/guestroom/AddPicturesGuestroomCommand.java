package by.epam.project.hostel.controller.command.impl.guestroom;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static by.epam.project.hostel.controller.constant.Constant.Guestroom.ID;

public class AddPicturesGuestroomCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddPicturesGuestroomCommand.class);

    private static final String FILE = "file";
    private static final String PICTURE_UPLOAD_PATH = "/img/guestroom/";
    private static final String IMAGE_MIME_TYPE = "image/";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer guestroomId = Integer.valueOf(request.getParameter(ID));
        Part filePart = request.getPart(FILE);
        String filename = filePart.getSubmittedFileName();
        if (!filename.isEmpty()) {
            String mimeType = request.getServletContext().getMimeType(filename);
            if (mimeType.startsWith(IMAGE_MIME_TYPE)) {
                File uploads = new File(request.getServletContext().getRealPath("") + PICTURE_UPLOAD_PATH);
                File file = new File(uploads, filename);
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
        String imgPath = PICTURE_UPLOAD_PATH + filename;
        try {
            ServiceFactory.getInstance().getGuestroomService().addImage(guestroomId, imgPath);
        } catch (ServiceException e) {
            logger.error("error during adding image ", e);
        }
    }
}
