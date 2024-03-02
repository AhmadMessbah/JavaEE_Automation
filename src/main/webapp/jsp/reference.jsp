<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>reference</title>
    <link rel="stylesheet" href="../assets/css/persian-datepicker.min.css">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/reference.css">
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/css/all.css">
    <link rel="stylesheet" href="../assets/css/sidebar.css">
</head>
<body>
<jsp:include page="../jsp/navbar.jsp"></jsp:include>

<div class="content">
    <div id="org-form">
        <form id="reference_form" method="post" action="reference.do">
            <h1>Reference</h1>
            <br>
            <div class="row  mb-4">
            <label for="letterIdRef">Letter Id</label>
            <input type="text" id="letterIdRef" name="letterIdRef" value="${sessionScope.letterIdRef}">
            </div>

            <div class="row  mb-4">
                <label for="r_refType">Select Reference Type : </label>
                <select name="r_refType" id="r_refType">
                    <c:forEach var="r_refType" items="${sessionScope.refTypes}">
                        <option value="${r_refType}">${r_refType}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="row  mb-4">
                <label for="priority">Select Reference Priority : </label>
                <select name="priority" id="priority">
                    <c:forEach var="priority" items="${sessionScope.priorities}">
                        <option value="${priority}">${priority}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="row  mb-4">
                <p>Paraph</p>
                <label class="col form-label" for="paraph">
                    <textarea id="paraph" class="col form-control" name="paraph"></textarea>
                </label>
            </div>


            <div class="row  mb-4">
                <p>Explanation</p>
                <label class="col form-label" for="explanation">
                    <textarea id="explanation" class="col form-control" name="explanation"></textarea>
                </label>
            </div>

            <div>
                <label for="validate">Validation : </label>
                <input type="checkbox" id="validate" name="validate">
            </div>
            <br><br>

            <div>
                <label for="status">Status : </label>
                <input type="checkbox" id="status" name="status" value="false" onchange="this.checked ?this.value=true:this.value=false">
            </div>
            <br><br>

            <div class="row  mb-4">
                <label for="r_expiration">Expiration Date : </label>
                <input type="text" id="r_expiration" name="r_expiration">
            </div>
            <br><br>
            <div class="row mb-4">
                <input type="submit" class="btn btn-primary" value="Save">
            </div>

        </form>
    </div>
    <div id="org-table">
        <table class="table table-hover table-primary">
            <thead>
            <tr>
                <th>id</th>
                <th>letter</th>
                <th>refType</th>
                <th>priority</th>
                <th>expiration</th>
                <th>paraph</th>
                <th>explanation</th>
                <th>status</th>
                <th>validate</th>
                <th>operation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="reference" items="${sessionScope.referenceList}">
                <tr>
                    <td>${reference.id}</td>
                    <td>${reference.letterId.id}</td>
                    <td>${reference.refType}</td>
                    <td>${reference.priority}</td>
                    <td>${reference.expiration}</td>
                    <td>${reference.paraph}</td>
                    <td>${reference.explanation}</td>
                    <td>${reference.status}</td>
                    <td>${reference.validate}</td>
                    <td>
                        <button class="btn btn-warning" onclick="edit(${reference.id})"><i class="fa fa-edit"></i>
                            Edit
                        </button>
                        <button class="btn btn-danger" onclick="remove(${reference.id})"><i class="fa fa-remove"></i>
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

<script src="../assets/js/reference.js"></script>

<script src="../assets/js/jquery-3.2.1.min.js"></script>
<script src="../assets/js/persian-date.min.js"></script>
<script src="../assets/js/persian-datepicker.min.js"></script>
<script src="../assets/js/app.js"></script>

<%--<script>--%>
<%--    let myElement = document.querySelector('');--%>
<%--    kamaDatepicker(myElement);--%>

<%--    kamaDatepicker('l_date', { buttonsColor: "red", forceFarsiDigits: true });--%>
<%--</script>--%>
</body>
</html>
