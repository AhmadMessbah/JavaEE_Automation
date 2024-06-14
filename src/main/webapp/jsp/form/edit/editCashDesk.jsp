<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>ویرایش صندوق</title>
    <jsp:include page="../../css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../../../assets/css/form.css">
</head>
<body>
<!--nav bar-->
<jsp:include page="../../../jsp/dashboard.jsp"></jsp:include>

<div class="formbold-main-wrapper">
    <div class="formbold-form-wrapper">
        <div class="formbold-form-title">
            <h2>ویرایش صندوق</h2>
        </div>

        <!--start form-->
        <form id="CashDeskEditForm" >

            <input class="form-control" type="text" name="id" value="${sessionScope.cashDesk.id}" hidden="hidden">

            <div class="formbold-input-group">
                <label for="name" class="formbold-form-label"> نام صندوق </label>
                <input type="text" name="name" id="name" value="${sessionScope.cashDesk.name}"
                       class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="cashDeskNumber" class="formbold-form-label"> شماره صندوق </label>
                <input type="number" name="cashDeskNumber" id="cashDeskNumber"
                       value="${sessionScope.cashDesk.cashDeskNumber}"
                       class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="cashBalance" class="formbold-form-label"> موجودی صندوق </label>
                <input type="number" name="cashBalance" id="cashBalance" value="${sessionScope.cashDesk.cashBalance}"
                       class="formbold-form-input"/>
            </div>

            <div style="text-align: right;">
                <label for="cashier" class="formbold-form-label">صندوقدار:</label>
                <select name="username" id="cashier" class="formbold-form-input">
                    <c:forEach var="user" items="${sessionScope.userList}">
                        <option value="${user.username}" <c:if test="${user.username == sessionScope.cashDesk.cashier.username}">selected</c:if>>
                                ${user.username}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <button id="submit" class="a-btn" onclick="editCashDesk(event)">ویرایش</button>
        </form>
        <!--end form-->
    </div>
</div>

<script src="../../../assets/js/jquery-3.7.1.min.js" defer></script>
<script src="../../../assets/js/cashDesk.js" defer></script>

</body>
</html>
