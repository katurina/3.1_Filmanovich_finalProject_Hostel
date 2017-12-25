package by.epam.project.hostel.service;

import by.epam.project.hostel.entity.Hostel;
import by.epam.project.hostel.service.exception.ValidationException;

public interface HostelService {
    Hostel getHostelById(int id, String language) throws ValidationException;
}
