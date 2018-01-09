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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static by.epam.project.hostel.controller.constant.Constant.Page.CURRENT_PAGE;
import static by.epam.project.hostel.controller.constant.Constant.Page.LOCAL;
import static by.epam.project.hostel.controller.constant.Constant.Page.PAGE;

public class GetRequiredGuestroomsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GetRequiredGuestroomsCommand.class);
    private static final GuestroomService guestroomService = ServiceFactory.getInstance().getGuestroomService();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//todo getting desired rooms
        String language = (String) request.getSession().getAttribute(LOCAL);
        String pageParam = request.getParameter(CURRENT_PAGE);
        int currentPage = pageParam == null ? 1 : Integer.valueOf(pageParam);
        SearchGuestroomsParams searchParamsEntity = getSearchParamsEntity(request);

        try {
            List<Guestroom> searchedGuestrooms = guestroomService.getGuestroomBySearchParam(currentPage, searchParamsEntity, language);
            Page<Guestroom> page = PageWrapper.wrapList(searchedGuestrooms, currentPage, guestroomService.getTotalRowCount());
            request.setAttribute(PAGE, page);
        } catch (ServiceException e) {
            e.printStackTrace();
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

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        searchParams.setNightPriceFrom(BigDecimal.valueOf(Double.valueOf(nightPriceFrom)));
        searchParams.setNightPriceTo(BigDecimal.valueOf(Double.valueOf(nightPriceTo)));
        searchParams.setSorting(sortingParam);
        try {
            searchParams.setDateFrom(dateFromParam.isEmpty() ? null : formatter.parse(dateFromParam));
            searchParams.setDateFrom(dateToParam.isEmpty() ? null : formatter.parse(dateToParam));
        } catch (ParseException e) {
            logger.error("error during parsing date" + dateFromParam + dateToParam);
        }
        searchParams.setWifi(wifiParam.isEmpty() ? null : true);
        searchParams.setTv(tvParam.isEmpty() ? null : true);
        searchParams.setShower(showerParam.isEmpty() ? null : true);
        return searchParams;
    }
}
