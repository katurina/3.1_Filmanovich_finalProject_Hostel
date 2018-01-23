package by.epam.project.hostel.dao;

import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Comment;

import java.util.List;

public interface CommentDAO extends EntityDAO{
    List<Comment> getCommentsByRoomId(Integer guestroomId) throws DAOException;
}
