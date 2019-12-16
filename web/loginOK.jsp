<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title> Welcome in Knowledgebase </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"><script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="./style.css">



</head>

<body>
<div class="d-flex flex-column justify-content-center align-items-center h-100">

<ul class="nav justify-content-center">
    <li class="nav-item">
        <a class="nav-link" href="loginOK.jsp">Add quest</a>
    </li>
    <li class="nav-item">
        <a class="nav-link active" href="adminSite.jsp">Your profile</a>
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
<hr/>
<div class="jumbotron">

    <h1 class="display-4">Hello, ask anything here!</h1>
<br/>

    <form action="AddQuest" method="get">

    <input class="form-control" name="title" placeholder ="Title">

    <textarea class="form-control"  rows="15" name="question"></textarea>

    <hr/>
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
        <hr/>
        <button class="btn btn-secondary" name="add" > Add question </button>

    </form>


    </p>
</div>

</div>

</body>

</html>