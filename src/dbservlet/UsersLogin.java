package dbservlet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

            PreparedStatement stm = DBManager.getConnection().prepareStatement("select UserId, UserName,Password from Users WHERE UserName=? AND Password=?");
            PreparedStatement stm2 = DBManager.getConnection().prepareStatement("select UserId from Users WHERE UserName=? AND Password=?");

            stm.setString(1, userName);
            stm.setString(2, Password);
          //  stm2.setString(1, userName);
          //  stm2.setString(2, Password);
            ResultSet rs = stm.executeQuery();
           // ResultSet rs1 = stm2.executeQuery();


           // out.println(rs1.getLong("UserId"));


            if(rs.next()){

             //   HttpSession session = request.getSession();
             //   request.getSession().setAttribute("UserId", rs1.getLong("UserId"));
                RequestDispatcher req = request.getRequestDispatcher("loginOK.jsp");
                req.include(request,response);

                /*
                        <td><%=resultSet.getString("Date") %></td>

                HttpSession session = request.getSession();
                session.setAttribute("user", userName);
                response.sendRedirect("Welcome");
                 */


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
