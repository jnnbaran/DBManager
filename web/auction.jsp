<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %><%--
  Created by IntelliJ IDEA.
  User: joanna
  Date: 25.11.2019
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>you can add new auction here </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"><script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="./style.css">
</head>

<body>

<div class="d-flex flex-column justify-content-center align-items-center">
    <h1>you can add new auction here</h1>
    <select class="select">
    <option value="-1">Select User</option>
    <%
        try{

            String Query = "SELECT * FROM users";
            Class.forName("com.mysql.jdbc.Driver"); //.newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/itsecurity","root", "mysql");
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(Query);

            while(rs.next()){
    %>
    <option value="<%=rs.getInt("id") %>"><%=rs.getString("last_name") %></option>
    <%
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            out.println("Error: "+ex.getMessage());
        }
    %>

    </select>


</div>

</body>
</html>
