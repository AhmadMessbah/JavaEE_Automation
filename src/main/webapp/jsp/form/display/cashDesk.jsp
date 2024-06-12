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
        <c:if test="${not empty sessionScope.ok}">
            <p class="success">${sessionScope.ok}</p>
        </c:if>

        <div class="formbold-form-title">
            <a class="formbold-btn warning" href="#" onclick="showEditCashDesk(${sessionScope.cashDesk.id})">ویرایش</a>
            <a class="formbold-btn danger" href="#" onclick="removeCashDesk(${sessionScope.cashDesk.id})">حذف</a>
        </div>

        <!--start form-->
        <form id="cashDesk-display" inert>
            <div class="formbold-input-flex">
                <input class="form-control" type="text" name="id" value="${sessionScope.cashDesk.id}" hidden="hidden">

                <div>
                    <label class="formbold-form-label" for="name">نام صندوق:</label>
                    <input id="name" class="formbold-form-input" type="text" name="name" value="${sessionScope.cashDesk.name}" readonly>
                </div>

                <div>
                    <label class="formbold-form-label" for="cashDeskNumber">شماره صندوق:</label>
                    <input id="cashDeskNumber" class="formbold-form-input" type="number" name="cashDeskNumber" value="${sessionScope.cashDesk.cashDeskNumber}" readonly>
                </div>
            </div>

            <div class="formbold-input-flex">
                <div>
                    <label class="formbold-form-label" for="cashBalance">موجودی صندوق:</label>
                    <input id="cashBalance" class="formbold-form-input" type="number" name="cashBalance" value="${sessionScope.cashDesk.cashBalance}" readonly>
                </div>

                <div class="row mb-4">
                    <label class="formbold-form-label" for="user">صندوقدار :</label>
                    <select name="username" id="user" disabled>
                        <c:forEach items="${sessionScope.userList}" var="user">
                            <option value="${user.username}" ${user.username == sessionScope.cashDesk.cashier.username ? 'selected' : ''}>${user.username}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="../../../assets/js/cashDesk.js"></script>
</body>
</html>
