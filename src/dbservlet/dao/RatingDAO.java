package dbservlet.dao;

import javax.sql.DataSource;
import java.sql.*;

public class RatingDAO {

    private DataSource dataSource;

    public RatingDAO(DataSource theDataSource) {

        dataSource = theDataSource;
    }

    public int getRating(int answerId, boolean positive) throws SQLException {
        String sql = String.format(
                "select count(*) as likes from Rating where AnswerId = %d and Rating = '%b'",
                answerId,
                positive
        );

        try(
                Connection myConn = dataSource.getConnection();
                Statement myStmt = myConn.createStatement();
                ResultSet myRs = myStmt.executeQuery(sql)
        )
         {
                if (myRs.next()) {
                    return myRs.getInt("likes");
                }
                return 0;
        }
    }


    public void updateUserRating(int answerId, int userId, boolean rating) throws SQLException {
        String sql;

        if(getUserRating(answerId, userId) == null ) {
            sql = "insert into Rating (Rating, AnswerId, UserId) values (?, ?, ?)";
        } else {
            sql = "update Rating set Rating = ? where AnswerId = ? and UserId = ?";
        }

        try(
                Connection myConn = dataSource.getConnection();
                PreparedStatement myStmt = myConn.prepareStatement(sql);
        )
        {
            myStmt.setBoolean(1, rating);
            myStmt.setInt(2,answerId);
            myStmt.setInt(3,userId);
            myStmt.execute();
        }
    }

    public Boolean getUserRating(int answerId, int userId) throws SQLException {
        String sql = String.format(
                "select Rating from Rating where AnswerId = %d and UserId = '%d'",
                answerId,
                userId
        );

        try(
                Connection myConn = dataSource.getConnection();
                Statement myStmt = myConn.createStatement();
                ResultSet myRs = myStmt.executeQuery(sql)
        )
        {
            if (myRs.next()) {
                return myRs.getBoolean("Rating");
            }
            return null;
        }
    }
}
