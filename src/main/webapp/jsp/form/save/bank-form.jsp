<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>بانک</title>
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
            <h2>ایجاد بانک جدید</h2>
        </div>

        <!--start form-->
        <form action="bank.do" method="post">

            <div class="formbold-input-group">
                <label for="name" class="formbold-form-label"> نام بانک </label>
                <input type="text" name="name" id="name" placeholder="نام بانک را وارد کنید" class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="accountNumber" class="formbold-form-label"> شماره حساب </label>
                <input type="text" name="accountNumber" id="accountNumber" placeholder="شماره حساب را وارد کنید" class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="branchCode" class="formbold-form-label"> شماره شعبه </label>
                <input type="text" name="branchCode" id="branchCode" placeholder="شماره شعبه را وارد کنید" class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="branchName" class="formbold-form-label"> نام شعبه </label>
                <input type="text" name="branchName" id="branchName" placeholder="نام شعبه را وارد کنید" class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="accountType" class="formbold-form-label">
                    نوع حساب را انتخاب کنید
                </label>
                <select class="formbold-form-select" name="accountType" id="accountType">
                    <c:forEach var="accountType" items="${sessionScope.accountType}">
                        <option value="${accountType}">${accountType.title}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="formbold-input-group">
                <label for="accountBalance" class="formbold-form-label"> موجودی حساب </label>
                <input type="text" name="accountBalance" id="accountBalance" placeholder="موجودی حساب را وارد کنید" class="formbold-form-input"/>
            </div>

            <button class="a-btn">ثبت</button>
            <c:if test="${not empty param.selectedBank}">
                <a class="a-btn" href="bank.do?bankIdRef=${param.selectedBank}">بانک ثبت شده</a>
            </c:if>

        </form>
        <!--end form-->
    </div>
</div>
<script src="../../../assets/js/jquery-3.7.1.min.js"></script>
</body>
</html>