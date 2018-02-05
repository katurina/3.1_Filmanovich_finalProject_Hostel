package by.epam.project.hostel.controller.img.loader;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ImgLoader {
    private static final String IMAGE_MIME_TYPE = "image/";

    private ImgLoader() {
    }

    public static void loadImageJpg(Part filePart, String filename, String mimeType, String pathname) throws IOException {
        if (!filename.isEmpty()) {
            if (mimeType.startsWith(IMAGE_MIME_TYPE)) {
                File uploads = new File(pathname);
                File file = new File(uploads, filename);
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }

}
