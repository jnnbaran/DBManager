<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<!DOCTYPE html>
<html>
<head>
    <title>KNOWLEDGEBASE APP</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"><script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <link type="text/css" rel="stylesheet" href="css/style-question.css">


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

<div class="jumbotron background-color: rgb(43, 160, 255)"  >

    <div class="header">
    <div class="container">
            <h1 class="display-4"> FIND QUESTION </h1>
            <p class="lead" ></p>
        </div>
    </div>


<div class="d-flex flex-column justify-content-center align-items-center">

    <div id="content" class="d-flex flex-direction:row margin-right: 3px" >
        <form action="QuestionController" method="GET" >

        <p style=" margin-right: 1rem;"> Find by category:  </p>
        <select class="form-control" style="width: 250px; margin-right: 1rem;" name="categoryId">
            <c:forEach var="tempCategory" items="${CATEGORY_LIST}">

                <c:url var="categoryLink" value="CategoryServlet">
                    <c:param name="command" value="LIST" />
                    <c:param name="categoryId" value="${tempCategory.categoryId}"/>
                </c:url>

                <option href="${categoryLink}" > ${tempCategory.category}</option>


            </c:forEach>

        </select>


        <button name="command" value="selectedList"> SEARCH </button>
        </form>

    </div>


    <hr/>
        <c:forEach var="tempQuestion" items="${QUESTION_LIST}">

            <c:url var="answerseLinkk" value="QuestionController">
                <c:param name="command" value="LOAD" />
                <c:param name="userId" value="${tempUser.userId}"/>
                <c:param name="questionId" value="${tempQuestion.questionId}"/>
            </c:url>

            <c:url var="answersLink" value="AnswerServlet">
                <c:param name="command" value="LIST" />
                <c:param name="userId" value="${tempUser.userId}"/>
                <c:param name="questionId" value="${tempQuestion.questionId}"/>
            </c:url>

    <form action="AnswerServlet" method="get">

        <div class="card"style="width: 60rem;" >
           <h5 class="card-header"> ${tempQuestion.date}, asked by: ${tempQuestion.user.userName} </h5>
            <div class="card-body">
                <h5 class="card-title"> ${tempQuestion.title}  <button class="far fa-trash-alt" style="width: 1rem;height: 1rem;" onclick="if (!(confirm('Are you sure you want to delete this question?'))) return false"></button></h5>

                <p class="card-text"> ${tempQuestion.question} </p>
                <a href="${answersLink}" class="btn btn-secondary"> ANSWERS </a>
            </div>

        </div>
    </form>
            <hr/>

        </c:forEach>

    </div>

</div>
</div>
</body>
</html>
