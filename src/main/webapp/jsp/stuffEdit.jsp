<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>stuffEdit</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/stuff.css">


    <style>
        .error {
            display: none;
            color: red;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div id="org-form">
        <form  action="api/stuff/" method="GET" >
            <%--        <div class="row mb-4">--%>
            <%--            <label for="section">Section Name</label>--%>
            <%--            <select name="section" id="section">--%>
            <%--                <c:forEach var="section" items="${sessionScope.sectionList}">--%>
            <%--                    <option value="${section.title}">${section.title}</option>--%>
            <%--                </c:forEach>--%>
            <%--            </select>--%>
            <%--        </div>--%>

            <div class="row mb-4">
                <label for="name">Stuff Name</label>
                <input type="text" id="name" class="col form-control" name="name" placeholder="StuffName">
            </div>
            <div class="row mb-4">
                <label for="brand">Stuff Brand</label>
                <input type="text" id="brand"  class="col form-control" name="brand" placeholder="StuffBrand">
            </div>
            <div class="row mb-4">
                <label for="price">Stuff Price</label>
                <input type="text" id="price"  class="col form-control" name="price" placeholder="StuffPrice">
            </div>
            <div class="row mb-4">
                <label for="model">Stuff Model</label>
                <input type="text" id="model"  class="col form-control" name="model" placeholder="StuffModel">
            </div>
            <div class="row mb-4">
                <label for="status">Stuff Status</label>
                <input type="text" id="status"  class="col form-control" name="status" placeholder="StuffStatus">
            </div>
            <%--        <div class="row md-3">--%>
            <%--            <label class="form-label" for="file">File</label>--%>
            <%--            <input class="form-control" id="file" type="file" name="file">--%>
            <%--            <div id="file-msg error">${sessionScope.error}</div>--%>
            <%--        </div>--%>
            <br><br>
            <div class="row mb-4">
                <input type="submit" class="btn btn-primary" value="Edit">
            </div>
        </form>
    </div>
<%--    <div id="org-table">--%>
<%--        <table class="table table-hover table-primary">--%>
<%--            <thead>--%>
<%--            <tr>--%>
<%--                <th>id</th>--%>
<%--                <th>name</th>--%>
<%--                <th>brand</th>--%>
<%--                <th>price</th>--%>
<%--                <th>model</th>--%>
<%--                <th>status</th>--%>
<%--                <th>section</th>--%>
<%--            </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--            <c:forEach var="stuff" items="${sessionScope.StuffList}">--%>


<%--                <tr>--%>
<%--                    <td>${stuff.id}</td>--%>
<%--                    <td>${stuff.name}</td>--%>
<%--                    <td>${stuff.brand}</td>--%>
<%--                    <td>${stuff.price}</td>--%>
<%--                    <td>${stuff.model}</td>--%>
<%--                    <td>${stuff.status}</td>--%>
<%--                        &lt;%&ndash;                    <td>${stuff.section}</td>&ndash;%&gt;--%>
<%--                    <td>--%>
<%--&lt;%&ndash;                        <button class="btn btn-warning" onclick="edit(${stuff.id})"><i class="fa fa-edit"></i>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            Edit&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </button>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <button class="btn btn-danger" onclick="remove(${stuff.id})"><i class="fa fa-remove"></i>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            Remove&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </button>&ndash;%&gt;--%>
<%--                    </td>--%>
<%--                </tr>--%>


<%--            </c:forEach>--%>

<%--            </tbody>--%>
<%--        </table>--%>
<%--    </div>--%>
</div>
<jsp:include page="js-import.jsp"></jsp:include>
<script src="../assets/js/stuff.js"></script>

</body>
</html>
