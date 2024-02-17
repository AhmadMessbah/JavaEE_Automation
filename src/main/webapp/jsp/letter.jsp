<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>letter</title>
    <link rel="stylesheet" href="../assets/css/kamadatepicker.min.css">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/letter.css">
</head>
<body>

<div class="container-fluid">
    <div id="org-form">
    <form id="letter_form" method="post" action="letter.do" enctype="multipart/form-data">
        <br><br>
        <div class="row  mb-4">
            <label class="col form-label" for="l_title">Title</label>
            <input id="l_title" class="col form-control" type="text" name="l_title">
        </div>

        <div class="row  mb-4">
            <label class="col form-label" for="l_letter_number">Letter Number</label>
            <input id="l_letter_number" class="col form-control" type="text" name="l_letter_number">
        </div>

<%--        <div class="row  mb-4">--%>
<%--            <label class="col form-label" for="l_context">Context</label>--%>
<%--            <input id="l_context" class="col form-control" type="text" name="l_context">--%>
<%--        </div>--%>
        <div class="row  mb-4">
            <p>context</p>
            <label class="col form-label" for="l_context">
                <textarea id="l_context" class="col form-control" name="l_context"></textarea>
            </label>
        </div>

        <div class="row  mb-4">
            <label class="col form-label" for="l_receiver_name">Receiver Name</label>
            <input id="l_receiver_name" class="col form-control" type="text" name="l_receiver_name">
        </div>

        <div class="row  mb-4">
            <label class="col form-label" for="l_receiver_title">Receiver Title</label>
            <input id="l_receiver_title" class="col form-control" type="text" name="l_receiver_title">
        </div>

        <div class="row  mb-4">
            <label class="col form-label" for="l_sender_name">Sender Name</label>
            <input id="l_sender_name" class="col form-control" type="text" name="l_sender_name">
        </div>

        <div class="row  mb-4">
            <label class="col form-label" for="l_sender_title">Sender Title</label>
            <input id="l_sender_title" class="col form-control" type="text" name="l_sender_title">
        </div>

        <div class="mb-3">
            <label class="form-label" for="file">File</label>
            <input class="form-control" id="file" type="file" name="file">
            <div id="file-msg error">${sessionScope.error}</div>
        </div>
        <br><br><br>
        <div class="row  mb-4">
            <label for="accessLevel">Select AccessLevel: </label>
            <select name="accessLevel" id="accessLevel">
                <c:forEach var="accessLevel" items="${sessionScope.accessLevels}">
                    <option value="${accessLevel}">${accessLevel}</option>
                </c:forEach>
            </select>
        </div>
        <div class="row  mb-4">
            <label for="transferMethod">Select Transfer Method: </label>
            <select name="transferMethod" id="transferMethod">
                <c:forEach var="transferMethod" items="${sessionScope.transferMethods}">
                    <option value="${transferMethod}">${transferMethod}</option>
                </c:forEach>
            </select>
        </div>
        <div class="row  mb-4">
            <label for="letterType">Select Letter Type: </label>
            <select name="letterType" id="letterType">
                <c:forEach var="letterType" items="${sessionScope.letterTypes}">
                    <option value="${letterType}">${letterType}</option>
                </c:forEach>
            </select>
        </div>

        <div class="row  mb-4">
            <label for="l_date">Date : </label>
            <input type="text" id="l_date" name="l_date">
        </div>
<%--        <label for="user">bbbb</label><input type="text" name="user" id="user">--%>
<%--        <input type="text" name="user" id="user">--%>
<%--        <input type="text" name="user" id="user">--%>

        <div class="row mb-4">
            <input type="submit" class="btn btn-primary" value="Save">
        </div>

        <div class="row mb-4">
        <a href="reference.do?letterIdRef=${param.selectedLetter}">Reference</a>
        </div>

    </form>
    </div>

    <div id="org-table">
        <table class="table table-hover table-primary">
            <thead>
            <tr>
                <th>id</th>
                <th>title</th>
                <th>date</th>
                <th>receiverName</th>
                <th>receiverTitle</th>
                <th>senderName</th>
                <th>senderTitle</th>
                <th>context</th>
                <th>AccessLevel</th>
                <th>Letter Type</th>
                <th>Transfer Method</th>
                <th>file</th>
        <%--        <th>user</th>--%>
                <th>operation</th>
            </tr>
        </thead>
    <tbody>
    <c:forEach var="letter" items="${sessionScope.letterList}">
        <tr>
            <td>${letter.id}</td>
            <td>${letter.title}</td>
            <td>${letter.date}</td>
            <td>${letter.receiverName}</td>
            <td>${letter.receiverTitle}</td>
            <td>${letter.senderName}</td>
            <td>${letter.senderTitle}</td>
            <td>${letter.context}</td>
            <td>${letter.accessLevel}</td>
            <td>${letter.letterType}</td>
            <td>${letter.transferMethod}</td>
            <td>${letter.image}</td>
<%--            <td>${letter.user}</td>--%>
            <td>
                <button class="btn btn-warning" onclick="edit(${letter.id})"><i class="fa fa-edit"></i>
                    Edit
                </button>
                <button class="btn btn-danger" onclick="remove(${letter.id})"><i class="fa fa-remove"></i>
                    Remove
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    </div>
</div>
<jsp:include page="js-import.jsp"></jsp:include>

<script src="../assets/js/letter.js"></script>

<script src="../assets/js/jquery-3.7.1.min.js"></script>
<script src="../assets/js/kamadatepicker.holidays.js"></script>
<script src="../assets/js/kamadatepicker.min.js"></script>
<script>
    let myElement = document.querySelector('#l_date');
    kamaDatepicker(myElement);

    kamaDatepicker('l_date', { buttonsColor: "red", forceFarsiDigits: true });
</script>

</body>
</html>
