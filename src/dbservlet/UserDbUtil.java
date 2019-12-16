package dbservlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class UserDbUtil {
    private DataSource dataSource;

    public UserDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<User> getUsers() throws Exception {

        List<User> users = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            myConn = dataSource.getConnection();

            // create sql statement
            String sql = "select * from Users";

            myStmt = myConn.createStatement();

            // execute query
            myRs = myStmt.executeQuery(sql);

            // process result set
            while (myRs.next()) {

                // retrieve data from result set row
                int userId = myRs.getInt("UserId");
                String userName = myRs.getString("UserName");
                String roleId = myRs.getString("RoleId");
                String password = myRs.getString("Password");

                // create new student object
                User tempUser = new User(userId, userName, roleId, password);

                // add it to the list of students
                users.add(tempUser);
            }

            return users;
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

    public void deleteUser(String theUserId) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            int userId = Integer.parseInt(theUserId);

            myConn = dataSource.getConnection();

            String sql = "delete from Users where id=?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, userId);

            myStmt.execute();
        }
        finally {
            close(myConn, myStmt, null);
        }
    }




}


