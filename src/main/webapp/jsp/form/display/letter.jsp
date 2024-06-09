<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>مشاهده نامه</title>
    <link rel="stylesheet" href="../../../assets/css/displayForm.css">
</head>
<body>
<!--nav bar-->
<jsp:include page="../../../jsp/dashboard.jsp"></jsp:include>

<div class="formbold-main-wrapper">
    <div class="formbold-form-wrapper">
        <p class="success">${sessionScope.ok}</p>
        <!--img-->
        <%--        <img src="../../../assets/image/lettering.jpg" alt="">--%>

        <div class="formbold-form-title">
            <%--            <h2 class="">نامه</h2>--%>
            <a class="formbold-btn primary" href="#" onclick="reference(${sessionScope.letter.id})">ارجاع</a>
            <a class="formbold-btn warning" href="#" onclick="showEditLetter(${sessionScope.letter.id})">ویرایش</a>
            <a class="formbold-btn danger" href="#" onclick="removeLetter(${sessionScope.letter.id})">حذف</a>
        </div>

        <!--start form-->
        <form id="letter-display" inert>
            <div class="formbold-input-flex">
                <input class="form-control" type="text" name="id" value="${sessionScope.letter.id}" hidden="hidden">

                <div>
                    <label for="l_title" class="formbold-form-label"> عنوان : </label>
                    <input type="text" name="l_title" id="l_title" class="formbold-form-input"
                           value="${sessionScope.letter.title}"/>
                </div>
                <div>
                    <label for="l_letter_number" class="formbold-form-label"> شماره نامه : </label>
                    <input type="text" name="l_letter_number" id="l_letter_number" class="formbold-form-input"
                           value="${sessionScope.letter.letterNumber}"/>
                </div>
            </div>

            <div class="formbold-input-flex">
                <div>
                    <label for="l_sender_name" class="formbold-form-label"> نام فرستنده نامه : </label>
                    <input type="text" name="l_sender_name" id="l_sender_name" class="formbold-form-input"
                           value="${sessionScope.letter.senderName}"/>
                </div>
                <div>
                    <label for="l_sender_title" class="formbold-form-label"> عنوان فرستنده نامه : </label>
                    <input type="text" name="l_sender_title" id="l_sender_title" class="formbold-form-input"
                           value="${sessionScope.letter.senderTitle}"/>
                </div>
            </div>

            <div class="formbold-input-flex">
                <div>
                    <label for="l_receiver_name" class="formbold-form-label"> نام گیرنده نامه : </label>
                    <input type="text" name="l_receiver_name" id="l_receiver_name" class="formbold-form-input"
                           value="${sessionScope.letter.receiverName}"/>
                </div>
                <div>
                    <label for="l_receiver_title" class="formbold-form-label"> عنوان گیرنده نامه : </label>
                    <input type="text" name="l_receiver_title" id="l_receiver_title" class="formbold-form-input"
                           value="${sessionScope.letter.receiverTitle}"/>
                </div>
            </div>

            <div class="formbold-input-flex">
                <div>
                    <label for="user" class="formbold-form-label"> کاربر سیستم : </label>
                    <input type="text" name="user" id="user" class="formbold-form-input"
                           value="${sessionScope.letter.user.username}"/>
                </div>
                <div>
                    <label for="accessLevel" class="formbold-form-label"> سطح دسترسی نامه : </label>
                    <input type="text" name="accessLevel" id="accessLevel" class="formbold-form-input"
                           value="${sessionScope.letter.accessLevel.title}"/>
                </div>
            </div>

            <div class="formbold-input-flex">
                <div>
                    <label for="transferMethod" class="formbold-form-label"> روش فرستادن نامه : </label>
                    <input type="text" name="transferMethod" id="transferMethod" class="formbold-form-input"
                           value="${sessionScope.letter.transferMethod.title}"/>
                </div>
                <div>
                    <label for="letterType" class="formbold-form-label"> نوع نامه : </label>
                    <input type="text" name="letterType" id="letterType" class="formbold-form-input"
                           value="${sessionScope.letter.letterType.title}"/>
                </div>
            </div>

            <div class="formbold-input-group">
                <label for="refReceivers" class="formbold-form-label">
                    ارجاع گیرندگان نامه :
                </label>

                <%--                todo : does not work properly--%>
                <select class="formbold-form-select" name="refReceivers" id="refReceivers">
                    <c:forEach var="refReceivers" items="${sessionScope.letter.userList}">
                        <option>${refReceivers.username}</option>
                    </c:forEach>
                </select>
            </div>

            <div>
                <label for="l_context" class="formbold-form-label">
                    متن نامه :
                </label>
                <textarea
                        rows="10"
                        name="l_context"
                        id="l_context"
                        class="formbold-form-input"
                        readonly
                >${sessionScope.letter.context}</textarea>
            </div>

            <div class="formbold-input-flex">
                <div>
                    <label for="l_date" class="formbold-form-label"> تاریخ : </label>
                    <input type="text" name="l_date" id="l_date" class="formbold-form-input"
                           value="${sessionScope.letter.getFaDate()}"/>
                </div>
                <div>
                    <label for="file" class="formbold-form-label"> نام ضمیمه نامه : </label>
                    <input type="text" name="file" id="file" class="formbold-form-input"
                           value="${sessionScope.letter.image}"/>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="../../../assets/js/letter.js"></script>
</body>
</html>
