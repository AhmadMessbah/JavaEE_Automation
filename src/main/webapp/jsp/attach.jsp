<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<h1>Profile</h1>
<br>
<form action="/attach.do" method="post">
    <h3 style="background-color: red">${sessionScope.error}</h3>
    <input type="text" name="title">
    <input type="submit" value="Save">
</form>
</body>
</html>
