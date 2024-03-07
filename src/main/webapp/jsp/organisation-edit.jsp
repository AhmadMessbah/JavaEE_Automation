<%--
  Created by IntelliJ IDEA.
  User: sabin
  Date: 3/7/2024
  Time: 10:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit organisation</title>
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
        <form id="myForm" >
            <h1>Edit Organization</h1>
            <br>
            <div class="row  mb-4">
                <label class="col form-label" for="name">Name</label>
                <input id="name" class="col form-control" type="text" name="name" value="${sessionScope.organisation.title}">
            </div>

            <div class="row mb-4">
                <label class="col form-label" for="title">Title</label>
                <input id="title" class="col form-control" type="text" name="title">
            </div>

            <div class="row mb-4">
                <label class="col form-label" for="address">Address</label>
                <input id="address" class="col form-control" type="text" name="address"  value="${sessionScope.organisation.address}">
            </div>

            <div class="row mb-4">
                <label class="col form-label" for="phoneNumber">PhoneNumber</label>
                <input id="phoneNumber" class="col form-control" type="text" name="phoneNumber"  value="${sessionScope.organisation.phoneNumber}">
            </div>

            <div class="row mb-4">
                <label class="col form-label" for="description">Description</label>
                <input id="description" class="col form-control" type="text" name="description" value="${sessionScope.organisation.description}" >
            </div>
            <div class="row mb-4">
                <button id="submit" class="btn btn-warning" onclick="edit()"><i class="fa fa-edit"></i> Edit</button>
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
                        <button class="btn btn-danger" onclick="remove(${organisation.id})"><i class="fa fa-remove"></i>Remove
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<jsp:include page="js-import.jsp"></jsp:include>
<script src="../assets/js/organisation.js"></script>
<script>
    function edit() {
        const myForm = document.getElementById("myForm");
        const queryString = new URLSearchParams(new FormData(myForm)).toString();
        fetch("/organisation-edit.do?" + queryString, {
            method: "PUT"
        }).then(() => {
            document.location.replace("/organisation.do");
        });
    }
</script>

</body>
</html>
