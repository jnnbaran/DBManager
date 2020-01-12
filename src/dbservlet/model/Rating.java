package dbservlet.model;

public class Rating {

    private int ratingId;
    private int userId;
    private int answerId;
    private int rating;

    public Rating(int ratingId, int userId, int answerId, int rating) {
        this.ratingId = ratingId;
        this.userId = userId;
        this.answerId = answerId;
        this.rating = rating;
    }
    public Rating(int userId, int answerId, int rating) {
        this.userId = userId;
        this.answerId = answerId;
        this.rating = rating;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
