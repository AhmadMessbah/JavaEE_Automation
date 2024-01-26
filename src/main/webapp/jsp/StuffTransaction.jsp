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
<form action="/stuffTransaction"method="post">
    <label for="stuffTransactionUser">stuffTransactionUser</label>
<input type="text"name="stuffTransactionUser"id="stufftransactionuser">
    <label for="stuffTransactionSection">stuffTransactionSection</label>
<input type="text"name="stuffTransactionSection"id="stufftransactionsection">
    <label for="stuffTransactionStuff">stuffTransactionStuff</label>
<input type="text"name="stuffTransactionStuff"id="stufftransactionstuff">
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
</body>
</html>
