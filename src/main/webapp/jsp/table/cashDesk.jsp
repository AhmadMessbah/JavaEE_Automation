<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>جدول صندوق ها</title>
    <link rel="stylesheet" href="../../assets/css/myTable.css">
    <link rel="stylesheet" href="../../assets/css/all.css">
</head>
<body>
<!--nav bar-->
<jsp:include page="../../jsp/dashboard.jsp"></jsp:include>

<!--table-->
<div id="cashDesk-table">
    <table class="table table-hover table-primary">
        <thead>
        <tr>
            <th><i class="fa fa-window-minimize"></i></th>
            <th>نام صندوق</th>
            <th>شماره صندوق</th>
            <th>موجودی صندوق</th>
            <th>صندوقدار</th>
            <th class="btn"><i class="fa fa-gear"></i></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="cashDesk" items="${sessionScope.cashDeskList}">
            <tr>
                <td>${cashDesk.id}</td>
                <td>${cashDesk.name}</td>
                <td>${cashDesk.cashDeskNumber}</td>
                <td>${cashDesk.cashBalance}</td>
                <td>${cashDesk.user.username}</td>
                <td>
                    <a href="#" onclick="selectCashDesk(${cashDesk.id})"><i class="fas fa-eye show"></i></a>
                    <a href="#" onclick="showEditCashDesk(${cashDesk.id})"><i class="fa fa-edit edit"></i></a>
                    <a href="#" onclick="removeCashDesk(${cashDesk.id})"><i class="fa fa-remove remove"></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="../../assets/js/cashDesk.js"></script>

</body>
</html>