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


public class AnswerController   extends HttpServlet {
  /*  private static final long serialVersionUID = 1L;

    @Resource(name="jdbc/Knowledgebase")
    private DataSource dataSource;

    private AnswerDAO AnswerDAO;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            AnswerDAO = new AnswerDAO(dataSource);
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

    private void addAnswer(HttpServletRequest request, HttpServletResponse response) {
    }

    private void listAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Answer> answers = AnswerDAO.getAnswer();


        request.setAttribute("ANSWER_LIST", answers);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/answers.java");
        dispatcher.forward(request, response);
    }


   */
}
