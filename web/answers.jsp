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
        </form>
        <hr/>
<br>
    <h3> ANSWERS </h3>

    <c:forEach var="tempAnswer" items="${ANSWER_LIST}">
        <c:url var="upvote" value="AnswerServlet">
            <c:param name="command" value="updateRating" />
            <c:param name="userId" value="${tempAnswer.userId}"/>
            <c:param name="answerId" value="${tempAnswer.answerId}"/>
            <c:param name="questionId" value="${tempAnswer.questionId}"/>
            <c:param name="vote" value="upvote"/>

        </c:url>
        <c:url var="downvote" value="AnswerServlet">
            <c:param name="command" value="updateRating" />
            <c:param name="userId" value="${tempAnswer.userId}"/>
            <c:param name="answerId" value="${tempAnswer.answerId}"/>
            <c:param name="questionId" value="${tempAnswer.questionId}"/>
            <c:param name="vote" value="downvote"/>
        </c:url>
        <c:url var="newComment" value="AnswerServlet">
            <c:param name="command" value="addComment" />
            <c:param name="answerId" value="${tempAnswer.answerId}"/>
        </c:url>
    <form action="AnswerServlet" method="get" style="display: flex; flex-direction: row;">

        <div style="display: flex; flex-direction: column; padding: 1rem;">
            <a href="${upvote}" class="btn btn-secondary"style="background-color: seagreen" >
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"> <path d="M12 0l8 9h-6v15h-4v-15h-6z"/></svg>
            </a>
            <span class="share share-count share-plus">${tempAnswer.rating}</span>
            <a href="${downvote}" class="btn btn-secondary"style="background-color: indianred"  name="vote" value="downvote">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M12 24l-8-9h6v-15h4v15h6z"/></svg>
            </a>
        </div>

        <div style="background-color: #f6f5f7; width: 60rem" >
            <h5 class="card-header" style="position: initial" > ${tempAnswer.user.userName}  </h5>

            <div class="the--comment" style="float:left; flex-direction: row">
                <p style="text-align: initial">${tempAnswer.answer}</p>
            </div>
        </div>

    </form>

        <form style=" display: flex; align-items: end; justify-content: end;" action="AnswerServlet" method="get">

            <div class = "comments"  >
                    <p style="text-align: initial; font-size: small ">${tempAnswer.comments}</p>

                <hr/><hr/>
            </div>
            <div style="display: flex; flex-direction: row; justify-content: flex-end">
                    <textarea style="width: 40rem; color: #bbbbbb"  rows="1"  name="comment" placeholder ="add a comment" ></textarea>
                <input hidden name="answerId" value="${tempAnswer.answerId}">
                    <button name="command" value="addComment"> ADD </button>
            </div>


            <hr/>
        </form>
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