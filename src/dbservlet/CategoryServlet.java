package dbservlet;


import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/CategoryServlet")

public class CategoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CategoryDbUtil CategoryDbUtil;

    @Resource(name="jdbc/Knowledgebase")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        // create our student db util ... and pass in the conn pool / datasource
        try {
            CategoryDbUtil = new CategoryDbUtil(dataSource);
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String theCommand = request.getParameter("command");

            if (theCommand == null) {
                theCommand = "LIST";
            }


            switch (theCommand) {

                case "LIST":
                    listCategory(request, response);
                    break;
                default:
                    listCategory(request, response);

            }

        } catch (Exception exc) {
            throw new ServletException(exc);
        }

    }

    private void listCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

            List<Category> categories = CategoryDbUtil.getCategory();

            // add students to the request
            request.setAttribute("CATEGORY_LIST", categories);

            // send to JSP page (view)
            RequestDispatcher dispatcher = request.getRequestDispatcher("/main-page.jsp");
            dispatcher.forward(request, response);


    }
    }












