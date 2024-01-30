<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>stuff</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>


    <style>
        .error {
            display: none;
            color: red;
        }
    </style>
</head>
<body>
<div>
    <form id="stuff-form" action="stuff.do" method="post" enctype="multipart/form-data">
        <div class="row mb-5">
            <label for="name">Stuff Name</label>
            <input type="text" id="name" name="name" placeholder="StuffName">
        </div>
        <div class="row mb-5">
            <label for="brand">Stuff Brand</label>
            <input type="text" id="brand" name="brand" placeholder="StuffBrand">
        </div>
        <div class="row mb-5">
            <label for="price">Stuff Price</label>
            <input type="text" id="price" name="price" placeholder="StuffPrice">
        </div>
        <div class="row mb-5">
            <label for="model">Stuff Model</label>
            <input type="text" id="model" name="model" placeholder="StuffModel">
        </div>
        <div class="row mb-5">
            <label for="status">Stuff Status</label>
            <input type="text" id="status" name="status" placeholder="StuffStatus">
        </div>
        <div class="row md-5">
            <label class="form-label" for="file">File</label>
            <input class="form-control" id="file" type="file" name="file">
            <div id="file-msg error">${sessionScope.error}</div>
        </div>
        <div>
            <input type="submit" value="save">
        </div>
    </form>
</div>

<div id="org-table">
    <table class="table table-hover table-dark">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>brand</th>
            <th>price</th>
            <th>model</th>
            <th>status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="stuff" items="${sessionScope.stuffList}">

            <tr>
                <td>${stuff.id}</td>
                <td>${stuff.name}</td>
                <td>${stuff.brand}</td>
                <td>${stuff.price}</td>
                <td>${stuff.model}</td>
                <td>${stuff.status}</td>

                <td>
                    <button class="btn btn-warning" onclick="edit(stuff.id)"><i class="fa fa-edit"></i> Edit</button>
                    <button class="btn btn-danger" onclick="remove(stuff.id)"><i class="fa fa-delete"></i>Remove</button>
                </td>
            </tr>

        </c:forEach>

        </tbody>
    </table>
</div>
<jsp:include page="js-import.jsp"></jsp:include>
<script src="../assets/js/stuff.js"></script>

</body>
</html>
