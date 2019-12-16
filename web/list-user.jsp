<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <title>User Tracker App</title>
</head>


<body>

<div id="wrapper">
    <div id="header">
        <h2>FooBar University</h2>
    </div>
</div>

<div id="container">

    <div id="content">

        <table>

            <tr>
                <th>id</th>
                <th>User Name</th>
                <th>Password</th>
            </tr>

            <% for (User tempUser : theUsers) { %>

            <tr>
                <td> <%= tempUser.getUserId() %> </td>
                <td> <%= tempUser.getUserName() %> </td>
                <td> <%= tempUser.getPassword() %> </td>
            </tr>

            <% } %>

        </table>

    </div>

</div>
</body>


</html>








