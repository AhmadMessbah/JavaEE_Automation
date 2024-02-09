<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cash Desk</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/cashDesk.css">
</head>
<body>
<div class="container-fluid">
    <div id="cash-form">
        <form action="cashDesk.do" method="post" enctype="multipart/form-data">
            <div class="row mb-4">
                <label class="col form-label" for="name">Name</label>
                <input id="name" class="col form-control" type="text" name="name">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="cashDeskNumber">Cash Desk Number</label>
                <input id="cashDeskNumber" class="col form-control" type="number" name="cashDeskNumber">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="cashBalance">Cash Balance</label>
                <input id="cashBalance" class="col form-control" type="number" name="cashBalance">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="cashier">Cashier</label>
                <input id="cashier" class="col form-control" type="text" name="cashier">
            </div>
            <div class="row mb-4">
                <input type="submit" class="btn btn-primary" value="Save">
            </div>
        </form>
    </div>
    <div id="cash-table">
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
                        <button class="btn btn-warning" onclick="edit(${cashDesk.id})"><i class="fa fa-edit"></i> Edit</button>
                        <button class="btn btn-danger" onclick="remove(${cashDesk.id})"><i class="fa fa-remove"></i>Remove</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="js-import.jsp"></jsp:include>
<script src="../assets/js/cashDesk.js"></script>
</body>
</html>
