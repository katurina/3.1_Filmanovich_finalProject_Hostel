package by.epam.project.hostel.service;

import by.epam.project.hostel.entity.Guestroom;
import by.epam.project.hostel.service.exception.ServiceException;

public interface GuestroomService extends EntityService {

    Guestroom getGuestroomById(int id, String language) throws ServiceException;
}
