package dbservlet.dao;

import dbservlet.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private DataSource dataSource;

    public UserDAO(DataSource theDataSource) {

        dataSource = theDataSource;
    }

    public List<User> getUsers() throws Exception {

        List<User> users = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "select * from Users";
            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {

                int userId = myRs.getInt("UserId");
                String userName = myRs.getString("UserName");
                int roleId = myRs.getInt("RoleId");
                String password = myRs.getString("Password");

                User tempUser = new User(userId, userName, roleId, password);

                users.add(tempUser);
            }

            return users;
        }
        finally {
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
            myConn = dataSource.getConnection();

            String sql = "insert into Users "
                    + "(userName, roleId, password) "
                    + "values (?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, theUser.getUserName());
            myStmt.setInt(2, theUser.getRoleId());
            myStmt.setString(3, theUser.getPassword());

            myStmt.execute();
        }
        finally {
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

            userId = Integer.parseInt(theUserId);
            myConn = dataSource.getConnection();

            String sql = "select * from Users where UserId=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, userId);

            myRs = myStmt.executeQuery();

            if (myRs.next()) {
                String userName = myRs.getString("userName");
                int roleId = Integer.parseInt(myRs.getString("roleId"));
                String password = myRs.getString("password");

                theUser = new User(userId, userName, roleId, password);
            }
            else {
                throw new Exception("Could not find student id: " + userId);
            }

            return theUser;
        }
        finally {
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

            myConn = dataSource.getConnection();

            String sql = "update Users "
                    + "set userName=?, RoleId=?, Password=? "
                    + "where UserId=?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, theUser.getUserName());
            myStmt.setInt(2, theUser.getRoleId());
            myStmt.setString(3, theUser.getPassword());
            myStmt.setInt(4, theUser.getUserId());

            myStmt.execute();
        }
        finally {
            close(myConn, myStmt, null);
        }
    }



}



