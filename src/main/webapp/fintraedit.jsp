<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2/9/2024
  Time: 7:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="financialTransaction-form">
    <form action="financialTransaction.do" method="post" enctype="multipart/form-data">
        <input id="username" type="text" name="username" onkeyup="showFind()">
        <input  type="text" name="checkNumber">
        <input type="text" name="cardNumber">

        <div class="row mb-4">
            <label class="col form-label" for="faDateTime">Fa Date Time</label>
            <input id="faDateTime" class="col form-control" type="text" name="faDateTime">
        </div>
        <div class="row mb-4">
            <label class="col form-label" for="user">User</label>
            <input id="user" class="col form-control" type="text" name="user">
        </div>
        <div class="row mb-4">
            <label class="col form-label" for="referringSection">Referring Section</label>
            <input id="referringSection" value="${sessionScope.finni.reffe}" class="col form-control" type="text" name="referringSection">
        </div>
        <div class="row mb-4">
            <label class="col form-label" for="paymentType">Payment Type</label>
            <input id="paymentType" class="col form-control" type="text" name="paymentType">
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
            <input id="transactionType" class="col form-control" type="text" name="transactionType">
        </div>
        <div class="row mb-4">
            <input type="submit" class="btn btn-primary" value="Save">
        </div>
    </form>
</div>
</body>
<script>
    async function showFind(){
        username_txt = document.getElementById("username");
        username = username_txt.value;
        const response = await fetch("/api/user/findByUsername/"+username,{
            method:"GET"
        });
        const data = await response.json();
        console.log(data);
    }
</script>
</html>
