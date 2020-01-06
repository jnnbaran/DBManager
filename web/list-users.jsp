<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<!DOCTYPE html>
<html>
<head>
    <title>KNOWLEDGEBASE APP</title>

  <!--  <link type="text/css" rel="stylesheet" href="css/style.css"> -->
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

    <div class="container">
        <h1 class="display-4">KNOWLEDGEBASE ADMIN SITE</h1>
    </div>



<div class="d-flex flex-column justify-content-center align-items-center">

    <div id="content">

        <input class="input-add" type="button" value="Add new user" align="center" onclick="window.location.href='add-user-form.jsp'; return false; " />
        <button> SHOW EDITORS </button>
        <hr/>

        <table class="table" style="width: 80rem;">
            <thead class="thead-dark">

            <tr>
                <th scope="col" >First Name</th>
                <th scope="col">Role Id</th>
                <th scope="col" >Action</th>
            </tr>

            <c:forEach var="tempUser" items="${USER_LIST}">

                <c:url var="templink" value="UserControllerServlet">
                    <c:param name="command" value="LOAD" />
                    <c:param name="userId" value="${tempUser.userId}"/>
                </c:url>

                <c:url var="deleteLink" value="UserControllerServlet">
                    <c:param name="command" value="DELETE" />
                    <c:param name="userId" value="${tempUser.userId}"/>
                </c:url>


            <tr>
                <td> ${tempUser.userName} </td>
                <td> ${tempUser.roleId} </td>
                <td> <a href="${templink}"> Update </a>
                    |
                    <a href="${deleteLink}"
                    onclick="if (!(confirm('Are you sure you want to delete this user?'))) return false"> Delete </a></td>
            </tr>

            </c:forEach>
            </thead>
        </table>

    </div>




</div>


</body>
</html>
