<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>صندوق</title>
    <jsp:include page="../../css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../../../assets/css/form.css">
    <meta charset="UTF-8" lang="fa">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body>
<!--nav bar-->
<jsp:include page="../../../jsp/dashboard.jsp"></jsp:include>

<div class="formbold-main-wrapper">
    <div class="formbold-form-wrapper">
        <div class="formbold-form-title">
            <h2>ایجاد صندوق جدید</h2>
        </div>

        <!--start form-->
        <form action="cashDesk.do" method="post">

            <div class="formbold-form-label">
                <label class="formbold-form-label" for="name">نام صندوق</label>
                <input id="name" class="formbold-form-input" placeholder="نام صندوق را وارد کنید" type="text" name="name">
            </div>

            <div class="formbold-input-group">
                <label for="cashDeskNumber" class="formbold-form-label"> شماره صندوق </label>
                <input type="text" name="cashDeskNumber" id="cashDeskNumber" placeholder="شماره صندوق را وارد کنید" class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="cashBalance" class="formbold-form-label"> موجودی صندوق </label>
                <input type="text" name="cashBalance" id="cashBalance" placeholder="موجودی صندوق را وارد کنید"
                       class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="user" class="formbold-form-label">صندوقدار </label>
                <select name="username" id="user" class="formbold-form-input">
                    <c:forEach items="${sessionScope.userList}" var="user">
                        <option>${user.username}</option>
                    </c:forEach>
                </select>
            </div>

            <button class="a-btn">ثبت</button>
            <c:if test="${not empty param.selectedCashDesk}">
                <a class="a-btn" href="cashDesk.do?cashDeskIdRef=${param.selectedCashDesk}">صندوق ثبت شده</a>
            </c:if>

        </form>
        <!--end form-->
    </div>
</div>
<script src="../../../assets/js/jquery-3.7.1.min.js"></script>
</body>
</html>