package dbservlet;

import dbservlet.Category;
import dbservlet.CategoryDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        listCategory(request, response);
    }

    private void listCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        CategoryDAO dao = new CategoryDAO();

        try {

            List<Category> listCategory = dao.list();
            request.setAttribute("listCategory", listCategory);

            RequestDispatcher dispatcher = request.getRequestDispatcher("loginOK.jsp");
            dispatcher.forward(request, response);


            out.println("   <select name=\"category\">\n" +
                    "                    <c:forEach items=\"${listCategory}\" var=\"category\">\n" +
                    "                        <option value=\"${category.CategoryId}\"\n" +
                    "                                <c:if test=\"${category.CategoryId eq selectedCatId}\">selected=\"selected\"</c:if>\n" +
                    "                        >\n" +
                    "                                ${category.Category}\n" +
                    "                        </option>\n" +
                    "                    </c:forEach>\n" +
                    "                </select>");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("category"));

        request.setAttribute("selectedCatId", categoryId);

        listCategory(request, response);
    }
}