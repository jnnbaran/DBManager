<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<!DOCTYPE html>
<html>
<head>
    <title>KNOWLEDGEBASE APP</title>

 <!--  <link type="text/css" rel="stylesheet" href="css/style.css"> -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"><script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

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

<div class="jumbotron jumbotron-fluid">
    <div class="container">
        <h1 class="display-4">KNOWLEDGEBASE ADMIN SITE</h1>
        <p class="lead" >......</p>
    </div>
</div>


<div class="d-flex flex-column justify-content-center align-items-center">

    <div id="content">

        <input type="button" value="Add user" onclick="window.location.href='add-user-form.jsp'; return false; " />

        <c:forEach var="tempQuestion" items="${QUESTION_LIST}">

        <div class="card"style="width: 60rem;" >
           <h5 class="card-header"> ${tempQuestion.date} </h5>
            <div class="card-body">
                <h5 class="card-title"><strong> ${tempQuestion.title} </strong>  <button class="far fa-trash-alt" style="width: 2rem;height: 2rem;" onclick="if (!(confirm('Are you sure you want to delete this question?'))) return false"></button></h5>

                <p class="card-text"> ${tempQuestion.question} </p>
                <a href="#" class="btn btn-primary">ANSWERS</a>
            </div>

        </div>
            <hr/>

        </c:forEach>

    </div>

!

</div>
</body>
</html>
