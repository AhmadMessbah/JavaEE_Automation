<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>مشاهده صندوق</title>
    <link rel="stylesheet" href="../../../assets/css/displayForm.css">
</head>
<body>
<!--nav bar-->
<jsp:include page="../../../jsp/dashboard.jsp"></jsp:include>

<div class="formbold-main-wrapper">
    <div class="formbold-form-wrapper">
        <p class="success">${sessionScope.ok}</p>

        <div class="formbold-form-title">
            <a class="formbold-btn warning" href="#" onclick="showEditLetter(${sessionScope.cashDesk.id})">ویرایش</a>
            <a class="formbold-btn danger" href="#" onclick="removeLetter(${sessionScope.cashDesk.id})">حذف</a>
        </div>

        <!--start form-->
        <form id="cashDesk-display" inert>
            <div class="formbold-input-flex">
                <input class="form-control" type="text" name="id" value="${sessionScope.cashDesk.id}" hidden="hidden">

                <div>
                    <label class="formbold-form-label" for="name">نام صندوق:</label>
                    <input id="name" class="formbold-form-input" type="text" name="name"
                           value="${sessionScope.cashDesk.name}">
                </div>

                <div>
                    <label class="formbold-form-label" for="cashDeskNumber">شماره صندوق:</label>
                    <input id="cashDeskNumber" class="formbold-form-input" type="number" name="cashDeskNumber"
                           value="${sessionScope.cashDesk.cashDeskNumber}">
                </div>

                <div>
                    <label class="formbold-form-label" for="cashBalance">موجودی صندوق:</label>
                    <input id="cashBalance" class="formbold-form-input" type="number" name="cashBalance"
                           value="${sessionScope.cashDesk.cashBalance}">
                </div>

                <div class="row mb-4">
                    <label class="formbold-form-label" for="user">صندوقدار را انتخاب کنبد:</label>
                    <select name="username" id="user">
                        <c:forEach items="${sessionScope.user.userList}" var="user">
                            <option>${user.username}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="row mb-4">
                    <input type="submit" class="btn btn-primary" value="Save">
                </div>
            </div>
        </form>
    </div>
</div>

<script src="../../../assets/js/cashDesk.js"></script>

</body>
</html>