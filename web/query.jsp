<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SQL query</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"><script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="./style.css">
</head>


<body>

<div class="d-flex flex-column justify-content-center align-items-center h-100">
    <div>
        <form align="center" name="queryForm" action="UsersServlet" method="post">
            <button class="btn btn-outline-success" name="query" value = "select * from users"> Show All Users </button>
            <hr/>
            <button class="btn btn-secondary" name="query" value = "SELECT * FROM users WHERE id = ( SELECT MAX(id) FROM users ) ;"> Show Latest User </button>
            <hr/>
        </form>

        <form align="center" name="queryForm" action="AuctionsServlet" method="post">
            <button class="btn btn-secondary"name="query" value = "select * from AUCTIONSONLINE "> Show all auctions </button>
            <hr/>
        </form>

        <form align="center"name="add"  method="get" action="add.jsp">
            <input class="btn btn-primary" type="submit" name="add" value="Add new user" >
            <hr/>

        </form>

        <form align="center"name="add"  method="get" action="auction.jsp">
            <input class="btn btn-primary" type="submit" name="add" value="Add new auction" >
        </form>

    </div>
</div>
</body>
</html>