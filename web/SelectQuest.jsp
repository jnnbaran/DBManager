
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
        <h1 class="display-4">KNOWLEDGEBASE</h1>
        <p class="lead" >Find anyything you want.</p>
    </div>
</div>

<div class=" d-flex justify-content-center">



    <select class="form-control" style="width: 250px">
        <option value="-1">Select Category</option>
        <%
            try{

                String Query = "SELECT * FROM Category";
                Class.forName("com.mysql.jdbc.Driver"); //.newInstance();


                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Knowledgebase","root", "mysql");

                Statement stm = conn.createStatement();

                ResultSet rs = stm.executeQuery(Query);

                while(rs.next()){
        %>
        <option value="<%=rs.getInt("CategoryId") %>"><%=rs.getString("Category") %></option>

        <%
                }



            }catch(Exception ex){
                ex.printStackTrace();
                out.println("Error: "+ex.getMessage());
            }
        %>

    </select>
    <form action="SelectQuest" method="get" value="select * from Question where CategoryId=2">
        <button class="btn btn-outline-success" id="btn" name="query" style="WIDTH: 10REM;
            HEIGHT: fit-content;"  >SEARCH </button>
    </form>
</div>

<div class="d-flex flex-column justify-content-center align-items-center">


    <!--   <tr>
           <td>Date</td>
           <td>Title</td>
           <td>Question</td>

       </tr> -->
    <%
        try{
            connection = DriverManager.getConnection(connectionUrl+database, userid, password);
            statement=connection.createStatement();
            String sql ="select * from Questions where CategoryId=2";
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
    %>


    <!-- <tr>
        <td><%=resultSet.getString("Date") %></td>
        <td><%=resultSet.getString("Title") %></td>
        <td><%=resultSet.getString("Question") %></td>
    </tr>* -->

    <div class="card"style="width: 60rem;" >
        <h5 class="card-header"> <%=resultSet.getString("Date") %> </h5>
        <div class="card-body">
            <h5 class="card-title"><strong> <%=resultSet.getString("Title") %></strong>  <button class="far fa-trash-alt" style="width: 2rem;height: 2rem;"></button></h5>

            <p class="card-text"><%=resultSet.getString("Question") %></p>
            <a href="#" class="btn btn-primary">Add a comments</a>
        </div>

    </div>

    <hr/>
    <%
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</div>







<hr/>



    <form action="AddQuest" method="get">

        <button class="btn btn-secondary" name="query" > OK </button>

    </form>



</div>

</body>

</html>