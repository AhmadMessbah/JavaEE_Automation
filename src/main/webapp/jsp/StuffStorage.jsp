<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StuffStorage</title>
    <meta charset="UTF-8">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/stuffStorage.css">


    <style>
    .error {
        display: none;
        color: red;
    }
</style>
</head>
<body>
<div>
    <form action="stuffStorage.do" method="post">
        <div>
            <label for="stuffName">stuffName</label>
            <input type="text" name="stuffName" id="stuffName">
        </div>
        <div>
            <label for="stuffCount">stuffCount</label>
            <input type="text" name="stuffCount" id="stuffCount">
        </div>
        <div>
            <input type="submit" value="save">
        </div>
    </form>
</div>

<script>
    function edit(id) {
        alert(id);
    }

    function remove(id) {
        fetch("/api/stuffstorage/" + id, {
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
