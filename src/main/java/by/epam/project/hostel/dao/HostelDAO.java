package by.epam.project.hostel.dao;

import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Hostel;

public interface HostelDAO extends EntityDAO {
    Hostel getHostelById(int id, String language) throws DAOException;
}
