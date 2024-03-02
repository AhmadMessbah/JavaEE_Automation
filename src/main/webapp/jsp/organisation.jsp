<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Section</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/organisation.css">
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/css/all.css">
    <link rel="stylesheet" href="../assets/css/sidebar.css">

</head>
<body>
<jsp:include page="../jsp/navbar.jsp"></jsp:include>

<div class="content">
    <div id="org-form">
        <form action="organisation.do" method="post">
            <h1>Create Organization</h1>
            <br>
            <div class="row  mb-4">
                <label class="col form-label" for="name">Name</label>
                <input id="name" class="col form-control" type="text" name="name">
            </div>

            <div class="row mb-4">
                <label class="col form-label" for="title">Title</label>
                <input id="title" class="col form-control" type="text" name="title">
            </div>

            <div class="row mb-4">
                <label class="col form-label" for="address">Address</label>
                <input id="address" class="col form-control" type="text" name="address">
            </div>

            <div class="row mb-4">
                <label class="col form-label" for="phoneNumber">PhoneNumber</label>
                <input id="phoneNumber" class="col form-control" type="text" name="phoneNumber">
            </div>

            <div class="row mb-4">
                <label class="col form-label" for="description">Description</label>
                <input id="description" class="col form-control" type="text" name="description">
            </div>
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
                <th>title</th>
                <th>address</th>
                <th>phoneNumber</th>
                <th>description</th>
                <th>operation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="organisation" items="${sessionScope.organisationList}">
                <tr>
                    <td>${organisation.id}</td>
                    <td>${organisation.name}</td>
                    <td>${organisation.title}</td>
                    <td>${organisation.address}</td>
                    <td>${organisation.phoneNumber}</td>
                    <td>${organisation.description}</td>
                    <td>
                        <button class="btn btn-warning" onclick="edit(${organisation.id})"><i class="fa fa-edit"></i>
                            Edit
                        </button>
                        <button class="btn btn-danger" onclick="remove(${organisation.id})"><i class="fa fa-remove"></i>Remove
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    `
</div>

<jsp:include page="js-import.jsp"></jsp:include>
<script src="../assets/js/organisation.js"></script>
</body>
</html>
