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

    private UserDAO userDAO;

    @Resource(name="jdbc/Knowledgebase")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            userDAO = new UserDAO(dataSource);
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

        userDAO.updateUser(theUser);

        listUsers(request, response);
    }

    private void loadUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String theUserId = request.getParameter("userId");
        User theUser = userDAO.getUser(theUserId);
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
        userDAO.addUser(theUser);

        listUsers(request, response);
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<User> users = userDAO.getUsers();

        request.setAttribute("USER_LIST", users);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-users.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String theUserId = request.getParameter("userId");
        userDAO.deleteUser(theUserId);
        listUsers(request, response);
    }



}

