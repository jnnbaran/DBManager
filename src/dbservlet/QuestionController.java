package dbservlet;

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

    private QuestionDbUtil QuestionDbUtil;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            QuestionDbUtil = new QuestionDbUtil(dataSource);
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

                case "LOAD":
                    loadQuestion(request, response);
                    break;

                case "UPDATE":
                    updateQuestion(request, response);
                    break;

                case "DELETE":
                    deleteQuestion(request, response);
                    break;

                default:
                    listQuestion(request, response);
            }

        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }


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
                QuestionDbUtil.addQuestion(theQuestion);

                listQuestion(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void listQuestion(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<Question> questions = QuestionDbUtil.getQuestion();

        request.setAttribute("QUESTION_LIST", questions);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-question.jsp");
        dispatcher.forward(request, response);
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
