package by.epam.project.hostel.controller.command.impl.guestroom;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.entity.Guestroom;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

import static by.epam.project.hostel.controller.constant.Constant.Guestroom.BATH;
import static by.epam.project.hostel.controller.constant.Constant.Guestroom.CAPACITY;
import static by.epam.project.hostel.controller.constant.Constant.Guestroom.DESCRIPTION_EN;
import static by.epam.project.hostel.controller.constant.Constant.Guestroom.DESCRIPTION_RU;
import static by.epam.project.hostel.controller.constant.Constant.Guestroom.HOSTEL_ID;
import static by.epam.project.hostel.controller.constant.Constant.Guestroom.NIGHT_PRICE;
import static by.epam.project.hostel.controller.constant.Constant.Guestroom.TV;
import static by.epam.project.hostel.controller.constant.Constant.Guestroom.WIFI;

public class EditGuestroomCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Guestroom guestroom = createGuestroom(request);
        String descriptionRu = request.getParameter(DESCRIPTION_EN);
        String descriptionEn = request.getParameter(DESCRIPTION_RU);
    }

    private Guestroom createGuestroom(HttpServletRequest request) throws IOException, ServletException {
        Guestroom guestroom = new Guestroom();

        Integer hostelId = Integer.valueOf(request.getParameter(HOSTEL_ID));
        BigDecimal nightPrice = BigDecimal.valueOf(Double.valueOf(request.getParameter(NIGHT_PRICE)));
        String tv = request.getParameter(TV);
        String wifi = request.getParameter(WIFI);
        String bath = request.getParameter(BATH);
        Integer capacity = Integer.valueOf(request.getParameter(CAPACITY));

        guestroom.setHostelId(hostelId);
        guestroom.setTv(tv);
        guestroom.setWifi(wifi);
        guestroom.setBath(bath);
        guestroom.setCapacity(capacity);
        guestroom.setNightPrice(nightPrice);

        return guestroom;
    }
}
