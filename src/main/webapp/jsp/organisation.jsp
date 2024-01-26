<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Section</title>
    <jsp:include page=""></jsp:include>


    <style>
        .error {
            display: none;
            color: red;
        }
    </style>
</head>
<body>

<form action="organisation.do" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="name">Name</label>
        <input id="name" type="text" name="name">
        <label for="title">Title</label>
        <input id="title" type="text" name="title">
        <label for="address">Address</label>
        <input id="address" type="text" name="address">
        <label for="phoneNumber">PhoneNumber</label>
        <input id="phoneNumber" type="text" name="phoneNumber">
        <label for="description">Description</label>
        <input id="description" type="text" name="description">
    </div>

</form>

<table class="table table-hover table-dark">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>title</th>
        <th>address</th>
        <th>phoneNumber</th>
        <th>description</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="organisation" items="${sessionScope.organisation}">
        <tr>
            <td>${organisation.id}</td>
            <td>${organisation.name}</td>
            <td>${organisation.title}</td>
            <td>${organisation.address}</td>
            <td>${organisation.phoneNumber}</td>
            <td>${organisation.description}</td>
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
        fetch("/api/organisation/" + id, {
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
