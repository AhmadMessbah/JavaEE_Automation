<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Card Payment</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/cardPayment.css">
</head>
<body>

<select name="docType" id="">
    <c:forEach var="docType" items="docTypes">
        <option value="${docType}"></option>
    </c:forEach>
</select>

<form action="cardPayment.do" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="depositCode">Deposit Code</label>
        <input id="depositCode" type="text" name="depositCode">
    </div>
    <div class="mb-3">
        <label for="bankInvolved">Bank Involved</label>
        <input id="bankInvolved" type="text" name="bankInvolved">
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
        <th>depositCode</th>
        <th>bankInvolved</th>
        <th>financialTransaction</th>
        <th>operation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cardPayment" items="${requestScope.cardPaymentList}">
        <tr>
            <td>${cardPayment.id}</td>
            <td>${cardPayment.depositCode}</td>
            <td>${cardPayment.bankInvolved}</td>
            <td>${cardPayment.financialTransaction}</td>
            <td>
                <button onclick="edit(id)"><i class="fa fa-edit"></i> Edit</button>
                <button onclick="remove(id)"><i class="fa fa-delete"></i>Remove</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="js-import.jsp"></jsp:include>
<script src="../assets/js/cardPayment.js"></script>
</body>
</html>
