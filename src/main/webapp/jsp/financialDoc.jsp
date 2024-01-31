<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Financial Doc</title>
    <style>
        .error {
            display: none;
            color: red;
        }
    </style>
</head>
<body>

<select name="docType" id="">
    <c:forEach var="docType" items="docTypes">
        <option value="${docType}"></option>
    </c:forEach>
</select>

<form action="financialDoc.do" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="docNumber">Doc Number</label>
        <input id="docNumber" type="text" name="docNumber">
    </div>
    <div class="mb-3">
        <label for="faDateTime">Fa Date Time</label>
        <input id="faDateTime" type="text" name="faDateTime">
    </div>
    <div class="mb-3">
        <label for="description">description</label>
        <input id="description" type="text" name="description">
    </div>
    <div class="mb-3">
        <label for="financialTransaction">Financial Transaction</label>
        <input id="financialTransaction" type="text" name="financialTransaction">
    </div>
    <input type="submit" value="Save">
</form>

<table class="table table-hover table-dark">
    <thead>
    <tr>
        <th>id</th>
        <th>docNumber</th>
        <th>faDateTime</th>
        <th>description</th>
        <th>financialTransaction</th>
        <th>operation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="financialDoc" items="${sessionScope.financialDocList}">
        <tr>
            <td>${financialDoc.id}</td>
            <td>${financialDoc.docNumber}</td>
            <td>${financialDoc.faDateTime}</td>
            <td>${financialDoc.description}</td>
            <td>${financialDoc.financialTransaction}</td>
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
        fetch("/api/financialDoc/" + id, {
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
