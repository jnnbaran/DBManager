package dbservlet.model;

import java.util.Date;
import java.util.List;

public class Answer {

    private int answerId;
    private Date date;
    private int rating;
    private int negativeRating;
    private Boolean activeUserRating;
    private String Answer;
    private int questionId;
    private int userId;
    private User user;
    private List<Comment> comments;

    public Answer(Date date, String answer, int questionId, Object userId) {
        this.date = date;
        Answer = answer;
        this.questionId = questionId;
        this.userId = (int) userId;
    }

    public Answer(Date date, String answer, int questionId, int userId, User user) {
        this.date = date;
        Answer = answer;
        this.questionId = questionId;
        this.userId = userId;
        this.user = user;
    }

    public Answer(int answerId, Date date, String answer, int questionId, int userId, User user) {
        this.answerId = answerId;
        this.date = date;
        Answer = answer;
        this.questionId = questionId;
        this.userId = userId;
        this.user = user;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNegativeRating() {
        return negativeRating;
    }

    public void setNegativeRating(int negativeRating) {
        this.negativeRating = negativeRating;
    }

    public Boolean getActiveUserRating() {
        return activeUserRating;
    }

    public void setActiveUserRating(Boolean activeUserRating) {
        this.activeUserRating = activeUserRating;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
