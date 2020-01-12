package dbservlet.dao;

import dbservlet.model.Rating;
import dbservlet.model.User;

import javax.sql.DataSource;
import java.sql.*;

public class RatingDAO {
/*
    private DataSource dataSource;


    public RatingDAO(DataSource theDataSource) {

        dataSource = theDataSource;
    }


    public void addRating(Rating theRating) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();

            String sql = "insert into Rating "
                    + "(userId, AnswerId, Rating) "
                    + "values (?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            // set the param values for the student
            myStmt.setInt(1, theRating.getRatingId());
            myStmt.setInt(2, theRating.getAnswereId());
            myStmt.setInt(3, theRating.getRating());

            // execute sql insert
            myStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


*/
}





