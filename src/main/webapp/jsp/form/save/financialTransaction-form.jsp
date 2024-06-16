<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>تراکنش مالی</title>
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
            <h2>ایجاد تراکنش جدید</h2>
        </div>

        <!--start form-->
        <form action="financialTransaction.do" method="post">

                <div class="formbold-input-group">
                    <label for="user" class="formbold-form-label">Select user: </label>
                    <select name="username" id="user" class="formbold-form-select">
                        <c:forEach items="${userList}" var="user">
                            <option>${user.username}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="formbold-input-group">
                    <label for="department" class="formbold-form-label">Select department: </label>
                    <select name="dId" id="department" class="formbold-form-select">
                        <c:forEach items="${departmentList}" var="department">
                            <option value="${department.id}">${department.title}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="formbold-input-group">
                    <label for="bank" class="formbold-form-label">Select bank: </label>
                    <select name="bankId" id="bank" class="formbold-form-select">
                        <option value="${0}">None</option>
                        <c:forEach items="${bankList}" var="bank">
                            <option value="${bank.id}">${bank.accountNumber}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="formbold-input-group">
                    <label class="formbold-form-label" for="bankAmount">Bank Amount</label>
                    <input id="bankAmount" class="formbold-form-input" type="text" name="bankAmount">
                </div>

                <div class="formbold-input-group">
                    <label for="cashDesk" class="formbold-form-label">Select cashDesk: </label>
                    <select name="cashId" id="cashDesk" class="formbold-form-select">
                        <option value="${0}">None</option>
                        <c:forEach items="${cashDeskList}" var="cashDesk">
                            <option value="${cashDesk.id}">${cashDesk.cashDeskNumber}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="formbold-input-group">
                    <label class="formbold-form-label" for="cashAmount">Cash Amount</label>
                    <input id="cashAmount" class="formbold-form-input" type="text" name="cashAmount">
                </div>

                <div class="formbold-input-group">
                    <label for="check" class="formbold-form-label">Select check: </label>
                    <select name="checkId" id="check" class="formbold-form-select">
                        <option value="${0}">None</option>
                        <c:forEach items="${checkPaymentList}" var="check">
                            <option value="${check.id}">${check.checkNumber}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="formbold-input-group">
                    <label class="formbold-form-label" for="paymentType">Payment Type</label>
                    <select name="paymentType" id="paymentType" class="formbold-form-select">
                        <c:forEach var="paymentType" items="${sessionScope.paymentTypes}">
                            <option value="${paymentType}">${paymentType}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="formbold-input-group">
                    <label class="formbold-form-label" for="trackingCode">Tracking Code</label>
                    <input id="trackingCode" class="formbold-form-input"
                           value="${sessionScope.financialTransaction.trackingCode}" type="text" name="trackingCode">
                </div>

                <div class="formbold-input-group">
                    <label class="formbold-form-label" for="transactionType">Transaction Type</label>
                    <select name="transactionType" id="transactionType" class="formbold-form-select">
                        <c:forEach var="transactionType" items="${sessionScope.transactionTypes}">
                            <option value="${transactionType}">${transactionType}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="formbold-input-group">
                    <label for="date" class="formbold-form-label"> تاریخ </label>
                    <input type="text" name="date" id="date" placeholder="تاریخ  را وارد کنید"
                           value="${sessionScope.financialTransaction.faDate}" class="formbold-form-input">
                </div>

            <button class="a-btn">ثبت</button>
            <c:if test="${not empty param.selectedFinancialTransaction}">
                <a class="a-btn" href="bank.do?bankIdRef=${param.selectedFinancialTransaction}">تراکنش ثبت شده</a>
            </c:if>

        </form>
        <!--end form-->
    </div>
</div>

<script src="../../../assets/js/jquery-3.7.1.min.js"></script>
</body>
</html>
