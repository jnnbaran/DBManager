package dbservlet;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionDbUtil {
    private DataSource dataSource;

    public QuestionDbUtil(DataSource theDataSource) {

        dataSource = theDataSource;
    }


    public List<Question> getQuestion() throws Exception, SQLException {

        List<Question> questions = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            myConn = dataSource.getConnection();


            String sql = "select * from Questions";

            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {

                // retrieve data from result set row
                int QuestionId = myRs.getInt("QuestionId");
                Date Date = myRs.getDate("Date");
                int UserId = myRs.getInt("UserId");
                String Title = myRs.getString("Title");
                String Question = myRs.getString("Question");
                int CategoryId = myRs.getInt("CategoryId");


                Question tempQuestion = new Question(QuestionId, (java.sql.Date) Date, UserId,  Title, Question, CategoryId);

                questions.add(tempQuestion);
            }

            return questions;
        }
        finally {
            // close JDBC objects
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
                myConn.close();   // doesn't really close it ... just puts back in connection pool
            }
        }
        catch (Exception exc) {
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
            // convert student id to int
            questionId = Integer.parseInt(theQuestionId);

            // get connection to database
            myConn = dataSource.getConnection();

            // create sql to get selected student
            String sql = "select * from Questions where QuestionId=?";

            // create prepared statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, questionId);

            // execute statement
            myRs = myStmt.executeQuery();

            // retrieve data from result set row
            if (myRs.next()) {

                Date Date = myRs.getDate("Date");
                int UserId = myRs.getInt("UserId");
                String Title = myRs.getString("Title");
                String Question = myRs.getString("Question");
                int CategoryId = myRs.getInt("CategoryId");

                theQuestion = new Question((java.sql.Date) Date, UserId, Title, Question, CategoryId);
            }
            else {
                throw new Exception("Could not find qestion id: " + questionId);
            }

            return theQuestion;
        }
        finally {
            // clean up JDBC objects
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
        }
        finally {
            close(myConn, myStmt, null);
        }


    }
}
