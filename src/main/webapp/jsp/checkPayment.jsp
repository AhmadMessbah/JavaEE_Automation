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
<div class="container-fluid">
    <div id="check-form">
        <form action="checkPayment.do" method="post" enctype="multipart/form-data">
            <div class="row mb-4">
                <label class="col form-label" for="checkNumber">Check Number</label>
                <input id="checkNumber" class="col form-control" type="text" name="checkNumber">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="faCheckDueDate">Fa Check Due Date</label>
                <input id="faCheckDueDate" class="col form-control" type="text" name="faCheckDueDate">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="cashDesk">Cash Desk</label>
                <input id="cashDesk" class="col form-control" type="text" name="cashDesk">
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

    <div id="check-table">
        <table class="table table-hover table-primary">
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
            <c:forEach var="checkPayment" items="${sessionScope.checkPaymentList}">
                <tr>
                    <td>${checkPayment.id}</td>
                    <td>${checkPayment.checkNumber}</td>
                    <td>${checkPayment.faCheckDueDate}</td>
                    <td>${checkPayment.cashDesk}</td>
                    <td>${checkPayment.financialTransaction}</td>
                    <td>
                        <button class="btn btn-warning" onclick="edit(${checkPayment.id})"><i class="fa fa-edit"></i> Edit</button>
                        <button class="btn btn-danger" onclick="remove(${checkPayment.id})"><i class="fa fa-remove"></i>Remove</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="js-import.jsp"></jsp:include>
<script src="../assets/js/checkPayment.js"></script>
</body>
</html>
