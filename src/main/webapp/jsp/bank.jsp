<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bank</title>
    <link rel="stylesheet" href="../assets/css/kamadatepicker.min.css">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/bank.css">
</head>
<body>
<div class="container-fluid">
    <div id="bank-form">
        <form action="bank.do" method="post">
            <div class="row mb-4">
                <label class="col form-label" for="name">Name</label>
                <input id="name" class="col form-control" type="text" name="name">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="accountNumber">Account Number</label>
                <input id="accountNumber" class="col form-control" type="text" name="accountNumber">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="branchCode">Branch Code</label>
                <input id="branchCode" class="col form-control" type="number" name="branchCode">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="branchName">Branch Name</label>
                <input id="branchName" class="col form-control" type="text" name="branchName">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="accountType">Account Type</label>
                <input id="accountType" class="col form-control" type="text" name="accountType">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="accountBalance">Account Balance</label>
                <input id="accountBalance" class="col form-control" type="number" name="accountBalance">
            </div>
            <div class="row mb-4">
                <input type="submit" class="btn btn-primary" value="Save">
            </div>
        </form>
    </div>

    <div id="bank-table">
        <table class="table table-hover table-primary">
            <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>accountNumber</th>
                <th>branchCode</th>
                <th>branchName</th>
                <th>accountType</th>
                <th>accountBalance</th>
                <th>operation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="bank" items="${sessionScope.bankList}">
                <tr>
                    <td>${bank.id}</td>
                    <td>${bank.name}</td>
                    <td>${bank.accountNumber}</td>
                    <td>${bank.branchCode}</td>
                    <td>${bank.branchName}</td>
                    <td>${bank.accountType}</td>
                    <td>${bank.accountBalance}</td>
                    <td>
                        <button class="btn btn-warning" onclick="edit(${bank.id})"><i class="fa fa-edit"></i>
                            Edit
                        </button>
                        <button class="btn btn-danger" onclick="remove(${bank.id})"><i class="fa fa-remove"></i>Remove
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="js-import.jsp"></jsp:include>
<script src="../assets/js/bank.js"></script>
</body>
</html>
