package dbservlet;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDbUtil implements LoginDAO {


    private DataSource dataSource;

    public LoginDbUtil(DataSource theDataSource) {

        dataSource = theDataSource;
    }


    @Override
    public String loginCheck(Login loginBean) {

        try{
            Connection myConn = dataSource.getConnection();
            String sql ="select * from Users where UserName=? and password=?";

            PreparedStatement myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1,loginBean.getUserName());
            myStmt.setString(2,loginBean.getPassword());

            ResultSet rs=myStmt.executeQuery();

            if(rs.next()){

                String userid = String.valueOf(rs.getInt("userId"));


                return userid;
            }
            else{
                return "false";
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return "error";
    }
}
