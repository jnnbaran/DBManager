package dbservlet.controller;

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
import java.util.List;

@WebServlet("/UsersQuestionController")
public class UsersQuestionController  extends HttpServlet {

    @Resource(name = "jdbc/Knowledgebase")
    private DataSource dataSource;
    private QuestionDAO questionDAO;


    @Override
    public void init() throws ServletException {
        super.init();

        try {
            questionDAO = new QuestionDAO(dataSource);
        } catch (Exception exc) {
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

                case "UPDATE":
                    updateQuestion(request, response);
                    break;

                default:
                    listQuestion(request, response);
            }

        } catch (Exception exc) {
            throw new ServletException(exc);
        }


    }

    private void updateQuestion(HttpServletRequest request, HttpServletResponse response) {
    }

    private void listQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        List<Question> questions = questionDAO.getQuestionForUser(userId);

        request.setAttribute("QUESTION_LIST", questions);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user-list-question.jsp");
        dispatcher.forward(request, response);


    }
}