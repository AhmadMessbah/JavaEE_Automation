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
<div class="container-fluid">
    <div id="card-form">
        <form action="cardPayment.do" method="post">
            <div class="row mb-4">
                <label class="col form-label" for="depositCode">Deposit Code</label>
                <input id="depositCode" class="col form-control" type="text" name="depositCode">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="bankInvolved">Bank Involved</label>
                <input id="bankInvolved" class="col form-control" type="text" name="bankInvolved">
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

    <div id="card-table">
        <table class="table table-hover table-primary">
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
            <c:forEach var="cardPayment" items="${sessionScope.cardPaymentList}">
                <tr>
                    <td>${cardPayment.id}</td>
                    <td>${cardPayment.depositCode}</td>
                    <td>${cardPayment.bankInvolved}</td>
                    <td>${cardPayment.financialTransaction}</td>
                    <td>
                        <button class="btn btn-warning" onclick="edit(${cardPayment.id})"></i class="fa fa-edit"> Edit</button>
                        <button class="btn btn-danger" onclick="remove(${cardPayment.id})"><i class="fa fa-remove"></i>Remove</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="js-import.jsp"></jsp:include>
<script src="../assets/js/cardPayment.js"></script>
</body>
</html>
