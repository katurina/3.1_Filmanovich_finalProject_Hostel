package by.epam.project.hostel.service.impl;

import by.epam.project.hostel.dao.CommentDAO;
import by.epam.project.hostel.dao.DAOFactory;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.service.CommentService;
import by.epam.project.hostel.service.exception.ServiceException;

public class CommentServiceImpl implements CommentService {

    private static final CommentDAO commentDAO = DAOFactory.getInstance().getCommentDAO();

    @Override
    public int getTotalRowCount() throws ServiceException {
        try {
            return commentDAO.getTotalRowCount();
        } catch (DAOException e) {
            throw new ServiceException("error during getting comment's row count",e);
        }
    }
}
