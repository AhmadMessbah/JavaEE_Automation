<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cash Desk</title>
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

<form action="cashDesk.do" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="name">Name</label>
        <input id="name" type="text" name="name">
    </div>
    <div class="mb-3">
        <label for="cashDeskNumber">Cash Desk Number</label>
        <input id="cashDeskNumber" type="number" name="cashDeskNumber">
    </div>
    <div class="mb-3">
        <label for="cashBalance">Cash Balance</label>
        <input id="cashBalance" type="number" name="cashBalance">
    </div>
    <div class="mb-3">
        <label for="cashier">Cashier</label>
        <input id="cashier" type="text" name="cashier">
    </div>
    <input type="submit" value="Save">
</form>

<table class="table table-hover table-dark">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>cashDeskNumber</th>
        <th>cashBalance</th>
        <th>cashier</th>
        <th>operation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cashDesk" items="${sessionScope.cashDeskList}">
        <tr>
            <td>${cashDesk.id}</td>
            <td>${cashDesk.name}</td>
            <td>${cashDesk.cashDeskNumber}</td>
            <td>${cashDesk.cashBalance}</td>
            <td>${cashDesk.cashier}</td>
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
        fetch("/api/cashDesk/" + id, {
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
