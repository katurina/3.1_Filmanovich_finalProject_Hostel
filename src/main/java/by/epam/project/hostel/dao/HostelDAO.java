package by.epam.project.hostel.dao;

import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Hostel;

import java.util.List;
import java.util.Map;

public interface HostelDAO extends EntityDAO {
    Hostel getHostelById(int id, String language) throws DAOException;

    Hostel getHostelByRoomId(int id, String language) throws DAOException;

    List<Hostel> getHostels(String language) throws DAOException;

    void deleteHostelById(Integer hostelId) throws DAOException;

    int addHostelTransaction(Map<String, Hostel> hostel) throws DAOException;

    void addHostelLanguageParamsTransaction(Map<String, Hostel> hostel, int hostelId) throws DAOException;

    void deleteHostelDescriptionTransaction(Integer hostelId) throws DAOException;
}
