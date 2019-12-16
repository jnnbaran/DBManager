package dbservlet;

import java.io.IOException;
import java.time.Year;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/UserControllerServlet")
public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDbUtil UserDbUtil;

    @Resource(name="jdbc/web_student_tracker")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        // create our student db util ... and pass in the conn pool / datasource
        try {
            UserDbUtil = new UserDbUtil(dataSource);
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // list the students ... in mvc fashion
        try {
            listUsers(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // get students from db util
        List<User> students = UserDbUtil.getUsers();

        // add students to the request
        request.setAttribute("USER_LIST", students);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-users.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read student id from form data
        String theUserId = request.getParameter("userId");

        // delete student from database
        UserDbUtil.deleteUser(theUserId);

        // send them back to "list students" page
        listUsers(request, response);
    }

}

