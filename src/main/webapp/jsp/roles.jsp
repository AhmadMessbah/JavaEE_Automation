<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Role</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/user.css">
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>

<div class="content">
    <div id="org-form">
        <form id="roles_form" action="roles.do" method="post">
            <h1>Set Role</h1>
            <br>
            <div class="row  mb-4">
                <label class="col form-label" for="roleName">Role Name: </label>
                <input id="roleName" class="col form-label" type="text" name="roleName">
            </div>

<%--            <label for="role">Select Role: </label>--%>
<%--            <select name="role" id="role">--%>
<%--                <c:forEach var="role" items="${sessionScope.roleTypes}">--%>
<%--                    <option value="${role}">${role}</option>--%>
<%--                </c:forEach>--%>
<%--            </select>--%>
<%--            <br><br>--%>

            <div class="row  mb-4">
                <label class="col form-label" for="username">Username: </label>
                <input id="username" class="col form-label" type="text" name="username">
            </div>

            <div class="row mb-4">
                <input type="submit" class="btn btn-primary" value="save">
            </div>

            <div class="row mb-4">
                <i>Create User</i>
                <a href="user.do">user</a>
            </div>

        </form>
    </div>

    <jsp:include page="js-import.jsp"></jsp:include>
    <script src="../assets/js/user.js"></script>

</body>
</html>
