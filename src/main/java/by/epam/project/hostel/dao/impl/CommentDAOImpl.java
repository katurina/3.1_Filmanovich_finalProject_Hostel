package by.epam.project.hostel.dao.impl;

import by.epam.project.hostel.dao.CommentDAO;
import by.epam.project.hostel.dao.db.connection.ConnectionProvider;
import by.epam.project.hostel.dao.exception.ConnectionPoolException;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommentDAOImpl extends EntityDAOImpl implements CommentDAO {
    private static final ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

    @Override
    public List<Comment> getCommentsByRoomId(Integer guestroomId) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT id,comment,date,rate FROM comments WHERE guestrooms_id =?;")) {
            ps.setInt(1, guestroomId);
            try (ResultSet rs = ps.executeQuery()) {
                List<Comment> comments = new ArrayList<>();
                while (rs.next()) {
                    comments.add(createComment(rs));
                }
                return comments;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during getting comments by room id = " + guestroomId, e);
        }
    }


    /**
     * @return
     */
    @Override
    protected String getTableName() {
        return "comments";
    }

    private Comment createComment(ResultSet rs) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getInt(1));
        comment.setComment(rs.getString(2));
        comment.setCommentDate(LocalDate.parse(rs.getString(3)));
        comment.setRate(rs.getInt(4));
        return comment;
    }
}
