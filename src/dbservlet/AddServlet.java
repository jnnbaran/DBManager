package dbservlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Statement;

import static java.lang.System.out;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AddServlet() {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    

	
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");


        try {
            Class.forName("com.mysql.jdbc.Driver");

            String query = request.getParameter("query");
            out.println("query: " + query);

            if (query.length() > 6 &&
                    (
                            query.substring(0, 6).equalsIgnoreCase("insert") ||
                                    query.substring(0, 6).equalsIgnoreCase("update") ||
                                    query.substring(0, 6).equalsIgnoreCase("delete")
                    )
            ) {
                Statement stmt = DBManager.getConnection().createStatement();
                stmt.executeUpdate(query);
                out.println("insert or update or delete DONE");
                stmt.close();
            }
        } catch (Exception e) { out.println("lol exception polecial"); }
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
