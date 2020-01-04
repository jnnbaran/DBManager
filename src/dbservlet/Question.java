package dbservlet;


import java.util.Date;

public class Question {

    private int questionId;
    private java.sql.Date date;
    private int userId;
    private String title;
    private String question;
    private int categoryId;


    public Question(int questionId, java.sql.Date date, int userId, String title, String question, int categoryId) {
        this.questionId = questionId;
        this.date = date;
        this.userId = userId;
        this.title = title;
        this.question = question;
        this.categoryId = categoryId;
    }


    public Question(java.sql.Date date, int userId, String title, String question, int categoryId) {
        this.date = date;
        this.userId = userId;
        this.title = title;
        this.question = question;
        this.categoryId = categoryId;
    }


    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", date='" + date + '\'' +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", question='" + question + '\'' +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }
}
