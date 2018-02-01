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

import static by.epam.project.hostel.controller.constant.Constant.Hostel.ADDRESS_EN;
import static by.epam.project.hostel.controller.constant.Constant.Hostel.ADDRESS_RU;
import static by.epam.project.hostel.controller.constant.Constant.Hostel.CITY_EN;
import static by.epam.project.hostel.controller.constant.Constant.Hostel.CITY_RU;
import static by.epam.project.hostel.controller.constant.Constant.Hostel.COUNTRY_EN;
import static by.epam.project.hostel.controller.constant.Constant.Hostel.COUNTRY_RU;
import static by.epam.project.hostel.controller.constant.Constant.Hostel.DESCRIPTION_EN;
import static by.epam.project.hostel.controller.constant.Constant.Hostel.DESCRIPTION_RU;
import static by.epam.project.hostel.controller.constant.Constant.Hostel.NAME;
import static by.epam.project.hostel.controller.constant.Constant.Hostel.STARS;
import static by.epam.project.hostel.controller.constant.Constant.Language.EN;
import static by.epam.project.hostel.controller.constant.Constant.Language.RU;

public class AddHostelCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddHostelCommand.class);

    private static final String FILE = "file";
    private static final String PICTURE_UPLOAD_PATH = "/img/hotel/";
    private static final String IMAGE_MIME_TYPE = "image/";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(NAME);
        Integer stars = Integer.valueOf(request.getParameter(STARS));
        String imgPath = getImgPath(request);

        String countryRu = request.getParameter(COUNTRY_RU);
        String countryEn = request.getParameter(COUNTRY_EN);
        String cityRu = request.getParameter(CITY_RU);
        String cityEn = request.getParameter(CITY_EN);
        String descriptionEn = request.getParameter(DESCRIPTION_EN);
        String descriptionRu = request.getParameter(DESCRIPTION_RU);
        String addressRu = request.getParameter(ADDRESS_RU);
        String addressEn = request.getParameter(ADDRESS_EN);

        Map<String, Hostel> hostel = new HashMap<>();
        Hostel hostelRu = new Hostel(stars, name, countryRu, cityRu, descriptionRu, imgPath, addressRu);
        Hostel hostelEn = new Hostel(stars, name, countryEn, cityEn, descriptionEn, imgPath, addressEn);
        hostel.put(RU, hostelRu);
        hostel.put(EN, hostelEn);

        try {
            ServiceFactory.getInstance().getHostelService().addHostel(hostel);
            response.sendRedirect("/admin/admin_hostels");
        } catch (ServiceException e) {
            logger.error("error during adding hostel", e);
        }

    }


    private String getImgPath(HttpServletRequest request) throws IOException, ServletException {
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
        return filename.isEmpty() ? null : PICTURE_UPLOAD_PATH + filename;
    }
}
