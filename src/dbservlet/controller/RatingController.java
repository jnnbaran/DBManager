package dbservlet.controller;

import dbservlet.dao.RatingDAO;
import dbservlet.dao.UserDAO;
import dbservlet.model.Rating;
import dbservlet.model.User;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

public class RatingController {
/*
    private static final long serialVersionUID = 1L;
    private RatingDAO RatingDAO;

    private dbservlet.dao.UserDAO UserDAO;

    @Resource(name="jdbc/Knowledgebase")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            UserDAO = new UserDAO(dataSource);
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String theCommand = request.getParameter("command");

            switch (theCommand) {

                case "ADD":
                    addRating(request, response);
                    break;

            }

        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }


    }


    private void addRating(HttpServletRequest request, HttpServletResponse response) throws Exception {


        int userId = request.getParameter("userName");
        int answereId = Integer.parseInt(request.getParameter("roleId"));
        int rating = request.getParameter("password");

        Rating theRating = new Rating(userId, answereId, rating);

        RatingDAO.addRating(theRating);

        //tu jeszcze nie wiem co

    }
    }
*/
}
