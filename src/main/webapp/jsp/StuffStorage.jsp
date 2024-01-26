<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StuffStorage</title>
    <jsp:include page="css-import.jsp"></jsp:include>


    <style>
    .error {
        display: none;
        color: red;
    }
</style>
</head>
<body>
<form action="/StuffStorage.do" method="post">
    <input type="text" name="stuffName" id="stuffname">
    <input type="text" name="stuffCount" id="stuffCount">
    <input type="submit" value="save">
</form>
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
