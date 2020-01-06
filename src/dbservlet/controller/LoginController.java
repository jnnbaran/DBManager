package dbservlet.controller;

import dbservlet.dao.LoginDAO;
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

    private LoginDAO LoginDAO;

    @Override
    public void init() throws ServletException {
        super.init();

        // create our student db util ... and pass in the conn pool / datasource
        try {
            LoginDAO = new LoginDAO(dataSource);
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Login login = new Login();
        login.setUserName(request.getParameter("userName"));
        login.setPassword(request.getParameter("password"));

        User tempUser = LoginDAO.loginCheck(login);

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
