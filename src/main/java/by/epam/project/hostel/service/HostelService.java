package by.epam.project.hostel.service;

import by.epam.project.hostel.entity.Hostel;
import by.epam.project.hostel.service.exception.ServiceException;
import by.epam.project.hostel.service.exception.ValidationException;

import java.util.List;

public interface HostelService  extends EntityService {
    Hostel getHostelById(int id, String language) throws ValidationException;

    List<Hostel> getHostels(String language) throws ServiceException;

    void deleteHostelById(Integer hostelId) throws ServiceException;
}
