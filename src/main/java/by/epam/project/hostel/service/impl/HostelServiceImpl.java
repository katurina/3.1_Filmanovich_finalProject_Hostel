package by.epam.project.hostel.service.impl;

import by.epam.project.hostel.dao.CommentDAO;
import by.epam.project.hostel.dao.DAOFactory;
import by.epam.project.hostel.dao.GuestroomDAO;
import by.epam.project.hostel.dao.HostelDAO;
import by.epam.project.hostel.dao.db.transaction.Transaction;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Hostel;
import by.epam.project.hostel.service.HostelService;
import by.epam.project.hostel.service.exception.ServiceException;
import by.epam.project.hostel.service.exception.ValidationException;
import by.epam.project.hostel.service.validation.Validator;
import by.epam.project.hostel.service.validation.impl.HostelValidatorImpl;

import java.util.List;
import java.util.Map;

import static by.epam.project.hostel.controller.constant.Constant.Language.EN;
import static by.epam.project.hostel.controller.constant.Constant.Language.RU;

public class HostelServiceImpl extends BaseService implements HostelService {
    private static final Validator<Hostel> validator = new HostelValidatorImpl();
    private static final DAOFactory instance = DAOFactory.getInstance();
    private static final HostelDAO hostelDAO = instance.getHostelDAO();

    @Override
    public Hostel getHostelById(int id, String language) throws ValidationException {
        validator.validateID(id);
        validator.validate(language);
        try {
            return hostelDAO.getHostelById(id, language);
        } catch (DAOException e) {
            throw new ValidationException("error during validation getting hostel by id", e);
        }
    }

    @Override
    public List<Hostel> getHostels(String language) throws ServiceException {
        validator.validate(language);
        try {
            return hostelDAO.getHostels(language);
        } catch (DAOException e) {
            throw new ServiceException("error during getting whole hostels");
        }
    }

    @Override
    public void deleteHostelById(Integer hostelId) throws ServiceException {
        validator.validateID(hostelId);
        try {
            HostelDAO hostelDAO = instance.createHostelDAO();
            GuestroomDAO guestroomDAO = instance.createGuestroomDAO();
            CommentDAO commentDAO = instance.createCommentDAO();
            Transaction transaction = transactionManager.beginTransaction(hostelDAO, guestroomDAO, commentDAO);
            try {
                commentDAO.deleteCommentsByHostelIdTransaction(hostelId);
                guestroomDAO.deleteGuestroomsDescriptionByHostelIdTransaction(hostelId);
                guestroomDAO.deleteGuestroomsPicturesByHostelIdTransaction(hostelId);
                guestroomDAO.deleteGuestroomsByHostelIdTransaction(hostelId);
                hostelDAO.deleteHostelDescriptionTransaction(hostelId);
                hostelDAO.deleteHostelById(hostelId);
                transaction.commit();
            } catch (DAOException e) {
                transaction.rollback();
                throw e;
            } finally {
                transaction.endTransaction();
            }
        } catch (DAOException e) {
            throw new ServiceException("error during delete hostel by id = " + hostelId, e);
        }
    }

    @Override
    public void addHostel(Map<String, Hostel> hostel) throws ServiceException {
        validator.validate(hostel.get(RU));
        validator.validate(hostel.get(EN));
        try {
            HostelDAO hostelDAO = instance.createHostelDAO();
            Transaction transaction = transactionManager.beginTransaction(hostelDAO);
            try {
                int hostelId = hostelDAO.addHostelTransaction(hostel);
                hostelDAO.addHostelLanguageParamsTransaction(hostel, hostelId);
                transaction.commit();
            } catch (DAOException e) {
                transaction.rollback();
                throw e;
            } finally {
                transaction.endTransaction();
            }
        } catch (DAOException e) {
            throw new ServiceException("error during adding new hostel", e);
        }

    }

    @Override
    public int getTotalRowCount() throws ServiceException {
        try {
            return hostelDAO.getTotalRowCount();
        } catch (DAOException e) {
            throw new ServiceException("error during getting hostel's total row count", e);
        }
    }
}
