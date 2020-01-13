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
                int QuestionId = myRs.getInt("QuestionId");
                Date Date = myRs.getDate("Date");
                String Title = myRs.getString("Title");
                String Question = myRs.getString("Question");
                int Rating = myRs.getInt("Rating");
                String Answer = myRs.getString("Answer");

                int CategoryId = myRs.getInt("CategoryId");
                int userId = myRs.getInt("UserId");
                String userName = myRs.getString("UserName");
                int roleId = myRs.getInt("RoleId");
                String password = myRs.getString("Password");
                User tempUser = new User(userId, userName, roleId, password);
                Answer tempAnswer = new Answer((java.sql.Date) Date, Rating, Answer, QuestionId, userId, tempUser);
                //TU SKOŃCZYŁAM, POZOSTAŁO - POZAMIENIAC ZMIENNE, UPORZĄDKOWAĆ, PRZETESTOWAĆ WYŚWIETLANIE
                answers.add(tempAnswer);
            }
            return answers;

        }
    }

    public void addAnswer(Answer theAnswer) {

        String sql = "insert into Answers "
                + "(Date , Rating, Answer, QuestionId, UserId) "
                + "values (?, ?, ?, ?, ?)";
        try (Connection myConn = dataSource.getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            myStmt.setDate(1, (java.sql.Date) theAnswer.getDate());
            myStmt.setInt(2, theAnswer.getRating());
            myStmt.setString(3, theAnswer.getAnswer());
            myStmt.setInt(5, theAnswer.getQuestionId());
            myStmt.setInt(5, theAnswer.getUserId());

            myStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




