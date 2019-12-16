package dbservlet;


        import javax.servlet.RequestDispatcher;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.swing.text.DateFormatter;
        import java.io.IOException;
        import java.io.PrintWriter;
        import java.sql.*;
        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.time.LocalDate;
        import java.time.ZoneId;
        import java.util.UUID;
        import java.util.logging.SimpleFormatter;

        import static java.lang.System.out;
        import static java.lang.System.setOut;


@WebServlet("/AddQuest")
public class AddQuest extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public AddQuest() {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int UserId = 2;
        int CategoryId = 2;
        String Title = request.getParameter("title");
        String Question = request.getParameter("question");



        LocalDate todayLocalDate = LocalDate.now();

        try {

            Class.forName("com.mysql.jdbc.Driver");

            PreparedStatement pstmt = DBManager.getConnection().prepareStatement("INSERT INTO Questions (Date, UserId, Title, Question, CategoryId) VALUES (?,?,?,?,?)");

         /*   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("!!!!!->"+sdf.format(Date.valueOf(todayLocalDate)));*/

        //    PreparedStatement pstmt = con.prepareStatement("INSERT INTO Questions (Date, UserId, Title, Question, CategoryId) VALUES (?,?,?,?,?)");

            java.util.Date date = new java.util.Date();//date object
            java.sql.Date sqlDate=new java.sql.Date(date.getTime());
          //  Long UserId = (Long) request.getSession().getAttribute("UserId");


            pstmt.setDate(1, sqlDate);
            pstmt.setLong(2, UserId );
            pstmt.setString(3, Title );
            pstmt.setString(4, Question );
            pstmt.setInt(5, CategoryId);




           pstmt.executeUpdate();


           out.println("Insert completed successfully");
           // RequestDispatcher req = request.getRequestDispatcher("allQuestion.jsp");





        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);



    }

}
