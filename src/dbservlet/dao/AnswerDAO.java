package dbservlet.dao;
import dbservlet.model.Answer;
import dbservlet.model.Question;
import dbservlet.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnswerDAO {

    private DataSource dataSource;

    public AnswerDAO(DataSource theDataSource) {
        dataSource = theDataSource;

    }

    public List<Answer> getAnswer(int questionId) throws Exception, SQLException {
        List<Answer> answers = new ArrayList<>();
        String join = "select * from Answers join Questions Q join Users U on Answers.QuestionID = Q.QuestionId  and Answers.UserId = U.UserId  where Answers.QuestionID = ?";

        try (   // get a connection
                Connection myConn = dataSource.getConnection();
                PreparedStatement myStmt = myConn.prepareStatement(join)
        ) {
            myStmt.setInt(1, questionId);
            ResultSet myRs = myStmt.executeQuery();
            while (myRs.next()) {
                int answerId = myRs.getInt("AnswerId");
                int QuestionId = myRs.getInt("QuestionId");
                Date date = myRs.getDate("Date");
                String Title = myRs.getString("Title");
                String Question = myRs.getString("Question");
                String Answer = myRs.getString("Answer");

                int CategoryId = myRs.getInt("CategoryId");
                int userId = myRs.getInt("UserId");
                String userName = myRs.getString("UserName");
                int roleId = myRs.getInt("RoleId");
                String password = myRs.getString("Password");
                User tempUser = new User(userId, userName, roleId, password);
                Answer tempAnswer = new Answer(answerId, date, Answer, QuestionId, userId, tempUser);
                //TU SKOŃCZYŁAM, POZOSTAŁO - POZAMIENIAC ZMIENNE, UPORZĄDKOWAĆ, PRZETESTOWAĆ WYŚWIETLANIE
                answers.add(tempAnswer);
            }
            return answers;

        }
    }

    public void addAnswer(Answer theAnswer) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try  {
            myConn = dataSource.getConnection();
            String sql = "insert into Answers "
                    + "(Date , Rating, Answer, QuestionID, UserId) "
                    + "values (?, ?, ?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setDate(1, (java.sql.Date) theAnswer.getDate());
            myStmt.setInt(2, theAnswer.getRating());
            myStmt.setString(3, theAnswer.getAnswer());
            myStmt.setInt(4, theAnswer.getQuestionId());
            myStmt.setInt(5, theAnswer.getUserId());

            myStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(myConn, myStmt, null);
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
}
