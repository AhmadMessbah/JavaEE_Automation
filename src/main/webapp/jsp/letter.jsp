<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>letter</title>
</head>
<body>
<div>
    <form id="letter_form" method="post" action="/letter.do">
        <label for="title">Title : </label>
        <input type="text" id="title" name="title" placeholder="Title">

        <label for="letterNumber">Letter Number : </label>
        <input type="text" id="letterNumber" name="letterNumber" placeholder="LetterNumber">

<%--        this has to be in persian--%>
        <label for="faDate">Date : </label>
        <input type="datetime-local" id="faDate" name="faDate" placeholder="Date">

        <label for="context">Context : </label>
        <textarea id="context" name="context" placeholder="Context"></textarea>

        <label for="receiverName">Receiver Name : </label>
        <input type="text" id="receiverName" name="receiverName" placeholder="ReceiverName">

        <label for="receiverTitle">Receiver Title : </label>
        <input type="text" id="receiverTitle" name="receiverTitle" placeholder="ReceiverTitle">

        <label for="senderName">Sender Name : </label>
        <input type="text" id="senderName" name="senderName" placeholder="SenderName">

        <label for="senderTitle">Receiver Title : </label>
        <input type="text" id="senderTitle" name="senderTitle" placeholder="SenderTitle">

        <label for="senderTitle">Receiver Title : </label>
        <input type="text" id="senderTitle" name="senderTitle" placeholder="SenderTitle">

<%--        for an enum --%>
        <label for="accessLevel">Select letter Access Level : </label>
        <select id="accessLevel" name="accessLevel">
            <option value="normal">Normal</option>
            <option value="secret">Secret</option>
        </select>









    </form>
</div>

</body>
</html>
