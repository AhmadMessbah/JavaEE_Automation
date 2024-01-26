<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>checkTransaction</title>
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

<form action="checkTransaction.do" method="post" enctype="multipart/form-data">
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
    <input type="submit" value="Save">
</form>

<table class="table table-hover table-dark">
    <thead>
    <tr>
        <th>id</th>
        <th>checkNumber</th>
        <th>faCheckDueDate</th>
        <th>cashDesk</th>
        <th>operation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="checkTransaction" items="${sessionScope.checkTransactionList}">
        <tr>
            <td>${checkTransaction.id}</td>
            <td>${checkTransaction.checkNumber}</td>
            <td>${checkTransaction.faCheckDueDate}</td>
            <td>${checkTransaction.cashDesk}</td>
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
        fetch("/api/checkTransaction/" + id, {
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
