
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
    String id = request.getParameter("UserId");
    String driver = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost:3306/";
    String database = "Knowledgebase";
    String userid = "root";
    String password = "mysql";
    try {
        Class.forName(driver);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title> Add new user</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"><script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="./style.css">



</head>

<body>


<ul class="nav justify-content-center">
    <li class="nav-item">
        <a class="nav-link" href="loginOK.jsp">Add quest</a>
    </li>
    <li class="nav-item">
        <a class="nav-link active" href="#">Your profile</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="allQuestion.jsp" >All questions</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#">Find question</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="login.jsp">Logout</a>
    </li>
</ul>


<div class="jumbotron jumbotron-fluid">
    <div class="container">
        <h1 class="display-4">KNOWLEDGEBASE ADMIN SITE</h1>
        <p class="lead" >......</p>
    </div>
</div>


<div class="d-flex flex-column justify-content-center align-items-center">

    <table class="table" style="width: 80rem;">
        <thead class="thead-dark">
      <tr>
           <th scope="col">Id</th>
           <th scope="col">User name</th>
           <th scope="col">Role</th>
           <th scope="col"> Action</th>

       </tr>
        </thead>
    <%
        try{
            connection = DriverManager.getConnection(connectionUrl+database, userid, password);
            statement=connection.createStatement();
            String sql ="select * from Users";
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
    %>
        <form action="DeleteUser" method="get">
        <tbody>
        <tr>
            <td type="text" name="userId"><%=resultSet.getString("UserId") %></td>
            <td type="text" name="userName"><%=resultSet.getString("UserName") %></td>
            <td><%=resultSet.getString("RoleId") %></td>
            <td><button class="fas fa-user-slash"  onclick="if (!(confirm('Are you sure you want to delete this user?'))) return false"></button> <button class="far fa-edit " ></button></td>

        </tr>
        </tbody>
        </form>

    <%
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</div>







<hr/>


</div>

</body>

</html>