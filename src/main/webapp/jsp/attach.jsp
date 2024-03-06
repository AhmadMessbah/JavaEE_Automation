<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Attach</title>
    <link rel="stylesheet" href="../assets/css/kamadatepicker.min.css">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/attach.css">
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/css/all.css">
    <link rel="stylesheet" href="../assets/css/sidebar.css">
</head>
<body>
<jsp:include page="../jsp/navbar.jsp"></jsp:include>

<div class="content">
    <div id="org-form">
<form id="attach_form" method="post" action="attach.do" enctype="multipart/form-data">
    <h1>Attachment</h1>
    <br>
    <div>
        <label class="form-label" for="file">File</label>
        <input class="form-control" id="file" type="file" name="file">
        <div id="file-msg error">${sessionScope.error}</div>
        <br><br><br>
        <div class="row mb-4">
            <input type="submit" class="btn btn-primary" value="Save">
        </div>
    </div>
</form>
    </div>
</div>
</body>
</html>
