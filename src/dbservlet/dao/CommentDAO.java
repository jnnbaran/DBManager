package dbservlet.dao;

import dbservlet.model.Comment;
import dbservlet.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDAO {

    private DataSource dataSource;

    public CommentDAO(DataSource theDataSource) {
        dataSource = theDataSource;

    }

    public List<Comment> getComment(int answerId) throws Exception, SQLException {
        List<Comment> comments = new ArrayList<>();
        String join = "select * from Comments join Answers A join Users U on Comments.AnswerID = A.AnswerId  and Comments.UserId = U.UserId  where A.AnswerId= ?";

        try (
                Connection myConn = dataSource.getConnection();
                PreparedStatement myStmt = myConn.prepareStatement(join)
        ) {
            myStmt.setInt(1, answerId);
            ResultSet myRs = myStmt.executeQuery();
            while (myRs.next()) {
                int commentId = myRs.getInt("CommentId");
                int userId = myRs.getInt("UserId");
                Date date = myRs.getDate("Date");
                String comment = myRs.getString("Comment");
                String userName = myRs.getString("UserName");
                User tempUser = new User(userId, userName);
                Comment tempComment = new Comment(commentId, userId, answerId, date, comment, tempUser);
                comments.add(tempComment);
            }
            return comments;

        }
    }

    public void addComment(Comment theComment) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try  {
            myConn = dataSource.getConnection();
            String sql = "insert into Comments "
                    + "(UserID, AnswerID, Date, Comment) "
                    + "values (?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, theComment.getUserId());
            myStmt.setInt(2, theComment.getAnswerId());
            myStmt.setDate(3, (java.sql.Date) theComment.getDate());
            myStmt.setString(4, theComment.getComment());
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
