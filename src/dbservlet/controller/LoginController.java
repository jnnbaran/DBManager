package dbservlet.controller;

import dbservlet.dao.LoginDAO;
import dbservlet.dao.UserDAO;
import dbservlet.model.Login;
import dbservlet.model.User;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/UsersLogin")

public class LoginController extends HttpServlet{

    private static final long serialVersionUID = 1L;

    @Resource(name="jdbc/Knowledgebase")
    private DataSource dataSource;

    private LoginDAO loginDAO;
    private UserDAO userDAO;



    @Override
    public void init() throws ServletException {
        super.init();

        try {
            loginDAO = new LoginDAO(dataSource);
            userDAO = new UserDAO(dataSource);
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String theCommand = request.getParameter("command");


            switch (theCommand) {

                case "SignIn":
                    signIn(request, response);
                    break;

                case "SignUp":
                    signUp(request, response);
                    break;

            }
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }

    }

    private void signUp(HttpServletRequest request, HttpServletResponse response) throws Exception {



        String userName = request.getParameter("userName");
        int roleId = 3;
        String password = request.getParameter("password");

        User theUser = new User(userName, roleId, password);

        userDAO.addUser(theUser);

        response.sendRedirect("index.jsp?status=regok");


    }

    private void signIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Login login = new Login();
        login.setUserName(request.getParameter("userName"));
        login.setPassword(request.getParameter("password"));

        User tempUser = loginDAO.loginCheck(login);

        if(tempUser != null){


            login.setUserId(tempUser.getUserId());
            login.setRoleId(tempUser.getRoleId());
            session.setAttribute("userName",login.getUserName());
            session.setAttribute("userId",login.getUserId());
            session.setAttribute("roleId", login.getRoleId());
            response.sendRedirect("CategoryServlet");

        }

        if(tempUser == null){
            request.setAttribute("result", false);
            response.sendRedirect("index.jsp?status=false");
        }

        if(tempUser.equals("error")){
            response.sendRedirect("index.jsp?status=error");
        }



    }
}
