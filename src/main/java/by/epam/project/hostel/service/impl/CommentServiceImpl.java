package by.epam.project.hostel.service.impl;

import by.epam.project.hostel.dao.CommentDAO;
import by.epam.project.hostel.dao.DAOFactory;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Comment;
import by.epam.project.hostel.service.CommentService;
import by.epam.project.hostel.service.exception.ServiceException;
import by.epam.project.hostel.service.validation.Validator;
import by.epam.project.hostel.service.validation.impl.CommentValidatorImpl;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private static final CommentDAO commentDAO = DAOFactory.getInstance().getCommentDAO();
    private static final Validator<Comment> validator = new CommentValidatorImpl();


    @Override
    public int getTotalRowCount() throws ServiceException {
        try {
            return commentDAO.getTotalRowCount();
        } catch (DAOException e) {
            throw new ServiceException("error during getting comment's row count", e);
        }
    }

    @Override
    public List<Comment> getCommentsByRoomId(Integer guestroomId) throws ServiceException {
        validator.validateID(guestroomId);
        try {
            return commentDAO.getCommentsByRoomId(guestroomId);
        } catch (DAOException e) {
            throw new ServiceException("error during getting comments by room id", e);
        }
    }
}
