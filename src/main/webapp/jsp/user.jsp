<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/user.css">
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/css/all.css">
    <link rel="stylesheet" href="../assets/css/style.css">
    <link rel="stylesheet" href="../assets/css/sidebar.css">

</head>
<body>
<jsp:include page="../jsp/navbar.jsp"></jsp:include>

<div class="content">
    <div id="org-form">
<form id="user_form" action="user.do" method="post">
    <h1>Create User</h1>
    <br>
    <div class="row  mb-4">
        <label class="col form-label" for="username">Username: </label>
        <input id="username" class="col form-label" type="text" name="username" required>
        <p style="color: red">${sessionScope.duplicateUsername}</p>
    </div>

    <div class="row  mb-4">
        <label class="col form-label" for="password">Password: </label>
        <input id="password" class="col form-label" type="password" name="password" required>
    </div>

    <label for="role">Select Role: </label>
    <select name="role" id="role">
        <c:forEach var="role" items="${sessionScope.roles}">
            <option value="${role}">${role}</option>
        </c:forEach>
    </select>
    <br><br>

    <div class="row mb-4">
        <input type="submit" class="btn btn-primary" value="Save">
    </div>
    <a href="login.do">Login</a>
</form>
</div>

<div id="org-table">
    <table class="table table-hover table-primary">
    <thead>
    <tr>
        <th>username</th>
        <th>password</th>
        <th>role</th>
        <th>operation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${sessionScope.userList}">
        <tr>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.role}</td>
            <td>
                <button class="btn btn-warning" onclick="edit('${user.username}')"><i class="fa fa-edit"></i>Edit</button>
                <button class="btn btn-danger" onclick="remove('${user.username}')"><i class="fa fa-remove"></i>Remove</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
 </table>
</div>

<jsp:include page="js-import.jsp"></jsp:include>
    <script src="../assets/js/user.js"></script>


</body>
</html>
