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

<select name="docType" id="">
    <c:forEach var="docType" items="docTypes">
        <option value="${docType}"></option>
    </c:forEach>
</select>

<form action="financialDoc.do" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="docNumber">Doc Number</label>
        <input id="docNumber" type="text" name="docNumber">
    </div>
    <div class="mb-3">
        <label for="faDateTime">Fa Date Time</label>
        <input id="faDateTime" type="text" name="faDateTime">
    </div>
    <div class="mb-3">
        <label for="description">description</label>
        <input id="description" type="text" name="description">
    </div>
    <div class="mb-3">
        <label for="financialTransaction">Financial Transaction</label>
        <input id="financialTransaction" type="text" name="financialTransaction">
    </div>
    <input type="submit" value="Save">
</form>

<table class="table table-hover table-dark">
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
    <c:forEach var="financialDoc" items="${requestScope.financialDocList}">
        <tr>
            <td>${financialDoc.id}</td>
            <td>${financialDoc.docNumber}</td>
            <td>${financialDoc.faDateTime}</td>
            <td>${financialDoc.description}</td>
            <td>${financialDoc.financialTransaction}</td>
            <td>
                <button onclick="edit(id)"><i class="fa fa-edit"></i> Edit</button>
                <button onclick="remove(id)"><i class="fa fa-delete"></i>Remove</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="js-import.jsp"></jsp:include>
<script src="../assets/js/financialDoc.js"></script>
</body>
</html>
