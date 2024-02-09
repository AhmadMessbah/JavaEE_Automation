<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Financial Doc</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/financialDoc.css">
</head>
<body>
<div class="container-fluid">
    <div id="fdoc-form">
        <form action="financialDoc.do" method="post" enctype="multipart/form-data">
            <div class="row mb-4">
                <label class="col form-label" for="docNumber">Doc Number</label>
                <input id="docNumber" class="col form-control" type="text" name="docNumber">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="faDateTime">Fa Date Time</label>
                <input id="faDateTime" class="col form-control" type="text" name="faDateTime">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="description">description</label>
                <input id="description" class="col form-control" type="text" name="description">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="financialTransaction">Financial Transaction</label>
                <input id="financialTransaction" class="col form-control" type="text" name="financialTransaction">
            </div>
            <div class="row mb-4">
                <input type="submit" class="btn btn-primary" value="Save">
            </div>
        </form>
    </div>

    <div id="fdoc-table">
        <table class="table table-hover table-primary">
            <thead>
            <tr>
                <th>id</th>
                <th>docNumber</th>
                <th>faDateTime</th>
                <th>description</th>
                <th>financialTransaction</th>
                <th>operation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="financialDoc" items="${sessionScope.financialDocList}">
                <tr>
                    <td>${financialDoc.id}</td>
                    <td>${financialDoc.docNumber}</td>
                    <td>${financialDoc.faDateTime}</td>
                    <td>${financialDoc.description}</td>
                    <td>${financialDoc.financialTransaction}</td>
                    <td>
                        <button class="btn btn-warning" onclick="edit(${financialDoc.id})"><i class="fa fa-edit"></i>
                            Edit
                        </button>
                        <button class="btn btn-danger" onclick="remove(${financialDoc.id})"><i class="fa fa-remove"></i>Remove
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="js-import.jsp"></jsp:include>
<script src="../assets/js/financialDoc.js"></script>
</body>
</html>
