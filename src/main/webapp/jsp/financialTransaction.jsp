<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Financial Transaction</title>
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

<form action="financialTransaction.do" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="trackingCode">Tracking Code</label>
        <input id="trackingCode" type="number" name="trackingCode">
    </div>
    <div class="mb-3">
        <label for="amount">Amount</label>
        <input id="amount" type="number" name="amount">
    </div>
    <div class="mb-3">
        <label for="description">Description</label>
        <input id="description" type="number" name="description">
    </div>
    <div class="mb-3">
        <label for="faDateTime">Fa Date Time</label>
        <input id="faDateTime" type="text" name="faDateTime">
    </div>
    <div class="mb-3">
        <label for="payer">Payer</label>
        <input id="payer" type="text" name="payer">
    </div>
    <div class="mb-3">
        <label for="referringSection">Referring Section</label>
        <input id="referringSection" type="text" name="referringSection">
    </div>
    <div class="mb-3">
        <label for="financialDoc">Financial Doc</label>
        <input id="financialDoc" type="text" name="financialDoc">
    </div>
    <input type="submit" value="Save">
</form>

<table class="table table-hover table-dark">
    <thead>
    <tr>
        <th>id</th>
        <th>trackingCode</th>
        <th>amount</th>
        <th>description</th>
        <th>faDateTime</th>
        <th>payer</th>
        <th>referringSection</th>
        <th>financialDoc</th>
        <th>operation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="financialTransaction" items="${sessionScope.financialTransactionList}">
        <tr>
            <td>${financialTransaction.id}</td>
            <td>${financialTransaction.trackingCode}</td>
            <td>${financialTransaction.amount}</td>
            <td>${financialTransaction.description}</td>
            <td>${financialTransaction.faDateTime}</td>
            <td>${financialTransaction.payer}</td>
            <td>${financialTransaction.referringSection}</td>
            <td>${financialTransaction.financialDoc}</td>
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
        fetch("/api/financialTransaction/" + id, {
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
