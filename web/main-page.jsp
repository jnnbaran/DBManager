<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet"  href="./css/style.css">




</head>

<body>

<div class="d-flex flex-column justify-content-center align-items-center h-100 ">


    <ul class="nav justify-content-center">
        <li class="nav-item">
            <a class="nav-link" href="loginOK.jsp">Add quest</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="UserControllerServlet">Your profile</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="QuestionController" >All questions</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Find question</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="index.jsp">Logout</a>
        </li>
    </ul>
    <div class="jumbotron background-color: rgb(43, 160, 255)"  >

        <h1 class="display-4">Hello, ask anything here!</h1>
        <br/>

        <form action="QuestionController" method="GET">

            <input type="hidden" name="command" value="ADD" />

            <input class="form-control" name="title" placeholder ="Title">
            <textarea class="form-control"  rows="15" name="question" placeholder ="place to ask questions"></textarea>

            <hr/>



            <select class="form-control" style="width: 250px" name="categoryId">
                <c:forEach var="tempCategory" items="${CATEGORY_LIST}">

                    <c:url var="categoryLink" value="CategoryServlet">
                        <c:param name="command" value="LIST" />
                        <c:param name="categoryId" value="${tempCategory.categoryId}"/>
                    </c:url>

                <option href="${categoryLink}" > ${tempCategory.category}</option>


                </c:forEach>

              </select>




            <hr/>

            <button class="btn btn-secondary" name="add" > Add question </button>

        </form>

        </p>
    </div>

</div>

</body>

</html>