package by.epam.project.hostel.entity;

import java.util.Date;

public class Comment {
    private int id;
    private int userId;
    private int guestroomId;
    private String comment;
    private Date commentDate;
    private int rate;

    public Comment() {
    }

    public Comment(int id, int userId, int guestroomId, String comment, Date commentDate, int rate) {
        this.id = id;
        this.userId = userId;
        this.guestroomId = guestroomId;
        this.comment = comment;
        this.commentDate = commentDate;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGuestroomId() {
        return guestroomId;
    }

    public void setGuestroomId(int guestroomId) {
        this.guestroomId = guestroomId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Comment comment1 = (Comment) o;

        if (getId() != comment1.getId()) {
            return false;
        }
        if (getUserId() != comment1.getUserId()) {
            return false;
        }
        if (getGuestroomId() != comment1.getGuestroomId()) {
            return false;
        }
        if (getRate() != comment1.getRate()) {
            return false;
        }
        if (getComment() != null ? !getComment().equals(comment1.getComment()) : comment1.getComment() != null) {
            return false;
        }
        return getCommentDate() != null ? getCommentDate().equals(comment1.getCommentDate()) : comment1.getCommentDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getUserId();
        result = 31 * result + getGuestroomId();
        result = 31 * result + (getComment() != null ? getComment().hashCode() : 0);
        result = 31 * result + (getCommentDate() != null ? getCommentDate().hashCode() : 0);
        result = 31 * result + getRate();
        return result;
    }
}
