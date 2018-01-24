package by.epam.project.hostel.dao.impl;

import by.epam.project.hostel.dao.CommentDAO;
import by.epam.project.hostel.dao.db.connection.ConnectionProvider;
import by.epam.project.hostel.dao.exception.ConnectionPoolException;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Comment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommentDAOImpl extends EntityDAOImpl implements CommentDAO {
    private static final ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

    private static final String SELECT_COMMENTS_BY_ROOM_ID = "SELECT id,comment,date,rate FROM comments WHERE guestrooms_id =?;";
    private static final String SELECT_COMMENTS_BY_HOSTEL_ID = "SELECT   comments.id,  comment,  date,  rate FROM comments  INNER JOIN guestrooms g ON comments.guestrooms_id = g.id  INNER JOIN hostel h ON g.hostel_id = h.id WHERE h.id = ?";
    private static final String INSERT_COMMENT = "INSERT comments(guestrooms_id, user_id, comment, date,rate) VALUES (?,?,?,?,?);";

    @Override
    public List<Comment> getCommentsByRoomId(Integer guestroomId) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_COMMENTS_BY_ROOM_ID)) {
            ps.setInt(1, guestroomId);
            return createCommentsList(ps);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during getting comments by room id = " + guestroomId, e);
        }
    }

    @Override
    public List<Comment> getCommentsByHostelId(Integer hostelId) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_COMMENTS_BY_HOSTEL_ID)) {
            ps.setInt(1, hostelId);
            return createCommentsList(ps);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during getting comments by hostel id = " + hostelId, e);
        }
    }

    @Override
    public void addComment(Comment comment) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_COMMENT)) {
            ps.setInt(1, comment.getGuestroomId());
            ps.setInt(2, comment.getUserId());
            ps.setString(3, comment.getComment());
            ps.setDate(4, Date.valueOf(comment.getCommentDate()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error during inserting comment in db");
        }
    }

    /**
     * @return
     */
    @Override
    protected String getTableName() {
        return "comments";
    }

    private List<Comment> createCommentsList(PreparedStatement ps) throws SQLException {
        try (ResultSet rs = ps.executeQuery()) {
            List<Comment> comments = new ArrayList<>();
            while (rs.next()) {
                comments.add(createComment(rs));
            }
            return comments;
        }
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
