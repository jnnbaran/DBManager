package dbservlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.System.out;
import static java.lang.System.setOut;


@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public DeleteUser() {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userName = request.getParameter("userName");

        out.println(userName);
        int userId = Integer.parseInt(request.getParameter("userId"));

        //out.println(Password);


        try {

            Class.forName("com.mysql.jdbc.Driver");

            Statement stmt = DBManager.getConnection().createStatement();
            stmt.executeUpdate("DELETE * FROM Users WHERE UserId= ('"+userId+"')");


            out.println("Delete completed successfully");


            stmt.close();

            out.println(" <form align=\"center\" name=\"queryForm\" action=\"login.jsp\" method=\"post\">\n" +
                    " <button class=\"btn btn-outline-success\" name=\"query\" value = \"login\"> LOGIN </button>" +  "</form>");



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
