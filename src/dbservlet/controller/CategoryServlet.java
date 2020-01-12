package dbservlet.controller;


import dbservlet.dao.CategoryDAO;
import dbservlet.model.Category;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/CategoryServlet")

public class CategoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CategoryDAO categoryDAO;

    @Resource(name="jdbc/Knowledgebase")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            categoryDAO = new CategoryDAO(dataSource);
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
            HttpSession session = request.getSession();

            List<Category> categories = categoryDAO.getCategory();

            request.setAttribute("CATEGORY_LIST", categories);
            int roleId = (int) session.getAttribute("roleId");

           request.setAttribute("role", roleId);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/main-page.jsp");
            dispatcher.forward(request, response);



    }
    }












