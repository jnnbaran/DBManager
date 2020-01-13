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

<%@ include file="/navigation.jsp" %>


<div class="jumbotron background-color: rgb(43, 160, 255)"  >

    <div class="header">
        <div class="container">
            <h1 class="display-4"> KNOWLEDGEBASE </h1>
            <p class="lead" ></p>
        </div>
    </div>


    <div class="d-flex flex-column justify-content-center align-items-center">

        <hr/>
        <form action="AnswerServlet" method="get">

        <div class="card"style="width: 60rem;" >
            <h5 class="card-header"> ${THE_QUESTION.date} asked by:  </h5>
            <div class="card-body">
                <h5 class="card-title" > ${THE_QUESTION.title}</h5>
                <p class="card-text"> ${THE_QUESTION.question} </p>
                <p name="questionId"> ${THE_QUESTION.questionId}</p>

                <input type=hidden name="questionId" value="${THE_QUESTION.questionId}">

            </div>

        </div>
        <hr/>
<br>
    <h3> ANSWERS </h3>

    <c:forEach var="tempAnswer" items="${ANSWER_LIST}">

        <div style="background-color: #f6f5f7; width: 60rem;" >
            <h5 class="card-header" style="position: initial" > ${tempAnswer.user.userName} <small>1 month ago</small> </h5>
            <div class="the--comment">
                <p style="text-align: initial">${tempAnswer.answer}</p>
            </div>


            <div class="ratings">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="shareCount plus feather feather-chevron-up"><polyline points="18 15 12 9 6 15"></polyline></svg>
                <span class="share share-count share-plus">0</span>
            </div>
            <div class = "comments">
                <p style="text-align: initial; font-size: small "> random comments</p>
            <hr/>
                <p style="text-align: initial; font-size: small "> random comments 3 </p>
            <hr/>
                <textarea style="width: 60rem; color: #bbbbbb"  rows="1"  name="comment" placeholder ="add a comment" ></textarea>
            </div>

    </div>
        <hr/>
    </c:forEach>
    <br />
    <textarea style="width: 60rem"  rows="5"  name="answer" placeholder ="your answer"></textarea>
    <br/>
    <button class="btn btn-secondary" name="command" value="ADD"> POST YOUR ANSWER </button>
    <!-- połączyć przycisk z dodawaniem odpowiedzi -->
        </form>


</div>
</div>

<script type = "text/javascript" src="./script/counter.js"></script>

</body>
</html>