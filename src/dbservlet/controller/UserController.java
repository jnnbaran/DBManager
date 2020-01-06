package dbservlet.controller;

import dbservlet.dao.UserDAO;
import dbservlet.model.User;

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


@WebServlet("/UserControllerServlet")
public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO UserDAO;

    @Resource(name="jdbc/Knowledgebase")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        // create our student db util ... and pass in the conn pool / datasource
        try {
            UserDAO = new UserDAO(dataSource);
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // read the "command" parameter
            String theCommand = request.getParameter("command");

            // if the command is missing, then default to listing students
            if (theCommand == null) {
                theCommand = "LIST";
            }

            // route to the appropriate method
            switch (theCommand) {

                case "LIST":
                    listUsers(request, response);
                    break;

                case "ADD":
                    addUser(request, response);
                    break;

                case "LOAD":
                    loadUser(request, response);
                    break;

                case "UPDATE":
                    updateUser(request, response);
                    break;

                case "DELETE":
                    deleteUser(request, response);
                    break;

                default:
                    listUsers(request, response);
            }

        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }


    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read student info from form data
        int userId = Integer.parseInt(request.getParameter("userId"));
        String userName = request.getParameter("userName");
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        String password = request.getParameter("password");

        // create a new student object
        User theUser = new User(userId, userName, roleId, password);

        // perform update on database
        UserDAO.updateUser(theUser);

        // send them back to the "list students" page
        listUsers(request, response);
    }

    private void loadUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read student id from form data
        String theUserId = request.getParameter("userId");

        // get student from database (db util)
        User theUser = UserDAO.getUser(theUserId);

        // place student in the request attribute
        request.setAttribute("THE_USER", theUser);

        // send to jsp page: update-student-form.jsp
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/update-user-form.jsp");
        dispatcher.forward(request, response);
    }


    private void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read student info from form data
        String userName = request.getParameter("userName");
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        String password = request.getParameter("password");

        // create a new student object
        User theUser = new User(userName, roleId, password);

        // add the student to the database
        UserDAO.addUser(theUser);

        // send back to main page (the student list)
        listUsers(request, response);
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // get students from db util
        List<User> users = UserDAO.getUsers();

        // add students to the request
        request.setAttribute("USER_LIST", users);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-users.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read student id from form data
        String theUserId = request.getParameter("userId");

        // delete student from database
        UserDAO.deleteUser(theUserId);

        // send them back to "list students" page
        listUsers(request, response);
    }



}

