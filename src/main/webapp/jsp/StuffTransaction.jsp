<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StuffTransaction</title>
    <jsp:include page="css-import.jsp"></jsp:include>

    <style>
        .error {
            display: none;
            color: red;
        }
    </style>
</head>
<body>
<form action="/stuffTransaction.do"method="post">
    <label for="stuffTransactionUser">stuffTransactionUser</label>
<input type="text"name="stuffTransactionUser"id="stufftransactionuser">
    <label for="stuffTransactionSection">stuffTransactionSection</label>
<input type="text"name="stuffTransactionSection"id="stufftransactionsection">
    <label for="stuffTransactionStuff">stuffTransactionStuff</label>
<input type="text"name="stuffTransactionStuff"id="stufftransactionstuff">
    <label for="stuffTransactionType">stuffTransactionType</label>
    <input type="text"name="stuffTransactionType"id="stufftransactiontype">
    <select name="stuffTransactionType"
    <option value="cash">cash</option>
    <option value="bankDeposit">bankDeposit</option>
    <option value="check">check</option>
            id="stufftransactiontype">
    </select>
<input type="submit" value="save">
</form>

<script>
    function edit(id) {
        alert(id);
    }

    function remove(id) {
        fetch("/api/stuffTransaction/" + id, {
            method: "DELETE"
        }).then(response => {
            JSON.parse(response)
        })
            .then(data => {
                alert(data);
            })
    }
</script>
<h1>StuffTransaction</h1>
<br>
<form action="/servlet/StuffTransaction.do"method="get"></form>
//to do method get stuff transaction.

</body>
</html>
