package by.epam.project.hostel.service.impl;

import by.epam.project.hostel.dao.DAOFactory;
import by.epam.project.hostel.dao.GuestroomDAO;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Guestroom;
import by.epam.project.hostel.entity.search.SearchGuestroomsParams;
import by.epam.project.hostel.service.GuestroomService;
import by.epam.project.hostel.service.exception.ServiceException;
import by.epam.project.hostel.service.validation.Validator;
import by.epam.project.hostel.service.validation.impl.GuestroomValidatorImpl;
import by.epam.project.hostel.service.validation.impl.SearchParamsValidatorImpl;

import java.util.List;

public class GuestroomServiceImpl implements GuestroomService {

    private static final GuestroomDAO guestroomDAO = DAOFactory.getInstance().getGuestroomDAO();
    private static final Validator<Guestroom> validator = new GuestroomValidatorImpl();
    private static final Validator<SearchGuestroomsParams> validateSearchParams = new SearchParamsValidatorImpl();

    @Override
    public Guestroom getGuestroomById(int id, String language) throws ServiceException {
        validator.validateID(id);
        validator.validate(language);
        try {
            return guestroomDAO.getGuestRoomById(id, language);
        } catch (DAOException e) {
            throw new ServiceException("error during getting guestroom by id = " + id, e);
        }
    }

    @Override
    public List<Guestroom> getGuestroomBySearchParam(int currentPage, SearchGuestroomsParams searchParams, String language) throws ServiceException {
        validator.validate(language);
        validator.validateCurrentPage(currentPage);
        validateSearchParams.validate(searchParams);
        try {
            return guestroomDAO.getGuestroomBySearchParam(currentPage, searchParams, language);
        } catch (DAOException e) {
            throw new ServiceException("error during getting guestrooms by search params", e);
        }
    }

    @Override
    public int getTotalRowCount() throws ServiceException {
        try {
            return guestroomDAO.getTotalRowCount();
        } catch (DAOException e) {
            throw new ServiceException("error during getting guestroom's total row count", e);
        }
    }

    @Override
    public Integer getTotalRowCount(SearchGuestroomsParams searchParams, String language) throws ServiceException {
        validateSearchParams.validate(searchParams);
        try {
            return guestroomDAO.getTotalRowCount(searchParams, language);
        } catch (DAOException e) {
            throw new ServiceException("error during getting total row count", e);
        }
    }

    @Override
    public void deleteGuestroomById(Integer guestroomId) throws ServiceException {
        validator.validateID(guestroomId);
        try {
            guestroomDAO.deleteGuestroomById(guestroomId);
        } catch (DAOException e) {
            throw new ServiceException("error during delete guestroom by id", e);
        }
    }

    @Override
    public void addGuestroom(Guestroom guestroom, String descriptionEn, String descriptionRu) throws ServiceException {
        validator.validate(descriptionEn, descriptionRu);
        validator.validate(guestroom);
        try {
            guestroomDAO.addGuestroom(guestroom, descriptionEn, descriptionRu);
        } catch (DAOException e) {
            throw new ServiceException("error during adding guestroom", e);
        }
    }

    @Override
    public void addImage(Integer guestroomId, String imgPath) throws ServiceException {
        validator.validateID(guestroomId);
        validator.validate(imgPath);
        try {
            guestroomDAO.addImage(guestroomId, imgPath);
        } catch (DAOException e) {
            throw new ServiceException("error during adding image", e);
        }
    }
}
