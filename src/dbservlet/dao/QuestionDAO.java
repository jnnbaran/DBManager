package dbservlet.dao;

import dbservlet.model.Question;
import dbservlet.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionDAO {
    private DataSource dataSource;

    public QuestionDAO(DataSource theDataSource) {

        dataSource = theDataSource;
    }

    public List<Question> getSelectQuestions(int categoryId) throws Exception, SQLException {


        List<Question> selectQuestions = new ArrayList<>();
        String join = "select * from Questions join Users U on Questions.UserId = U.UserId WHERE Questions.CategoryId = ?";

        try (
                // get a connection
                Connection myConn = dataSource.getConnection();
                PreparedStatement myStmt = myConn.prepareStatement(join)
        ) {
            myStmt.setInt(1, categoryId);
            ResultSet myRs = myStmt.executeQuery();


            while (myRs.next()) {

                // retrieve data from result set row
                int QuestionId = myRs.getInt("QuestionId");
                Date Date = myRs.getDate("Date");
                int UserId = myRs.getInt("UserId");
                String Title = myRs.getString("Title");
                String Question = myRs.getString("Question");
                int CategoryId = myRs.getInt("CategoryId");

                int userId = myRs.getInt("UserId");
                String userName = myRs.getString("UserName");
                int roleId = myRs.getInt("RoleId");
                String password = myRs.getString("Password");

                User tempUser = new User(userId, userName, roleId, password);
                Question tempQuestion = new Question(QuestionId, (java.sql.Date) Date, UserId, Title, Question, CategoryId, tempUser);

                selectQuestions.add(tempQuestion);
            }

            return selectQuestions;
        }
    }


    public List<Question> getQuestion() throws Exception {

        List<Question> questions = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            myConn = dataSource.getConnection();


            String sql = "select * from Questions";
            String join = "select * from Questions join Users U on Questions.UserId = U.UserId";

            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery(join);

            while (myRs.next()) {

                // retrieve data from result set row
                int QuestionId = myRs.getInt("QuestionId");
                Date Date = myRs.getDate("Date");
                int UserId = myRs.getInt("UserId");
                String Title = myRs.getString("Title");
                String Question = myRs.getString("Question");
                int CategoryId = myRs.getInt("CategoryId");

                int userId = myRs.getInt("UserId");
                String userName = myRs.getString("UserName");
                int roleId = myRs.getInt("RoleId");
                String password = myRs.getString("Password");

                User tempUser = new User(userId, userName, roleId, password);
                Question tempQuestion = new Question(QuestionId, (java.sql.Date) Date, UserId, Title, Question, CategoryId, tempUser);

                questions.add(tempQuestion);
            }

            return questions;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

        try {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }


    public Question getQuestion(String theQuestionId) throws Exception {

        Question theQuestion = null;

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int questionId;

        try {
            questionId = Integer.parseInt(theQuestionId);
            myConn = dataSource.getConnection();
            String sql = "select * from Questions where QuestionId=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, questionId);
            myRs = myStmt.executeQuery();

            if (myRs.next()) {
                Date Date = myRs.getDate("Date");
                int UserId = myRs.getInt("UserId");
                String Title = myRs.getString("Title");
                String Question = myRs.getString("Question");
                int CategoryId = myRs.getInt("CategoryId");

                theQuestion = new Question(questionId, (java.sql.Date) Date, UserId, Title, Question, CategoryId);
            } else {
                throw new Exception("Could not find qestion id: " + questionId);
            }

            return theQuestion;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }


    public void addQuestion(Question theQuestion) throws SQLException {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();

            String sql = "insert into Questions "
                    + "(Date , UserId, Title, Question, CategoryId) "
                    + "values (?, ?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setDate(1, (java.sql.Date) theQuestion.getDate());
            myStmt.setInt(2, theQuestion.getUserId());
            myStmt.setString(3, theQuestion.getTitle());
            myStmt.setString(4, theQuestion.getQuestion());
            myStmt.setInt(5, theQuestion.getCategoryId());

            myStmt.execute();
        } finally {
            close(myConn, myStmt, null);
        }


    }

    public List<Question> getQuestionForUser(int userId) {


        List<Question> questions = new ArrayList<>();

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();

            String join = "select * from Questions join Users U on Questions.UserId = U.UserId where Questions.UserId=?";
            myStmt = myConn.prepareStatement(join);

            myStmt.setInt(1, userId);


            myRs = myStmt.executeQuery();

            while (myRs.next()) {

                // retrieve data from result set row
                int QuestionId = myRs.getInt("QuestionId");
                Date Date = myRs.getDate("Date");
                int UserId = myRs.getInt("UserId");
                String Title = myRs.getString("Title");
                String Question = myRs.getString("Question");
                int CategoryId = myRs.getInt("CategoryId");
                String userName = myRs.getString("UserName");
                int roleId = myRs.getInt("RoleId");
                String password = myRs.getString("Password");

                User tempUser = new User(userId, userName, roleId, password);
                Question tempQuestion = new Question(QuestionId, (java.sql.Date) Date, UserId, Title, Question, CategoryId, tempUser);

                questions.add(tempQuestion);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(myConn, myStmt, myRs);
        }


        return questions;
    }
}
