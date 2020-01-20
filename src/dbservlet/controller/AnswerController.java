package dbservlet.controller;

import dbservlet.dao.*;
import dbservlet.model.Answer;
import dbservlet.model.Category;
import dbservlet.model.Comment;
import dbservlet.model.Question;
import org.w3c.dom.ls.LSOutput;

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


@WebServlet("/AnswerServlet")
public class AnswerController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Resource(name="jdbc/Knowledgebase")
    private DataSource dataSource;
    private AnswerDAO answerDAO;
    private QuestionDAO questionDAO;
    private RatingDAO ratingDAO;
    private CommentDAO commentDAO;



    @Override
    public void init() throws ServletException {
        super.init();
        try {
            answerDAO = new AnswerDAO(dataSource);
            questionDAO = new QuestionDAO(dataSource);
            ratingDAO = new RatingDAO(dataSource);
            commentDAO = new CommentDAO(dataSource);
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
                case "updateRating":
                    updateRating(request, response);
                    break;
                case "addComment":
                    addComment(request, response);
                    break;
                default:
                    listAnswer(request, response);
            }
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    private void addComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        java.util.Date datee = new java.util.Date();//date object
        java.sql.Date date = new java.sql.Date(datee.getTime());
        String comment = request.getParameter("comment");
        int answerId = Integer.parseInt(request.getParameter("answerId"));


        Comment theComment = new Comment(userId, answerId, date, comment);
        commentDAO.addComment(theComment);
        listAnswer(request, response);

    }


    private void deleteAnswer(HttpServletRequest request, HttpServletResponse response) {
    }
    private void loadAnswer(HttpServletRequest request, HttpServletResponse response) {
    }
    private void addAnswer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String theQuestionId = request.getParameter("questionId");
        int questionId = Integer.parseInt(theQuestionId);
        Object userId = session.getAttribute("userId");
        String answer = request.getParameter("answer");
        java.util.Date datee = new java.util.Date();
        java.sql.Date date = new java.sql.Date(datee.getTime());

        Answer theAnswer = new Answer(date, answer, questionId, userId);
        answerDAO.addAnswer(theAnswer);


    }

    private void listAnswer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String theQuestionId = request.getParameter("questionId");
        Question theQuestion= questionDAO.getQuestion(theQuestionId);
        request.setAttribute("THE_QUESTION", theQuestion);
        int questionIdd = Integer.parseInt(theQuestionId);

        List<Answer> answers = answerDAO.getAnswer(questionIdd);
        fetchRatingForAnswers(answers, (int)request.getSession().getAttribute("userId"));
        fetchCommentsForAnswers(answers);
        request.setAttribute("ANSWER_LIST", answers);

      /*  for (Answer answer: answers
             ) {
            List<Comment> comments = commentDAO.getComment(answer.getAnswerId());
            request.setAttribute("COMMENTS_LIST", comments);
        }
*/
        RequestDispatcher dispatcher = request.getRequestDispatcher("/answers.jsp");
        dispatcher.forward(request, response);


    }

    private void fetchRatingForAnswers(List<Answer> answers, int currentUserId) throws Exception {
        for (Answer answer: answers) {
            answer.setRating(ratingDAO.getRating(answer.getAnswerId(), true));
            answer.setNegativeRating(ratingDAO.getRating(answer.getAnswerId(), false));
            answer.setActiveUserRating(ratingDAO.getUserRating(answer.getAnswerId(), currentUserId));
        }
    }

    private void fetchCommentsForAnswers(List<Answer> answers)throws Exception{
        for (Answer answer: answers) {
            answer.setComments(commentDAO.getComment(answer.getAnswerId()));
        }
    }
    private void updateRating(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Integer currentUserId = (Integer) session.getAttribute("userId");
        int answerId = Integer.parseInt(request.getParameter("answerId"));
        String vote = request.getParameter("vote");

        switch (vote) {
            case "upvote":
                ratingDAO.updateUserRating(answerId, currentUserId, true);
                break;
            case "downvote":
                ratingDAO.updateUserRating(answerId, currentUserId, false);
                break;
        }


        listAnswer(request,response);

    }

}
