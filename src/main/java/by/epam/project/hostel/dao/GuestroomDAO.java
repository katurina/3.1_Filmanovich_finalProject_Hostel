package by.epam.project.hostel.dao;

import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Guestroom;
import by.epam.project.hostel.entity.search.SearchGuestroomsParams;

import java.util.List;

public interface GuestroomDAO extends EntityDAO {
    List<Guestroom> getGuestroomsByHostelId(int id, String language, int page) throws DAOException;

    Guestroom getGuestRoomById(int id, String language) throws DAOException;

    List<Guestroom> getGuestroomBySearchParam(int currentPage, SearchGuestroomsParams searchParams, String language) throws DAOException;

    Integer getTotalRowCount(SearchGuestroomsParams searchParams, String language) throws DAOException;

    void deleteGuestroomById(Integer guestroomId) throws DAOException;

    void addGuestroom(Guestroom guestroom, String descriptionEn, String descriptionRu) throws DAOException;
}
