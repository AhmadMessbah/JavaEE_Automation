<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bank Deposit Transaction</title>
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

<form action="cardPayment.do" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="depositCode">Deposit Code</label>
        <input id="depositCode" type="text" name="depositCode">
    </div>
    <div class="mb-3">
        <label for="bankInvolved">Bank Involved</label>
        <input id="bankInvolved" type="text" name="bankInvolved">
    </div>
    <input type="submit" value="Save">
</form>

<table class="table table-hover table-dark">
    <thead>
    <tr>
        <th>id</th>
        <th>depositCode</th>
        <th>bankInvolved</th>
        <th>operation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cardPayment" items="${sessionScope.bankDepositTransactionList}">
        <tr>
            <td>${cardPayment.id}</td>
            <td>${cardPayment.depositCode}</td>
            <td>${cardPayment.bankInvolved}</td>
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
        fetch("/api/cardPayment/" + id, {
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
