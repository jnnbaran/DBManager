<!DOCTYPE html>
<html>

<head>
    <title>Update User</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>

<body>
<div id="wrapper">
    <div id="header">
        <h2>KNOWLEDGEBASE USERS</h2>
    </div>
</div>

<div id="container">
    <h3>Update User</h3>

    <form action="UserControllerServlet" method="GET">

        <input type="hidden" name="command" value="UPDATE" />

        <input type="hidden" name="userId" value="${THE_USER.userId}" />

        <table>
            <tbody>
            <tr>
                <td><label>User name:</label></td>
                <td><input type="text" name="userName"
                           value="${THE_USER.userName}" /></td>
            </tr>

            <tr>
                <td><label>role id:</label></td>
                <td><input type="text" name="roleId"
                           value="${THE_USER.roleId}" /></td>
            </tr>

            <tr>
                <td><label>Password:</label></td>
                <td><input type="text" name="password"
                           value="${THE_USER.password}" /></td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save" /></td>
            </tr>

            </tbody>
        </table>
    </form>

    <div style="clear: both;"></div>

    <p>
        <a href="StudentControllerServlet">Back to List</a>
    </p>
</div>
</body>

</html>











