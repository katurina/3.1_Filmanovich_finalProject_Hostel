package by.epam.project.hostel.controller.command.impl.guestroom;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.controller.constant.Constant;
import by.epam.project.hostel.controller.pagination.PageWrapper;
import by.epam.project.hostel.entity.Guestroom;
import by.epam.project.hostel.entity.pagination.Page;
import by.epam.project.hostel.entity.search.SearchGuestroomsParams;
import by.epam.project.hostel.service.GuestroomService;
import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static by.epam.project.hostel.controller.constant.Constant.Page.CURRENT_PAGE;
import static by.epam.project.hostel.controller.constant.Constant.Page.LOCAL;
import static by.epam.project.hostel.controller.constant.Constant.Page.PAGE;

public class GetRequiredGuestroomsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GetRequiredGuestroomsCommand.class);
    private static final GuestroomService guestroomService = ServiceFactory.getInstance().getGuestroomService();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = (String) request.getSession().getAttribute(LOCAL);
        String pageParam = request.getParameter(CURRENT_PAGE);
        int currentPage = pageParam == null || pageParam.isEmpty() ? 1 : Integer.valueOf(pageParam);
        SearchGuestroomsParams searchParamsEntity = getSearchParamsEntity(request);

        try {
            List<Guestroom> searchedGuestrooms = guestroomService.getGuestroomBySearchParam(currentPage, searchParamsEntity, language);
            Page<Guestroom> page = PageWrapper.wrapList(searchedGuestrooms, currentPage, guestroomService.getTotalRowCount(searchParamsEntity,language));
            request.setAttribute(PAGE, page);
        } catch (ServiceException e) {
            logger.error("error during getting required guestrooms by search params", e);
        }

    }

    private SearchGuestroomsParams getSearchParamsEntity(HttpServletRequest request) {

        SearchGuestroomsParams searchParams = new SearchGuestroomsParams();

        String wifiParam = request.getParameter(Constant.SearchParams.WIFI);
        String tvParam = request.getParameter(Constant.SearchParams.TV);
        String showerParam = request.getParameter(Constant.SearchParams.SHOWER);
        String sortingParam = request.getParameter(Constant.Guestroom.SORT);
        String nightPriceFrom = request.getParameter(Constant.SearchParams.NIGHT_PRICE_FROM);
        String nightPriceTo = request.getParameter(Constant.SearchParams.NIGHT_PRICE_TO);
        String dateFromParam = request.getParameter(Constant.SearchParams.DATE_FROM);
        String dateToParam = request.getParameter(Constant.SearchParams.DATE_TO);
        String capacityFrom = request.getParameter(Constant.SearchParams.CAPACITY_FROM);
        String capacityTo = request.getParameter(Constant.SearchParams.CAPACITY_TO);
        String city = request.getParameter(Constant.SearchParams.CITY);


        if (nightPriceFrom != null && !nightPriceFrom.isEmpty()) {
            searchParams.setNightPriceFrom(BigDecimal.valueOf(Double.valueOf(nightPriceFrom)));
        }
        if (nightPriceTo != null && !nightPriceTo.isEmpty()) {
            searchParams.setNightPriceTo(BigDecimal.valueOf(Double.valueOf(nightPriceTo)));
        }
        if (sortingParam != null && !sortingParam.isEmpty()) {
            searchParams.setSorting(sortingParam);
        }
        if (dateFromParam != null && !dateFromParam.isEmpty()) {
            searchParams.setDateFrom(LocalDate.parse(dateFromParam));
        }
        if (dateFromParam != null && !dateToParam.isEmpty()) {
            searchParams.setDateFrom(LocalDate.parse(dateToParam));
        }
        if (wifiParam != null) {
            searchParams.setWifi(!wifiParam.isEmpty());
        }
        if (tvParam != null) {
            searchParams.setTv(!tvParam.isEmpty());
        }
        if (showerParam != null) {
            searchParams.setShower(!showerParam.isEmpty());
        }

        if (capacityFrom != null && !capacityFrom.isEmpty()) {
            searchParams.setCapacityFrom(Integer.valueOf(capacityFrom));
        }
        if (capacityTo != null && !capacityTo.isEmpty()) {
            searchParams.setCapacityTo(Integer.valueOf(capacityTo));
        }
        if (city != null && !city.isEmpty()) {
            searchParams.setCity(city);
        }
        return searchParams;
    }
}