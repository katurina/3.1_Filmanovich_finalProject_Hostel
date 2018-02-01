package by.epam.project.hostel.controller.command.impl.hostel;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.entity.Hostel;
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
import java.util.HashMap;
import java.util.Map;

import static by.epam.project.hostel.controller.constant.Constant.Guestroom.IMG_PATH;
import static by.epam.project.hostel.controller.constant.Constant.Hostel.ID;
import static by.epam.project.hostel.controller.constant.Constant.Hostel.NAME;
import static by.epam.project.hostel.controller.constant.Constant.Hostel.STARS;
import static by.epam.project.hostel.controller.constant.Constant.Language.EN;
import static by.epam.project.hostel.controller.constant.Constant.Language.RU;

public class EditHostelCommand implements Command {

    private static final String FILE = "file";
    private static final String PICTURE_UPLOAD_PATH = "/img/hostel/";
    private static final String IMAGE_MIME_TYPE = "image/";

    private static final Logger logger = LogManager.getLogger(EditHostelCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Map<String, Hostel> hostel = createHostel(request);
            ServiceFactory.getInstance().getHostelService().editHostel(hostel);
            response.sendRedirect("/admin/edit_hostel?id=" + request.getParameter(ID));
        } catch (ServiceException e) {
            logger.error("error during editing hostel ", e);
        }

    }

    private Map<String, Hostel> createHostel(HttpServletRequest request) throws IOException, ServletException {
        Integer id = Integer.valueOf(request.getParameter(ID));
        String name = request.getParameter(NAME);
        Integer stars = Integer.valueOf(request.getParameter(STARS));
        String imagePath = getImagePath(request);
        String countryRu = request.getParameter("countryRu");
        String countryEn = request.getParameter("countryEn");
        String cityRu = request.getParameter("cityRu");
        String cityEn = request.getParameter("cityEn");
        String addressRu = request.getParameter("addressRu");
        String addressEn = request.getParameter("addressEn");
        String descriptionRu = request.getParameter("descriptionRu");
        String descriptionEn = request.getParameter("descriptionRu");
        Map<String, Hostel> hostel = new HashMap<>();
        hostel.put(RU, new Hostel(id, stars, name, countryRu, cityRu, descriptionRu, imagePath, addressRu));
        hostel.put(EN, new Hostel(id, stars, name, countryEn, cityEn, descriptionEn, imagePath, addressEn));
        return hostel;
    }

    private String getImagePath(HttpServletRequest request) throws IOException, ServletException {
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
        return filename.isEmpty() ? request.getParameter(IMG_PATH) : PICTURE_UPLOAD_PATH + filename;
    }
}
