<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SQL query</title>
</head>
<body>
 <form name="queryForm" action="UsersServlet" method="post">
        <button name="query" value = "select * from users"> Show All Users </button>
       <hr/>
        <button name="query" value = "select * from users WHERE id=max(id)"> Show Latest User </button>
 </form>
    
<form name="queryForm" action="AuctionsServlet" method="post">
        <button name="query" value = "select * from AUCTIONSONLINE "> Show all auctions </button>
 </form>


    
 	<form name="add"  method="get" action="add.jsp">
        <input type="submit" name="add" value="Add new user" >
    </form>

</body>
</html>