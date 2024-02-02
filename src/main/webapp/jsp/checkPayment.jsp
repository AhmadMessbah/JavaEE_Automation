<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check Payment</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/checkPayment.css">
</head>
<body>

<select name="docType" id="">
    <c:forEach var="docType" items="docTypes">
        <option value="${docType}"></option>
    </c:forEach>
</select>

<form action="checkPayment.do" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="checkNumber">Check Number</label>
        <input id="checkNumber" type="text" name="checkNumber">
    </div>
    <div class="mb-3">
        <label for="faCheckDueDate">Fa Check Due Date</label>
        <input id="faCheckDueDate" type="text" name="faCheckDueDate">
    </div>
    <div class="mb-3">
        <label for="cashDesk">Cash Desk</label>
        <input id="cashDesk" type="text" name="cashDesk">
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
        <th>checkNumber</th>
        <th>faCheckDueDate</th>
        <th>cashDesk</th>
        <th>financialTransaction</th>
        <th>operation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="checkPayment" items="${requestScope.checkPaymentList}">
        <tr>
            <td>${checkPayment.id}</td>
            <td>${checkPayment.checkNumber}</td>
            <td>${checkPayment.faCheckDueDate}</td>
            <td>${checkPayment.cashDesk}</td>
            <td>${checkPayment.financialTransaction}</td>
            <td>
                <button onclick="edit(id)"><i class="fa fa-edit"></i> Edit</button>
                <button onclick="remove(id)"><i class="fa fa-delete"></i>Remove</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="js-import.jsp"></jsp:include>
<script src="../assets/js/checkPayment.js"></script>
</body>
</html>
