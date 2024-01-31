<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bank</title>
    <style>
        .error {
            display: none;
            color: red;
        }
    </style>
</head>
<body>

<select name="docType" id="">
    <c:forEach var="docType" items="docTypes">
        <option value="${docType}"></option>
    </c:forEach>
</select>

<form action="bank.do" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="name">Name</label>
        <input id="name" type="text" name="name">
    </div>
    <div class="mb-3">
        <label for="accountNumber">Account Number</label>
        <input id="accountNumber" type="text" name="accountNumber">
    </div>
    <div class="mb-3">
        <label for="branchCode">Branch Code</label>
        <input id="branchCode" type="number" name="branchCode">
    </div>
    <div class="mb-3">
        <label for="branchName">Branch Name</label>
        <input id="branchName" type="text" name="branchName">
    </div>
    <div class="mb-3">
        <label for="accountType">Account Type</label>
        <input id="accountType" type="text" name="accountType">
    </div>
    <div class="mb-3">
        <label for="accountBalance">Account Balance</label>
        <input id="accountBalance" type="number" name="accountBalance">
    </div>
    <div class="mb-3">
        <label for="accountOwner">Account Owner</label>
        <input id="accountOwner" type="text" name="accountOwner">
    </div>
    <input type="submit" value="Save">
</form>

<table class="table table-hover table-dark">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>accountNumber</th>
        <th>branchCode</th>
        <th>branchName</th>
        <th>accountType</th>
        <th>accountBalance</th>
        <th>accountOwner</th>
        <th>operation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="bank" items="${applicationScope.bankList}">
        <tr>
            <td>${bank.id}</td>
            <td>${bank.name}</td>
            <td>${bank.accountNumber}</td>
            <td>${bank.branchCode}</td>
            <td>${bank.branchName}</td>
            <td>${bank.accountType}</td>
            <td>${bank.accountBalance}</td>
            <td>${bank.accountOwner}</td>
            <td>
                <button onclick="edit(id)"><i class="fa fa-edit"></i> Edit</button>
                <button onclick="remove(id)"><i class="fa fa-delete"></i>Remove</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="js-import.jsp"></jsp:include>

<script>
    function edit(id) {
        alert(id);
    }

    function remove(id) {
        fetch("/api/bank/" + id, {
            method: "DELETE"
        }).then(response => {
            JSON.parse(response)
        })
            .then(data => {
                alert(data);
            })
    }
</script>
</body>
</html>
