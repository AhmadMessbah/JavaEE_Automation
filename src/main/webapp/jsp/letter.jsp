<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>letter</title>
    <link rel="stylesheet" href="../assets/css/kamadatepicker.min.css">
</head>
<body>
<label for="">Select AccessLevel: </label><select name="accessLevel" id="">
    <c:forEach var="accessLevel" items="accessLevels">
        <option value="${accessLevel}"></option>
    </c:forEach>
</select>
<br>

    <form id="letter_form" method="post" action="letter.do" enctype="multipart/form-data">
        <label for="title">Title : </label>
        <input type="text" id="title" name="title" placeholder="Title">
        <br><br>
        <label for="letterNumber">Letter Number : </label>
        <input type="text" id="letterNumber" name="letterNumber" placeholder="LetterNumber">
        <br><br>
        <label for="context">Context : </label>
        <textarea id="context" name="context" placeholder="Context"></textarea>
        <br><br>
        <label for="receiverName">Receiver Name : </label>
        <input type="text" id="receiverName" name="receiverName" placeholder="ReceiverName">
        <br><br>
        <label for="receiverTitle">Receiver Title : </label>
        <input type="text" id="receiverTitle" name="receiverTitle" placeholder="ReceiverTitle">
        <br><br>
        <label for="senderName">Sender Name : </label>
        <input type="text" id="senderName" name="senderName" placeholder="SenderName">
        <br><br>
        <label for="senderTitle">Sender Title : </label>
        <input type="text" id="senderTitle" name="senderTitle" placeholder="SenderTitle">
        <br><br>
        <div class="mb-3">
            <label class="form-label" for="file">File</label>
            <input class="form-control" id="file" type="file" name="file">
            <div id="file-msg error">${sessionScope.error}</div>
        </div>
        <br><br><br>
        <label for="faDate">Date : </label>
        <input type="text" id="faDate" name="faDate" placeholder="Date">
        <br><br>
        <input type="submit" value="Save">
    </form>
<br><br>
<table class="table table-hover table-dark">
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
        <th>user</th>
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
            <td>${letter.user}</td>
            <td>
                <button onclick="edit(id)"><i class="fa fa-edit"></i> Edit</button>
                <button onclick="remove(id)"><i class="fa fa-delete"></i>Remove</button>
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
<br><br><br>
<script src="../assets/js/jquery-3.7.1.min.js"></script>
<script src="../assets/js/kamadatepicker.holidays.js"></script>
<script src="../assets/js/kamadatepicker.min.js"></script>
<script>
    let myElement = document.querySelector('#faDate');
    kamaDatepicker(myElement);

    kamaDatepicker('faDate', { buttonsColor: "red", forceFarsiDigits: true });
</script>

</body>
</html>
