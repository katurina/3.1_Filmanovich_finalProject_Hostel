package by.epam.project.hostel.dao;

import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Guestroom;
import by.epam.project.hostel.entity.pagination.Page;

public interface GuestroomDAO extends EntityDAO{
    Page<Guestroom> getGuestroomsByHostelId(int id, String language,int page) throws DAOException;
}
