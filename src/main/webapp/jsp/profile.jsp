<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<h1>Profile</h1>
<br>
<form action="/api/profile" method="post">
    <input type="text" name="nationalCode">
    <input type="submit" value="Save">
</form>
</body>
</html>
