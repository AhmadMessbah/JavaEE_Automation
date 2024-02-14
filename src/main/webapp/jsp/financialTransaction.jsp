<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Financial Transaction</title>
    <link rel="stylesheet" href="../assets/css/kamadatepicker.min.css">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/financialTransaction.css">
</head>
<body>
<div class="container-fluid">
    <div id="financialTransaction-form">
        <form action="financialTransaction.do" method="post" enctype="multipart/form-data">

            <div class="row mb-4">
                <label class="col form-label" for="faDateTime">Fa Date Time</label>
                <input id="faDateTime" class="col form-control" type="text" name="faDateTime" placeholder="Date">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="username">User</label>
                <input id="username" class="col form-control" type="text" name="username" onkeyup="showFind()">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="referringSection">Referring Section</label>
                <input id="referringSection" class="col form-control" type="text" name="referringSection">
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
                <label class="col form-label" for="amount">Amount</label>
                <input id="amount" class="col form-control" type="text" name="amount">
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
                <label class="col form-label" for="depositCode">Deposit Code</label>
                <input id="depositCode" class="col form-control" type="text" name="depositCode">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="bankInvolved">Bank Involved</label>
                <input id="bankInvolved" class="col form-control" type="text" name="bankInvolved"
                       onkeyup="showFindAccountNumber()">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="amount2">Amount</label>
                <input id="amount2" class="col form-control" type="text" name="amount2">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="faDateTime2">Fa Date Time</label>
                <input id="faDateTime2" class="col form-control" type="text" name="faDateTime2" placeholder="Date">
            </div>

            <div class="row mb-4">
                <label class="col form-label" for="checkNumber">Check Number</label>
                <input id="checkNumber" class="col form-control" type="text" name="checkNumber">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="faCheckDueDate">Check Due Date</label>
                <input id="faCheckDueDate" class="col form-control" type="text" name="faCheckDueDate"
                       placeholder="Date">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="cashDesk">Cash Desk</label>
                <select name="cashDesk" id="cashDesk">
                    <c:forEach var="cashDesk" items="${sessionScope.cashDeskList}">
                        <option value="${cashDesk.id}">${cashDesk.id}</option>
                        <option value="${cashDesk.name}">${cashDesk.name}</option>
                        <option value="${cashDesk.cashDeskNumber}">${cashDesk.cashDeskNumber}</option>
                        <option value="${cashDesk.cashDeskNumber}">${cashDesk.cashDeskNumber}</option>
                        <option value="${cashDesk.cashBalance}">${cashDesk.cashBalance}</option>
                        <option value="${cashDesk.cashier}">${cashDesk.cashier}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="amount3">Amount</label>
                <input id="amount3" class="col form-control" type="text" name="amount3">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="faDateTime3">Fa Date Time</label>
                <input id="faDateTime3" class="col form-control" type="text" name="faDateTime3" placeholder="Date">
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
                <th>faDateTime</th>
                <th>user</th>
                <th>referringSection</th>
                <th>paymentType</th>
                <th>amount</th>

                <th>trackingCode</th>
                <th>transactionType</th>
                <th>depositCode</th>
                <th>bankInvolved</th>
                <th>amount2</th>
                <th>faDateTime2</th>

                <th>checkNumber</th>
                <th>faCheckDueDate</th>
                <th>cashDesk</th>
                <th>amount3</th>
                <th>faDateTime3</th>

                <th>operation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="financialTransaction" items="${sessionScope.financialTransactionList}">
                <tr>
                    <td>${financialTransaction.id}</td>
                    <td>${financialTransaction.faDateTime}</td>
                    <td>${financialTransaction.user}</td>
                    <td>${financialTransaction.referringSection}</td>
                    <td>${financialTransaction.paymentType}</td>
                    <td>${financialTransaction.amount}</td>
                    <td>${financialTransaction.trackingCode}</td>
                    <td>${financialTransaction.operation}</td>

                    <td>${financialTransaction.cardPayment.id}</td>
                    <td>${financialTransaction.cardPayment.depositCode}</td>
                    <td>${financialTransaction.cardPayment.bankInvolved}</td>
                    <td>${financialTransaction.cardPayment.amount}</td>
                    <td>${financialTransaction.cardPayment.faDateTime}</td>

                    <td>${financialTransaction.checkPayment.id}</td>
                    <td>${financialTransaction.checkPayment.checkNumber}</td>
                    <td>${financialTransaction.checkPayment.faCheckDueDate}</td>
                    <td>${financialTransaction.checkPayment.cashDesk}</td>
                    <td>${financialTransaction.checkPayment.amount}</td>
                    <td>${financialTransaction.checkPayment.faDateTime}</td>

                    <td>
                        <button class="btn btn-warning" onclick="edit(${financialTransaction.id})"><i class="fa fa-edit"></i>Edit</button>
                        <button class="btn btn-danger" onclick="remove(${financialTransaction.id})"><i class="fa fa-remove"></i>Remove</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="js-import.jsp"></jsp:include>
<script src="../assets/js/financialTransaction.js"></script>
<script src="../assets/js/jquery-3.7.1.min.js"></script>
<script src="../assets/js/kamadatepicker.holidays.js"></script>
<script src="../assets/js/kamadatepicker.min.js"></script>
<script>
    let faDateTime = document.querySelector('#faDateTime');
    let faDateTime2 = document.querySelector('#faDateTime2');
    let faDateTime3 = document.querySelector('#faDateTime3');
    let faCheckDueDate = document.querySelector('#faDateTime3');
    kamaDatepicker(faDateTime);
    kamaDatepicker(faDateTime2);
    kamaDatepicker(faDateTime3);
    kamaDatepicker(faCheckDueDate);

    kamaDatepicker('faDateTime', {buttonsColor: "red", forceFarsiDigits: true});
    kamaDatepicker('faDateTime2', {buttonsColor: "red", forceFarsiDigits: true});
    kamaDatepicker('faDateTime3', {buttonsColor: "red", forceFarsiDigits: true});
    kamaDatepicker('faCheckDueDate', {buttonsColor: "red", forceFarsiDigits: true});
</script>
</body>
</html>
