package dbservlet;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                int roleId = myRs.getInt("RoleId");
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


    public void addUser(User theUser) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();

            // create sql for insert
            String sql = "insert into Users "
                    + "(userName, roleId, password) "
                    + "values (?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            // set the param values for the student
            myStmt.setString(1, theUser.getUserName());
            myStmt.setInt(2, theUser.getRoleId());
            myStmt.setString(3, theUser.getPassword());

            // execute sql insert
            myStmt.execute();
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    public User getUser(String theUserId) throws Exception {

        User theUser = null;

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int userId;

        try {
            // convert student id to int
            userId = Integer.parseInt(theUserId);

            // get connection to database
            myConn = dataSource.getConnection();

            // create sql to get selected student
            String sql = "select * from Users where UserId=?";

            // create prepared statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, userId);

            // execute statement
            myRs = myStmt.executeQuery();

            // retrieve data from result set row
            if (myRs.next()) {
                String userName = myRs.getString("userName");
                int roleId = Integer.parseInt(myRs.getString("roleId"));
                String password = myRs.getString("password");

                // use the studentId during construction
                theUser = new User(userId, userName, roleId, password);
            }
            else {
                throw new Exception("Could not find student id: " + userId);
            }

            return theUser;
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    public void deleteUser(String theUserId) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            int userId = Integer.parseInt(theUserId);

            myConn = dataSource.getConnection();

            String sql = "delete from Users where UserId=?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, userId);

            myStmt.execute();
        }
        finally {
            close(myConn, myStmt, null);
        }
    }


    public void updateUser(User theUser) throws SQLException {


        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {


            // get db connection
            myConn = dataSource.getConnection();

            // create SQL update statement
            String sql = "update Users "
                    + "set userName=?, RoleId=?, Password=? "
                    + "where UserId=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setString(1, theUser.getUserName());
            myStmt.setInt(2, theUser.getRoleId());
            myStmt.setString(3, theUser.getPassword());
            myStmt.setInt(4, theUser.getUserId());

            // execute SQL statement
            myStmt.execute();
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }



}



