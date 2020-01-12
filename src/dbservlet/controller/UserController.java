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
import java.io.PrintWriter;
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

        // create user db util ... and pass in the conn pool / datasource
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

            if (theCommand == null) {
                theCommand = "LIST";
            }

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
                case "REG":
                    registerUser(request,response);

                default:
                    listUsers(request, response);
            }

        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }


    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int userId = Integer.parseInt(request.getParameter("userId"));
        String userName = request.getParameter("userName");
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        String password = request.getParameter("password");

        User theUser = new User(userId, userName, roleId, password);

        UserDAO.updateUser(theUser);

        // send to the "list students" page
        listUsers(request, response);
    }

    private void loadUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String theUserId = request.getParameter("userId");

        // get user from database (db util)
        User theUser = UserDAO.getUser(theUserId);

        // place user in the request attribute
        request.setAttribute("THE_USER", theUser);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/update-user-form.jsp");
        dispatcher.forward(request, response);
    }


    private void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String userName = request.getParameter("userName");
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        String password = request.getParameter("password");

        User theUser = new User(userName, roleId, password);

        UserDAO.addUser(theUser);

        // send back to main page (the student list)
        listUsers(request, response);
    }


    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter(  );

        String userName = request.getParameter("userName");

        //you can register only as user with role no.3
        int roleId = 3;
        String password = request.getParameter("password");
        User theUser = new User(userName, roleId, password);
        UserDAO.addUser(theUser);

        out.print("You are successfully registered...");
        // dodać walidację, czy istnieje juz taki sam user
        // dodać walidację trudności hasłą
        //dodać okienko, rejestracja pomyślna, teraz możesz sie zalogować -> po kliknięciu powrót do strony logowania


    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // get user from db util
        List<User> users = UserDAO.getUsers();

        // add user to the request
        request.setAttribute("USER_LIST", users);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-users.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read user id from form data
        String theUserId = request.getParameter("userId");

        // delete user from database
        UserDAO.deleteUser(theUserId);

        // send them back to "list students" page
        listUsers(request, response);
    }



}

