package dbservlet.dao;

import dbservlet.model.Login;
import dbservlet.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {


    private DataSource dataSource;

    public LoginDAO(DataSource theDataSource) {

        dataSource = theDataSource;
    }


    public User loginCheck(Login loginBean) {

        try {
            Connection myConn = dataSource.getConnection();
            String sql = "select * from Users where UserName=? and password=?";


            PreparedStatement myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, loginBean.getUserName());
            myStmt.setString(2, loginBean.getPassword());

            ResultSet rs = myStmt.executeQuery();

            if (rs.next()) {
                int userid = rs.getInt("userId");
                String userName = rs.getString("userName");
                int roleId = rs.getInt("RoleId");
                String password = rs.getString("Password");

                User tempUser = new User(userid, userName, roleId, password);
                return tempUser;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
}