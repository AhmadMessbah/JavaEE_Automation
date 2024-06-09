<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>مشاهده بانک</title>
    <link rel="stylesheet" href="../../../assets/css/displayForm.css">
</head>
<body>
<!--nav bar-->
<jsp:include page="../../../jsp/dashboard.jsp"></jsp:include>

<div class="formbold-main-wrapper">
    <div class="formbold-form-wrapper">
        <p class="success">${sessionScope.ok}</p>

        <div class="formbold-form-title">
            <a class="formbold-btn warning" href="#" onclick="showEditBank(${sessionScope.bank.id})">ویرایش</a>
            <a class="formbold-btn danger" href="#" onclick="removeBank(${sessionScope.bank.id})">حذف</a>
        </div>

        <!--start form-->
        <form id="bank-display" inert>
            <div class="formbold-input-flex">
                <input class="form-control" type="text" name="id" value="${sessionScope.bank.id}" hidden="hidden">

                <div>
                    <label for="name" class="formbold-form-label"> نام بانک : </label>
                    <input type="text" name="name" id="name" class="formbold-form-input" value="${sessionScope.bank.name}"/>
                </div>

                <div>
                    <label for="accountNumber" class="formbold-form-label"> شماره حساب : </label>
                    <input type="text" name="accountNumber" id="accountNumber" class="formbold-form-input" value="${sessionScope.bank.accountNumber}"/>
                </div>
            </div>

            <div class="formbold-input-flex">
                <div>
                    <label for="branchCode" class="formbold-form-label"> شماره شعبه : </label>
                    <input type="text" name="branchCode" id="branchCode" class="formbold-form-input" value="${sessionScope.bank.branchCode}"/>
                </div>

                <div>
                    <label for="branchName" class="formbold-form-label"> نام شعبه : </label>
                    <input type="text" name="branchName" id="branchName" class="formbold-form-input" value="${sessionScope.bank.branchName}"/>
                </div>
            </div>

            <div class="formbold-input-flex">
                <div>
                    <label for="accountType" class="formbold-form-label"> نوع حساب : </label>
                    <input type="text" name="accountType" id="accountType" class="formbold-form-input" value="${sessionScope.bank.accountType.title}"/>
                </div>

                <div>
                    <label for="accountBalance" class="formbold-form-label"> موجودی حساب : </label>
                    <input type="text" name="accountBalance" id="accountBalance" class="formbold-form-input" value="${sessionScope.bank.accountBalance}"/>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="../../../assets/js/bank.js"></script>
</body>
</html>