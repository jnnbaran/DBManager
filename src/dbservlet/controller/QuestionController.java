package dbservlet.controller;

import dbservlet.dao.CategoryDAO;
import dbservlet.dao.QuestionDAO;
import dbservlet.model.Category;
import dbservlet.model.Question;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/QuestionController")
public class QuestionController  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(name="jdbc/Knowledgebase")
    private DataSource dataSource;

    private QuestionDAO questionDAO;
    private CategoryDAO categoryDAO;


    @Override
    public void init() throws ServletException {
        super.init();

        try {
            questionDAO = new QuestionDAO(dataSource);
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
                    listQuestion(request, response);
                    break;

                case "ADD":
                    addQuestion(request, response);
                    break;

                case "selectedList":
                    listSelectedQuestion(request, response);
                    break;

                case "UPDATE":
                    updateQuestion(request, response);
                    break;

                case "DELETE":
                    deleteQuestion(request, response);
                    break;
                case "LIST_CATEGORY":
                    listCategory(request, response);
                    break;

                default:
                    listQuestion(request, response);
            }

        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }


    }

    private void listCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        List<Category> categories = categoryDAO.getCategory();

        // add students to the request
        request.setAttribute("CATEGORY_LIST", categories);
        int roleId = (int) session.getAttribute("roleId");

        request.setAttribute("role", roleId);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/main-page.jsp");
        dispatcher.forward(request, response);

    }

    private void deleteQuestion(HttpServletRequest request, HttpServletResponse response) {
    }

    private void loadQuestion(HttpServletRequest request, HttpServletResponse response) {
    }

    private void addQuestion(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        String title = request.getParameter("title");
        String question = request.getParameter("question");
        Object userId = session.getAttribute("userId");
        String categoryName = request.getParameter("categoryId");

        try {
            Connection myConn = dataSource.getConnection();
            String sql = "select * from Category where Category=? ";

            PreparedStatement myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, String.valueOf(categoryName));

            ResultSet rs = myStmt.executeQuery();

            if (rs.next()) {

                String categoryId = String.valueOf(rs.getInt("CategoryId"));
                int categoryIdd = Integer.parseInt(categoryId);

                java.util.Date datee = new java.util.Date();//date object
                java.sql.Date date = new java.sql.Date(datee.getTime());
                //  int categoryId = 2;

                Question theQuestion = new Question(date, (Integer) userId, title, question, categoryIdd);

                // add the question to the database
                questionDAO.addQuestion(theQuestion);

                listQuestion(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void listQuestion(HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        List<Question> questions = questionDAO.getQuestion();

        request.setAttribute("QUESTION_LIST", questions);

        List<Category> categories = categoryDAO.getCategory();

        // add students to the request
        request.setAttribute("CATEGORY_LIST", categories);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-question.jsp");
        dispatcher.forward(request, response);
    }

    private void listSelectedQuestion(HttpServletRequest request, HttpServletResponse response)
            throws Exception {



        String categoryName = request.getParameter("categoryId");
        try {
            Connection myConn = dataSource.getConnection();
            String sql = "select * from Category where Category=? ";

            PreparedStatement myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, String.valueOf(categoryName));

            ResultSet rs = myStmt.executeQuery();

            if (rs.next()) {

                String categoryId = String.valueOf(rs.getInt("CategoryId"));
                int categoryIdd = Integer.parseInt(categoryId);

                List<Category> categories = categoryDAO.getCategory();
                request.setAttribute("CATEGORY_LIST", categories);

                List<Question> questions = questionDAO.getSelectQuestions(categoryIdd);
                request.setAttribute("QUESTION_LIST", questions);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/list-question.jsp");
                dispatcher.forward(request, response);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }



    }



    private void updateQuestion(HttpServletRequest request, HttpServletResponse response) throws Exception {


    }


    public String findCategory(String category) {

        try {
            Connection myConn = dataSource.getConnection();
            String sql = "select * from Category where Category=? ";

            PreparedStatement myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, String.valueOf(category));

            ResultSet rs = myStmt.executeQuery();

            if (rs.next()) {

                String categoryId = String.valueOf(rs.getInt("CategoryId"));


                return categoryId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return "error";
    }







}
