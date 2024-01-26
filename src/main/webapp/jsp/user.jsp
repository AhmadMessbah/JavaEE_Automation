<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <jsp:include page=""></jsp:include>
</head>
<body>

<label for="">select role: </label><select name="role" id="">
    <c:forEach var="role" items="role">
        <option value="${role}"></option>
    </c:forEach>
</select>

<form action="user.do" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="username">Username</label>
        <input id="username" type="text" name="username">
    </div>
    <div class="mb-3">
        <label for="password">Password</label>
        <input id="password" type="password" name="password">
    </div>
    <div class="mb-3">
        <label for="nationalCode">NationalCode</label>
        <input id="nationalCode" type="text" name="nationalCode">
    </div>
    <div class="mb-3">
        <label class="form-label" for="file">File</label>
        <input class="form-control" id="file" type="file" name="file">
        <div id="file-msg error">${sessionScope.error}</div>
    </div>
    <input type="submit" value="Save">
</form>

<table class="table table-hover table-dark">
    <thead>
    <tr>
        <th>id</th>
        <th>username</th>
        <th>password</th>
        <th>nationalCode</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${sessionScope.userList}">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.nationalCode}</td>

            <td>
                <button onclick="edit(id)"><i class="fa fa-edit"></i> Edit</button>
                <button onclick="remove(id)"><i class="fa fa-delete"></i>Remove</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="js-import.jsp"></jsp:include>

<script>
    function edit(id) {
        alert(id);
    }

    function remove(id) {
        fetch("/api/person/" + id, {
            method: "DELETE"
        }).then(response => {
            JSON.parse(response)
        })
            .then(data => {
                alert(data);
            })
    }
</script>
</body>
</html>
