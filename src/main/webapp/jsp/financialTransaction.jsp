<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Financial Transaction</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/user.css">
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/css/all.css">
    <link rel="stylesheet" href="../assets/css/style.css">
    <link rel="stylesheet" href="../assets/css/">
</head>
<body>
<div class="content">
    <div id="org-form">
        <form id="financialTransaction_form" action="/financialTransaction.do" method="post">

            <div class="row mb-4">
                <label for="user">Select user: </label>
                <select name="username" id="user">
                    <c:forEach items="${userList}" var="user">
                        <option>${user.username}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="row mb-4">
                <label for="department">Select department: </label>
                <select name="dId" id="department">
                    <c:forEach items="${departmentList}" var="department">
                        <option value="${department.id}">${department.title}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="row mb-4">
                <label for="bank">Select bank: </label>
                <select name="bankId" id="bank">
                    <option value="${0}">None</option>
                    <c:forEach items="${bankList}" var="bank">
                        <option value="${bank.id}">${bank.accountNumber}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="row mb-4">
                <label class="col form-label" for="bankAmount">Bank Amount</label>
                <input id="bankAmount" class="col form-control" type="text" name="bankAmount">
            </div>

            <div class="row mb-4">
                <label for="cashDesk">Select cashDesk: </label>
                <select name="cashId" id="cashDesk">
                    <option value="${0}">None</option>
                    <c:forEach items="${cashDeskList}" var="cashDesk">
                        <option value="${cashDesk.id}">${cashDesk.cashDeskNumber}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="row mb-4">
                <label class="col form-label" for="cashAmount">Cash Amount</label>
                <input id="cashAmount" class="col form-control" type="text" name="cashAmount">
            </div>

            <div class="row mb-4">
                <label for="check">Select check: </label>
                <select name="checkId" id="check">
                    <option value="${0}">None</option>
                    <c:forEach items="${checkPaymentList}" var="check">
                        <option value="${check.id}">${check.checkNumber}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="row mb-4">
                <label class="col form-label" for="paymentType">Payment Type</label>
                <select name="paymentType" id="paymentType">
                    <c:forEach var="paymentType" items="${sessionScope.paymentTypes}">
                        <option value="${paymentType}">${paymentType}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="row mb-4">
                <label class="col form-label" for="trackingCode">Tracking Code</label>
                <input id="trackingCode" class="col form-control" type="text" name="trackingCode">
            </div>

            <div class="row mb-4">
                <label class="col form-label" for="transactionType">Transaction Type</label>
                <select name="transactionType" id="transactionType">
                    <c:forEach var="transactionType" items="${sessionScope.transactionTypes}">
                        <option value="${transactionType}">${transactionType}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="row mb-4">
                <label for="date" class="formbold-form-label"> تاریخ </label>
                <input type="text" name="date" id="date" placeholder="تاریخ  را وارد کنید" class="formbold-form-input"
                       required/>
            </div>

            <div class="row mb-4">
                <input type="submit" class="btn btn-primary" value="Save">
            </div>
        </form>
    </div>

    <div id="financialTransaction-table">
        <table class="table table-hover table-primary">
            <thead>
            <tr>
                <th>id</th>
                <th>user</th>
                <th>referringDepartment</th>
                <th>bankAmount</th>
                <th>bank</th>
                <th>cashAmount</th>
                <th>cash</th>
                <th>check</th>
                <th>paymentType</th>
                <th>trackingCode</th>
                <th>transactionType</th>
                <th>faDateTime</th>
                <th>operation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="financialTransaction" items="${sessionScope.financialTransactionList}">
                <tr>
                    <td>${financialTransaction.id}</td>
                    <td>${financialTransaction.user.username}</td>
                    <td>${financialTransaction.referringDepartment.title}</td>
                    <td>${financialTransaction.bankAmount}</td>
                    <td>${financialTransaction.bank.accountNumber}</td>
                    <th>${financialTransaction.cashAmount}</th>
                    <th>${financialTransaction.cashDesk.cashDeskNumber}</th>
                    <th>${financialTransaction.checkPayment.checkNumber}</th>
                    <td>${financialTransaction.paymentType}</td>
                    <td>${financialTransaction.trackingCode}</td>
                    <td>${financialTransaction.transactionType}</td>
                    <td>${financialTransaction.date}</td>
                    <td>
                        <button class="btn btn-warning" onclick="editFinancialTransaction(${financialTransaction.id})">
                            <i class="fa fa-edit"></i>Edit
                        </button>
                        <button class="btn btn-danger"
                                onclick="removeFinancialTransaction('${financialTransaction.id}')"><i
                                class="fa fa-remove"></i>Remove
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="../assets/js/financialTransaction.js"></script>
<script src="../assets/js/jquery-3.7.1.min.js"></script>
<script>
    let myElement = document.querySelector('#date');
    kamaDatepicker(myElement);

    kamaDatepicker('date', {buttonsColor: "red", forceFarsiDigits: true});
</script>
</body>
</html>
