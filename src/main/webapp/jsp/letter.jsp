<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>letter</title>
    <link rel="stylesheet" href="../assets/css/kamadatepicker.min.css">
</head>
<body>

    <form id="letter_form" method="post" action="letter.do" enctype="multipart/form-data">
        <label for="l_title">Title : </label>
        <input type="text" id="l_title" name="l_title" placeholder="Title">
        <br><br>
        <label for="l_letter_number">Letter Number : </label>
        <input type="text" id="l_letter_number" name="l_letter_number" placeholder="LetterNumber">
        <br><br>
        <label for="l_context">Context : </label>
        <textarea id="l_context" name="l_context" placeholder="Context"></textarea>
        <br><br>
        <label for="l_receiver_name">Receiver Name : </label>
        <input type="text" id="l_receiver_name" name="l_receiver_name" placeholder="ReceiverName">
        <br><br>
        <label for="l_receiver_title">Receiver Title : </label>
        <input type="text" id="l_receiver_title" name="l_receiver_title" placeholder="ReceiverTitle">
        <br><br>
        <label for="l_sender_name">Sender Name : </label>
        <input type="text" id="l_sender_name" name="l_sender_name" placeholder="SenderName">
        <br><br>
        <label for="l_sender_title">Sender Title : </label>
        <input type="text" id="l_sender_title" name="l_sender_title" placeholder="SenderTitle">
        <br><br>
        <div class="mb-3">
            <label class="form-label" for="file">File</label>
            <input class="form-control" id="file" type="file" name="file">
            <div id="file-msg error">${sessionScope.error}</div>
        </div>

        <br><br><br>
        <label for="">Select AccessLevel: </label>
        <select name="accessLevel" id="">
            <c:forEach var="accessLevel" items="${sessionScope.accessLevels}">
                <option value="${accessLevel}">${accessLevel}</option>
            </c:forEach>
        </select>
        <br><br><br>
        <label for="">Select Transfer Method: </label>
        <select name="transferMethod" id="">
            <c:forEach var="transferMethod" items="${sessionScope.transferMethods}">
                <option value="${transferMethod}">${transferMethod}</option>
            </c:forEach>
        </select>
        <br><br><br>
        <label for="">Select Letter Type: </label>
        <select name="letterType" id="">
            <c:forEach var="letterType" items="${sessionScope.letterTypes}">
                <option value="${letterType}">${letterType}</option>
            </c:forEach>
        </select>
        <br><br><br>

<%--        <label for="l_date">Date : </label>--%>
<%--        <input type="text" id="l_date" name="l_date" placeholder="Date">--%>
<%--        <br><br>--%>
        <input type="submit" value="Save">
    </form>
<br><br>
<table class="table table-hover table-dark">
    <thead>
    <tr>
        <th>id</th>
        <th>title</th>
<%--        <th>date</th>--%>
        <th>receiverName</th>
        <th>receiverTitle</th>
        <th>senderName</th>
        <th>senderTitle</th>
        <th>context</th>
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
<%--            <td>${letter.date}</td>--%>
            <td>${letter.receiverName}</td>
            <td>${letter.receiverTitle}</td>
            <td>${letter.senderName}</td>
            <td>${letter.senderTitle}</td>
            <td>${letter.context}</td>
            <td>${letter.image}</td>
<%--            <td>${letter.user}</td>--%>
            <td>
                <button onclick="edit(${letter.id})"><i class="fa fa-edit"></i> Edit</button>
                <button onclick="remove(${letter.id})"><i class="fa fa-delete"></i>Remove</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<jsp:include page="js-import.jsp"></jsp:include>

<script>
    function edit(id) {
        alert(id);
    }

    function remove(id) {
        fetch("/api/letter/" + id, {
            method: "DELETE"
        }).then(response => {
            JSON.parse(response)
        })
            .then(data => {
                alert(data);
            })
    }
</script>
<%--<br><br><br>--%>
<%--<script src="../assets/js/jquery-3.7.1.min.js"></script>--%>
<%--<script src="../assets/js/kamadatepicker.holidays.js"></script>--%>
<%--<script src="../assets/js/kamadatepicker.min.js"></script>--%>
<%--<script>--%>
<%--    let myElement = document.querySelector('#l_date');--%>
<%--    kamaDatepicker(myElement);--%>

<%--    kamaDatepicker('l_date', { buttonsColor: "red", forceFarsiDigits: true });--%>
<%--</script>--%>

</body>
</html>
