<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Home</title>
</head>
<body>
<h1>Home Page</h1>
<br>
<a href="organisation.do">Organisation</a>
<br><br>
<a href="section.do">Sections</a>
<br><br>
<a href="user.do">Users</a>

<h1>Salam</h1>

<c:forEach var="i" begin="1" end="5">
  <h1>${i}</h1>
</c:forEach>
<br>
</body>
</html>