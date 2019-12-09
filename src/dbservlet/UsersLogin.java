package dbservlet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static java.lang.System.out;
import static java.lang.System.setOut;


@WebServlet("/UsersLogin")
public class UsersLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public UsersLogin() {
        super();

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userName = request.getParameter("first_name");
        String Password = request.getParameter("pwd");


        try {

            Class.forName("com.mysql.jdbc.Driver");


          //  Statement stmt = DBManager.getConnection().createStatement();

            PreparedStatement stm = DBManager.getConnection().prepareStatement("select UserName,Password from Users WHERE UserName=? AND Password=?");

            stm.setString(1, userName);
            stm.setString(2, Password);
            ResultSet rs = stm.executeQuery();


            if(rs.next()){

                RequestDispatcher req = request.getRequestDispatcher("loginOK.jsp");
                req.include(request,response);
            }
            else{
                RequestDispatcher req = request.getRequestDispatcher("badLogin.jsp");
                req.include(request,response);

            }

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);



    }

}
