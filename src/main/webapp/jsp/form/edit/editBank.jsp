<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>ویرایش بانک</title>
    <jsp:include page="../../css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../../../assets/css/form.css">
</head>
<body>
<!-- Navigation Bar -->
<jsp:include page="../../../jsp/dashboard.jsp"></jsp:include>

<div class="formbold-main-wrapper">
    <div class="formbold-form-wrapper">
        <div class="formbold-form-title">
            <h2>ویرایش بانک</h2>
        </div>

        <!-- Start Form -->
        <form id="bankEditForm">

            <input class="form-control" type="text" name="id" value="${sessionScope.bank.id}" hidden="hidden">

            <div class="formbold-input-group">
                <label for="name" class="formbold-form-label">نام بانک</label>
                <input type="text" name="name" id="name" value="${sessionScope.bank.name}" class="formbold-form-input" />
            </div>

            <div class="formbold-input-group">
                <label for="accountNumber" class="formbold-form-label">شماره حساب</label>
                <input type="text" name="accountNumber" id="accountNumber" value="${sessionScope.bank.accountNumber}" class="formbold-form-input" />
            </div>

            <div class="formbold-input-group">
                <label for="branchCode" class="formbold-form-label">شماره شعبه</label>
                <input type="text" name="branchCode" id="branchCode" value="${sessionScope.bank.branchCode}" class="formbold-form-input" />
            </div>

            <div class="formbold-input-group">
                <label for="branchName" class="formbold-form-label">نام شعبه</label>
                <input type="text" name="branchName" id="branchName" value="${sessionScope.bank.branchName}" class="formbold-form-input" />
            </div>

            <div class="formbold-input-group">
                <label for="accountType" class="formbold-form-label">نوع حساب را انتخاب کنید</label>
                <select name="accountType" id="accountType" class="formbold-form-input">
                    <c:forEach var="type" items="${requestScope.accountTypes}">
                        <option value="${type.name()}" ${type.name() == sessionScope.bank.accountType.name() ? 'selected' : ''}>${type.title}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="formbold-input-group">
                <label for="accountBalance" class="formbold-form-label">موجودی حساب</label>
                <input type="text" name="accountBalance" id="accountBalance" value="${sessionScope.bank.accountBalance}" class="formbold-form-input" />
            </div>

            <button id="submit" class="a-btn" onclick="editBank(event)">ویرایش</button>
        </form>
        <!-- End Form -->
    </div>
</div>

<script src="../../../assets/js/jquery-3.7.1.min.js" defer></script>
<script src="../../../assets/js/bank.js" defer></script>
</body>
</html>