package by.epam.project.hostel.service;

import by.epam.project.hostel.entity.Guestroom;
import by.epam.project.hostel.entity.search.SearchGuestroomsParams;
import by.epam.project.hostel.service.exception.ServiceException;

import java.util.List;

public interface GuestroomService extends EntityService {

    Guestroom getGuestroomById(int id, String language) throws ServiceException;

    List<Guestroom> getGuestroomBySearchParam(int currentPage, SearchGuestroomsParams searchParams, String language) throws ServiceException;

    Integer getTotalRowCount(SearchGuestroomsParams searchParams, String language) throws ServiceException;

    void deleteGuestroomById(Integer guestroomId) throws ServiceException;

    void addGuestroom(Guestroom guestroom, String descriptionEn, String descriptionRu) throws ServiceException;
}
