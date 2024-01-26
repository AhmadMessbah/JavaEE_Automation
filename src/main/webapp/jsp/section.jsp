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

<form action="section.do" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="name">Name</label>
        <input id="name" type="text" name="name">
        <label for="duty">Duty</label>
        <input id="duty" type="text" name="duty">
        <label for="title">Title</label>
        <input id="title" type="text" name="title">
        <label for="phoneNumber">PhoneNumber</label>
        <input id="phoneNumber" type="text" name="phoneNumber">
    </div>

</form>

<table class="table table-hover table-dark">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>duty</th>
        <th>title</th>
        <th>phoneNumber</th>
        <th>operation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="section" items="${sessionScope.sectionsPart}">
        <tr>
            <td>${section.name}</td>
            <td>${section.duty}</td>
            <td>${section.title}</td>
            <td>${section.phoneNumber}</td>
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
        fetch("/api/section/" + id, {
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
