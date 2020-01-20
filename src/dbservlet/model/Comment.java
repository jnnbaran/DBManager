package dbservlet.model;

import java.util.Date;

public class Comment {
   private int commentId;
   private int answerId;
   private int userId;
   private Date date;
   private String comment;
   private User user;


    public Comment(int commentId, int answerId, int userId, Date date, String comment, User user) {
        this.commentId = commentId;
        this.answerId = answerId;
        this.userId = userId;
        this.date = date;
        this.comment = comment;
        this.user = user;
    }

    public Comment(int userId, int answerId, java.sql.Date date, String comment) {
        this.answerId = answerId;
        this.userId = userId;
        this.date = date;
        this.comment = comment;

    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
