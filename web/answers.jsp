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
    <link type="text/css" rel="stylesheet" href="css/answers.css">


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
            <h1 class="display-4"> KNOWLEDGEBASE </h1>
            <p class="lead" ></p>
        </div>
    </div>


    <div class="d-flex flex-column justify-content-center align-items-center">

        <hr/>

        <div class="card"style="width: 60rem;" >
            <h5 class="card-header"> ${THE_QUESTION.date} asked by:  </h5>
            <div class="card-body">
                <h5 class="card-title"> ${THE_QUESTION.title}</h5>
                <p class="card-text"> ${THE_QUESTION.question} </p>
            </div>

        </div>
        <hr/>
    </div>

    <h3> ANSWERES </h3>

    <c:forEach var="tempAnswer" items="${ANSWER_LIST}">

<form>


    <h5 class="card-header" style="position: initial" > ${tempAnswer.user.userName} <small>1 month ago</small> </h5>
    <div class="the--comment">
        <p>${tempAnswer.answer}</p>
    </div>


    <div class="ratings">
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="shareCount plus feather feather-chevron-up"><polyline points="18 15 12 9 6 15"></polyline></svg>
        <span class="share share-count share-plus">1</span> <span class="bar">|</span><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                                                                                           class="shareCount minus feather feather-chevron-down"><polyline points="6 9 12 15 18 9"></polyline></svg>
    </div>

</form>
    </c:forEach>
    <br />
    <textarea class=comment rows="5" name="answer" placeholder ="add comment"></textarea>
    <br/>
    <button class="btn btn-secondary"> POST YOUR ANSWER </button>
    <!-- połączyć przycisk z dodawaniem odpowiedzi -->



</div>

<script type = "text/javascript" src="./script/counter.js"></script>

</body>
</html>