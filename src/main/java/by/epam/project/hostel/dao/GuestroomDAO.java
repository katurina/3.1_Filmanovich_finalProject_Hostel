package by.epam.project.hostel.dao;

import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Guestroom;
import by.epam.project.hostel.entity.search.SearchGuestroomsParams;

import java.util.List;

public interface GuestroomDAO extends EntityDAO {
    List<Guestroom> getGuestroomsByHostelId(int id, String language, int page) throws DAOException;

    Guestroom getGuestroomById(int id, String language) throws DAOException;

    List<Guestroom> getGuestroomBySearchParam(int currentPage, SearchGuestroomsParams searchParams, String language) throws DAOException;

    Integer getTotalRowCount(SearchGuestroomsParams searchParams, String language) throws DAOException;

    void deleteGuestroomByIdTransaction(Integer guestroomId) throws DAOException;

    void addImage(Integer guestroomId, String imgPath) throws DAOException;

    void deleteImage(String imgPath) throws DAOException;

    List<Guestroom> getGuestrooms(String language, Integer currentPage) throws DAOException;

    int addGuestroomWithTransaction(Guestroom guestroom) throws DAOException;

    void addImageWithTransaction(int guestroomId, List<String> imgPath) throws DAOException;

    void addDescriptionWithTransaction(int guestroomId, String descriptionEn, String descriptionRu) throws DAOException;

    void deleteImagesByGuestroomIdTransaction(Integer guestroomId) throws DAOException;

    void deleteDescriptionsByGuestroomIdTransaction(Integer guestroomId) throws DAOException;

    void deleteGuestroomsDescriptionByHostelIdTransaction(Integer hostelId) throws DAOException;

    void deleteGuestroomsPicturesByHostelIdTransaction(Integer hostelId) throws DAOException;

    void deleteGuestroomsByHostelIdTransaction(Integer hostelId) throws DAOException;

    List<Guestroom> getGuestroomsByHostelName(String hostelName, Integer currentPage) throws DAOException;

    Integer getTotalRowCount(String hostelName) throws DAOException;

    void editGuestroomDescriptionsWithTransaction(int guestroomId, String descriptionEn, String descriptionRu) throws DAOException;

    void editGuestroomWithTransaction(Guestroom guestroom) throws DAOException;

    String getGuestroomDescription(int guestroomId, String language) throws DAOException;
}
