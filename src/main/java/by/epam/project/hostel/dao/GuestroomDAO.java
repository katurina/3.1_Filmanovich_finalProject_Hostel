package by.epam.project.hostel.dao;

import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Guestroom;

import java.util.List;

public interface GuestroomDAO extends EntityDAO {
    List<Guestroom> getGuestroomsByHostelId(int id, String language, int page) throws DAOException;

    Guestroom getGuestRoomById(int id, String language) throws DAOException;
}
