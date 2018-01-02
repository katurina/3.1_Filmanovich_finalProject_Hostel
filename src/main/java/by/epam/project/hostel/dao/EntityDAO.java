package by.epam.project.hostel.dao;

import by.epam.project.hostel.dao.exception.DAOException;

public interface EntityDAO {
    int getTotalRowCount() throws DAOException;
}
