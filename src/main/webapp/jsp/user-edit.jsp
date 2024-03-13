<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
    <link rel="stylesheet" href="../assets/css/kamadatepicker.min.css">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/user.css">
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/css/all.css">
    <link rel="stylesheet" href="../assets/css/sidebar.css">
</head>
<body>
<jsp:include page="../jsp/navbar.jsp"></jsp:include>

div class="content">
<div id="org-form">
    <form id="myForm" >
        <h1>Edit User</h1>
        <div class="row  mb-4">
            <label class="col form-label" for="username">Person Username</label>
            <input id="username" class="col form-control" type="text" name="username" value="${sessionScope.user.username}">
        </div>

        <div class="row  mb-4">
            <label class="col form-label" for="password">Person Password</label>
            <input id="password" class="col form-control" type="password" name="password" value="${sessionScope.user.password}">
        </div>


        <div class="row  mb-4">
            <label for="role">Select Role: </label>
            <select name="role" id="role">
                <c:forEach var="role" items="${sessionScope.role}">
                    <option value="${role}">${role}</option>
                </c:forEach>
            </select>
        </div>

    </form>
    <button id="submit" class="btn btn-warning" onclick="edit()"><i class="fa fa-edit"></i> Edit</button>
</div>
</div>
<jsp:include page="js-import.jsp"></jsp:include>

<script src="../assets/js/user.js"></script>

<script src="../assets/js/jquery-3.7.1.min.js"></script>
<script>
    function edit() {
        const myForm = document.getElementById("myForm");
        const queryString = new URLSearchParams(new FormData(myForm)).toString();
        fetch("/userEdit.do?" + queryString, {
            method: "PUT"
        }).then(() => {
            document.location.replace("/user.do");
        });
    }
</script>
</body>
</html>
