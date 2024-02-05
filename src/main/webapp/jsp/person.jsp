<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Person</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/person.css">

</head>
<body>
<div class="container-fluid">
    <div id="org-form">
<%--        --%>
<%--<label for="">select gender: </label><select name="gender" id="">--%>
<%--    <c:forEach var="gender" items="gender">--%>
<%--        <option value="${gender}"></option>--%>
<%--    </c:forEach>--%>
<%--</select>--%>

<form id="person_form" action="person.do" method="post">
    <div class="row  mb-4">
        <label class="col form-label" for="name">Name : </label>
        <input id="name" class="col form-label" type="text" name="name">
    </div>

    <div class="row  mb-4">
        <label class="col form-label" for="family">Family : </label>
        <input id="family" class="col form-label" type="text" name="family">
    </div>

    <div class="row  mb-4">
        <label class="col form-label" for="nationalCode">NationalCode : </label>
        <input id="nationalCode" class="col form-label" type="text" name="nationalCode">
    </div>

<%--    <div class="row  mb-4">--%>
<%--        <label class="form-label" for="file">File</label>--%>
<%--        <input class="form-control" id="file" type="file" name="file">--%>
<%--        <div id="file-msg error">${sessionScope.error}</div>--%>
<%--    </div>--%>

    <div class="row mb-4">
        <input type="submit" class="btn btn-primary" value="Save">
    </div>
</form>
    </div>

    <div id="org-table">
        <table class="table table-hover table-primary">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>family</th>
        <th>nationalCode</th>
        <th>operation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="person" items="${sessionScope.personList}">
        <tr>
            <td>${person.id}</td>
            <td>${person.name}</td>
            <td>${person.family}</td>
            <td>${person.nationalCode}</td>
            <td>
                <button class="btn btn-warning" onclick="edit(${user.id})"><i class="fa fa-edit"></i>Edit</button>
                <button class="btn btn-danger" onclick="remove(${user.id})"><i class="fa fa-remove"></i>Remove</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<jsp:include page="js-import.jsp"></jsp:include>
<script src="../assets/js/user.js"></script>
</body>
</html>