
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
    <link rel="stylesheet" href="path/to/sweet-alert.css" />


</head>

<body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="path/to/sweet-alert.min.js"></script>


<%

    String status=request.getParameter("status");

    if(status!=null){
        if(status.equals("false")) {
            out.print("<div class=\"alert alert-danger\" role=\"alert\">\n" +
                    "                    Invalid login credentials!\n" +
                    "            </div>");

        }
        else if (status.equals("zbytslabehaslo")){
            out.print("password must contains one digit from 0-9, lowercase characters, uppercase characters, special symbols in the list  @#$%, length at least 6 characters and maximum of 20");
        }
        else if(status.equals("regok")){
            out.print("everything OK, you can login now ");
        }
        else{
            out.print("Some error occurred!");
        }
    }
%>



<div class="container" id="container">
    <div class="form-container sign-up-container">
        <form action="UsersLogin" method="post">
            <h1>Create Account</h1>
            <br>
            <input type="text" placeholder="userName" name="userName" />
            <input type="password" placeholder="password" name="password" />
            <button name="command" id="singup" value ="SignUp">Sign Up</button>
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
            <button name="command"  id="signnanin" value = "SignIn">Sign In</button>
        </form>
    </div>

    <script>

        $("#signnanin").click(function () {

            swal({
                title: 'Congratulations!',
                text: 'Your message has been succesfully sent',
                type: 'success'
            });
            
        })
    </script>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-left">
                <h1 style="color:white">Welcome Back!</h1>
                <p style="color: #3a3535">To keep connected with us please login </p>
                <button class="ghost" id="signIn">Sign In</button>
            </div>
            <div class="overlay-panel overlay-right">
                <h1 style="color:white">Hello, Friend!</h1>
                <p style="color: #3a3535">Enter username and start journey with us</p>
                <button class="ghost" id="signUp">Sign Up</button>
            </div>
        </div>
    </div>
</div>

<script type = "text/javascript" src="./script/alert.js"></script>

<script type = "text/javascript" src="./script/main.js"></script>

</body>

</html>