package dbservlet.controller;

import dbservlet.dao.AnswerDAO;
import dbservlet.dao.CategoryDAO;
import dbservlet.dao.QuestionDAO;
import dbservlet.model.Answer;
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
import java.util.List;

@WebServlet("/AnswerServlet")
public class AnswerController extends HttpServlet{

    private static final long serialVersionUID = 1L;
    @Resource(name="jdbc/Knowledgebase")
    private DataSource dataSource;
    private AnswerDAO answerDAO;
    private QuestionDAO questionDAO;


    @Override
    public void init() throws ServletException {
        super.init();
        try {
            answerDAO = new AnswerDAO(dataSource);
            questionDAO = new QuestionDAO(dataSource);
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String theCommand = request.getParameter("command");
            switch (theCommand) {
                case "LIST":
                    listAnswer(request, response);
                    break;
                case "ADD":
                    addAnswer(request, response);
                    break;
                case "LOAD":
                    loadAnswer(request, response);
                    break;
                case "DELETE":
                    deleteAnswer(request, response);
                    break;
                default:
                    listAnswer(request, response);
            }
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }
    private void deleteAnswer(HttpServletRequest request, HttpServletResponse response) {
    }
    private void loadAnswer(HttpServletRequest request, HttpServletResponse response) {
    }
    private void addAnswer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String theQuestionId = request.getParameter("questionId");
        Object userId = session.getAttribute("userId");
        String answer = request.getParameter("answer");
        int rating = 0;

        java.util.Date datee = new java.util.Date();//date object
        java.sql.Date date = new java.sql.Date(datee.getTime());

        Answer theAnswer = new Answer(date, rating, answer, theQuestionId, userId);
        answerDAO.addAnswer(theAnswer);
        listAnswer(request, response);

    }

    private void listAnswer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String theQuestionId = request.getParameter("questionId");
        Question theQuestion= questionDAO.getQuestion(theQuestionId);
        request.setAttribute("THE_QUESTION", theQuestion);
        int questionIdd = Integer.parseInt(theQuestionId);

        List<Answer> answers = answerDAO.getAnswer(questionIdd);
        request.setAttribute("ANSWER_LIST", answers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/answers.jsp");
        dispatcher.forward(request, response);
    }
}
