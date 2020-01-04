package dbservlet;

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

    private LoginDbUtil LoginDbUtil;

    @Override
    public void init() throws ServletException {
        super.init();

        // create our student db util ... and pass in the conn pool / datasource
        try {
            LoginDbUtil = new LoginDbUtil(dataSource);
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

        String result=LoginDbUtil.loginCheck(login);

        if(result != "false" && result != "error"){

            int userId = Integer.parseInt(result);
            login.setUserId(userId);
            session.setAttribute("userName",login.getUserName());
            session.setAttribute("userId",login.getUserId());

            response.sendRedirect("CategoryServlet");
        }

        if(result.equals("false")){
            request.setAttribute("result", false);
            response.sendRedirect("index.jsp?status=false");


        }

        if(result.equals("error")){
            response.sendRedirect("index.jsp?status=error");
        }

    }
}
