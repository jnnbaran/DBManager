<!DOCTYPE html>
<html>

<head>
    <title>Add USER</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>



    <h1> hello, add new user here</h1>
    <hr/>


    <form action="UserControllerServlet" method="GET" style="height:50%">

        <input type="hidden" name="command" value="ADD" />

        <table>
            <tbody>
            <tr>
                <td><label>User name:</label></td>
                <td><input type="text" name="userName" /></td>
            </tr>
            <tr>
                <td><label>Role id</label></td>
                <td><input type="text" name="roleId" /></td>
            </tr>

            <tr>
                <td><label>Password</label></td>
                <td><input type="text" name="password" /></td>
            </tr>


            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save" /></td>
            </tr>

            </tbody>
        </table>
    </form>

    <p>
        <a href="StudentControllerServlet">Back to List</a>
    </p>
</div>



</body>

</html>











