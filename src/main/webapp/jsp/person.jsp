<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Person</title>
    <jsp:include page=""></jsp:include>


    <style>
        .error {
            display: none;
            color: red;
        }
    </style>
</head>
<body>

<label for="">select gender: </label><select name="gender" id="">
    <c:forEach var="gender" items="gender">
        <option value="${gender}"></option>
    </c:forEach>
</select>

<form action="person.do" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="name">Name</label>
        <input id="name" type="text" name="name">
    </div>

    <div class="mb-3">
        <label for="family">Family</label>
        <input id="family" type="text" name="family">
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
        <th>name</th>
        <th>family</th>
        <th>operation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="person" items="${sessionScope.personList}">
        <tr>
            <td>${person.id}</td>
            <td>${person.name}</td>
            <td>${person.family}</td>
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
