<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
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
<div class="container-fluid">
    <nav class="navbar navbar-expand-lg navbar-light bg-light position-fixed w-100">
        <a class="navbar-brand" href="#">Project</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="../index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="login.do">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">About Us</a>
                </li>
            </ul>
        </div>
    </nav>
</div>

<!-- The sidebar -->
<div class="sidebar">
    <a href="organisation.do">Organization</a>
    <a href="person.do">Person</a>
    <a href="user.do">User</a>
    <a href="letter.do">Letter</a>
    <a href="reference.do">Reference</a>
</div>

<div class="container-fluid">
    <div id="org-form">
<form id="login_form" action="login.do" method="post">
    <br><br><br>
    <div class="row  mb-4">
        <label class="col form-label" for="username">Username: </label>
        <input id="username" class="col form-label" type="text" name="username">
    </div>

    <div class="row  mb-4">
        <label class="col form-label" for="password">Password: </label>
        <input id="password" class="col form-label" type="password" name="password">
    </div>

    <div class="row mb-4">
        <input type="submit" class="btn btn-primary" value="Login">
    </div>
    <p style="color: red">${sessionScope.wrongUser}</p>

    <div class="row mb-4">
        <i>Not a Member?</i>
        <a href="user.do">register</a>
    </div>

</form>
</div>

<jsp:include page="js-import.jsp"></jsp:include>
    <script src="../assets/js/user.js"></script>
</body>
</html>
