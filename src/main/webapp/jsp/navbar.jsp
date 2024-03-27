<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid">
    <nav class="navbar navbar-expand-lg navbar-light bg-light position-fixed w-100">
        <a class="navbar-brand" href="#">Project</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="../">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../">About Us</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="roles.do">Roles</a>
                </li>
                <c:if test="${not empty pageContext.request.userPrincipal}">
                    <li class="nav-item">
                        <a class="nav-link" href="logout.do">Log Out</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </nav>
</div>
<!-- The sidebar -->
<div class="sidebar">
    <a href="organisation.do">Organization</a>
    <a href="person.do">Person</a>
    <a href="user.do">User</a>
    <a href="letter.do">Letter</a>
    <a href="reference.do">Reference</a>

    <a href="attach.do">Attach</a>
    <a href="#">Bank</a>
    <a href="#">CardPayment</a>
    <a href="#">CashDesk</a>
    <a href="#">CheckPayment</a>
    <a href="#">FinancialDoc</a>
    <a href="#">FinancialTransaction</a>
    <a href="#">Stuff</a>
    <a href="#">StuffStorage</a>
    <a href="#">StuffTransaction</a>
</div>
<%--footer--%>
<div class="footer">
    <p>About Us</p>
</div>
