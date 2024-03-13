<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Person</title>
    <link rel="stylesheet" href="../assets/css/kamadatepicker.min.css">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/user.css">
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/css/all.css">
    <link rel="stylesheet" href="../assets/css/sidebar.css">
</head>
<body>
<jsp:include page="../jsp/navbar.jsp"></jsp:include>

<div class="content">
    <div id="org-form">
        <form id="myForm" >
            <h1>Edit Person</h1>
            <br>
            <input class="form-control" type="text" name="id" value="${sessionScope.person.id}" hidden="hidden">
            <input class="form-control" type="text" placeholder="ID" value="${sessionScope.person.id}" disabled>
            <br><br>
            <div class="row  mb-4">
                <label class="col form-label" for="name">Person Name</label>
                <input id="name" class="col form-control" type="text" name="name" value="${sessionScope.person.name}">
            </div>

            <div class="row  mb-4">
                <label class="col form-label" for="family">Person Family</label>
                <input id="family" class="col form-control" type="text" name="family" value="${sessionScope.person.family}">
            </div>

            <div class="row  mb-4">
                <label class="col form-label" for="nationalCode">Person NationalCode</label>
                <input id="nationalCode" class="col form-control" type="text" name="nationalCode" value="${sessionScope.person.nationalCode}">
            </div>

            <div class="row  mb-4">
                <label for="gender">Select Gender: </label>
                <select name="gender" id="gender">
                    <c:forEach var="gender" items="${sessionScope.gender}">
                        <option value="${gender}">${gender}</option>
                    </c:forEach>
                </select>
            </div>

        </form>
        <button id="submit" class="btn btn-warning" onclick="edit()"><i class="fa fa-edit"></i> Edit</button>
    </div>
</div>
<jsp:include page="js-import.jsp"></jsp:include>

<script src="../assets/js/person.js"></script>

<script src="../assets/js/jquery-3.7.1.min.js"></script>
<script>
    function edit() {
        const myForm = document.getElementById("myForm");
        const queryString = new URLSearchParams(new FormData(myForm)).toString();
        fetch("/personEdit.do?" + queryString, {
            method: "PUT"
        }).then(() => {
            document.location.replace("/person.do");
        });
    }
</script>
</body>
</html>
