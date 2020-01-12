
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title> Add new user</title>
    <link rel="stylesheet"  href="./css/style.css">



</head>

<body>


<%

    String status=request.getParameter("status");

    if(status!=null){
        if(status.equals("false")) {
            out.print("<div class=\"alert alert-danger\" role=\"alert\">\n" +
                    "                    Invalid login credentials!\n" +
                    "            </div>");
        }

        else{
            out.print("Some error occurred!");
        }
    }
%>

<div class="container" id="container">
    <div class="form-container sign-up-container">
        <form action="UserControllerServlet" method="get">
            <h1>Create Account</h1>
            <span>or use your USER NAME for registration</span>
            <input type="text" placeholder="userName" name="userName" />
            <input type="password" placeholder="password" name="password" />
            <button name="command" value="REG">Sign Up</button>
        </form>
    </div>
    <div class="form-container sign-in-container">
        <form action="UsersLogin" method="post">
            <h1>Sign in</h1>
            <span>use your user name, please </span>
            <hr/>
            <input type="text" placeholder="userName" name="userName" />
            <input type="password" placeholder="password" name="password" />
        <hr/>
            <button>Sign In</button>
        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-left">
                <h1 style="color:white">Welcome Back!</h1>
                <p style="color: #3a3535">To keep connected with us please login with your personal info</p>
                <button class="ghost" id="signIn">Sign In</button>
            </div>
            <div class="overlay-panel overlay-right">
                <h1 style="color:white">Hello, Friend!</h1>
                <p style="color: #3a3535">Enter your personal details and start journey with us</p>
                <button class="ghost" id="signUp">Sign Up</button>
            </div>
        </div>
    </div>
</div>


<script type = "text/javascript" src="./script/main.js"></script>


</body>

</html>