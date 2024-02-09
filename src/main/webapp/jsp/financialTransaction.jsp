<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Financial Transaction</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/financialTransaction.css">
</head>
<body>
<div class="container-fluid">
    <div id="financialTransaction-form">
        <form action="financialTransaction.do" method="post" enctype="multipart/form-data">
            <div class="row mb-4">
                <label class="col form-label" for="faDateTime">Fa Date Time</label>
                <input id="faDateTime" class="col form-control" type="text" name="faDateTime">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="user">User</label>
                <input id="user" class="col form-control" type="text" name="user">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="referringSection">Referring Section</label>
                <input id="referringSection" class="col form-control" type="text" name="referringSection">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="paymentType">Payment Type</label>
                <input id="paymentType" class="col form-control" type="text" name="paymentType">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="amount">Amount</label>
                <input id="amount" class="col form-control" type="text" name="amount">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="trackingCode">Tracking Code</label>
                <input id="trackingCode" class="col form-control" type="text" name="trackingCode">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="transactionType">Transaction Type</label>
                <input id="transactionType" class="col form-control" type="text" name="transactionType">
            </div>
            <div class="row mb-4">
                <input type="submit" class="btn btn-primary" value="Save">
            </div>
        </form>
    </div>

    <div id="financialTransaction-table">
        <table class="table table-hover table-primary">
            <thead>
            <tr>
                <th>id</th>
                <th>faDateTime</th>
                <th>user</th>
                <th>referringSection</th>
                <th>paymentType</th>
                <th>amount</th>
                <th>trackingCode</th>
                <th>transactionType</th>
                <th>operation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="financialTransaction" items="${sessionScope.financialTransactionList}">
                <tr>
                    <td>${financialTransaction.id}</td>
                    <td>${financialTransaction.faDateTime}</td>
                    <td>${financialTransaction.user}</td>
                    <td>${financialTransaction.referringSection}</td>
                    <td>${financialTransaction.paymentType}</td>
                    <td>${financialTransaction.amount}</td>
                    <td>${financialTransaction.trackingCode}</td>
                    <td>${financialTransaction.operation}</td>
                    <td>
                        <button class="btn btn-warning" onclick="edit(${financialTransaction.id})"><i
                                class="fa fa-edit"></i> Edit
                        </button>
                        <button class="btn btn-danger" onclick="remove(${financialTransaction.id})"><i
                                class="fa fa-remove"></i>Remove
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="js-import.jsp"></jsp:include>
<script src="../assets/js/financialTransaction.js"></script>
</body>
</html>
