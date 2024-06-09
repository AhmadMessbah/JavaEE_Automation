<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>نامه</title>
    <jsp:include page="../../css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../../../assets/css/kamadatepicker.min.css">
    <link rel="stylesheet" href="../../../assets/css/form.css">
    <meta charset="UTF-8" lang="fa">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body>
<!--nav bar-->
<jsp:include page="../../../jsp/dashboard.jsp"></jsp:include>

<div class="formbold-main-wrapper">
    <div class="formbold-form-wrapper">
        <!--img-->
        <%--        <img src="../../assets/image/lettering.jpg" alt="">--%>
        <div class="formbold-form-title">
            <h2>ایجاد نامه جدید</h2>
        </div>

        <!--start form-->
        <form action="letter.do" method="post" enctype="multipart/form-data">

            <div class="formbold-input-group">
                <label for="title" class="formbold-form-label"> عنوان </label>
                <input type="text" name="title" id="title" placeholder="عنوان نامه را وارد کنید"
                       class="formbold-form-input"/>
                <%--                <c:if test="${not empty sessionScope.errorMassage}">--%>
                <%--                    <span>${sessionScope.errorMassage}</span>--%>
                <%--                </c:if>--%>
            </div>

            <div class="formbold-input-group">
                <label for="letter_number" class="formbold-form-label"> شماره نامه </label>
                <input type="text" name="letter_number" id="letter_number" placeholder="شماره نامه را وارد کنید"
                       class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="sender_name" class="formbold-form-label"> نام فرستنده نامه </label>
                <input type="text" name="sender_name" id="sender_name" placeholder="نام فرستنده نامه را وارد کنید"
                       class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="sender_title" class="formbold-form-label"> عنوان فرستنده نامه </label>
                <input type="text" name="sender_title" id="sender_title" placeholder="عنوان فرستنده نامه را وارد کنید"
                       class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="receiver_name" class="formbold-form-label"> نام گیرنده نامه </label>
                <input type="text" name="receiver_name" id="receiver_name" placeholder="نام گیرنده نامه را وارد کنید"
                       class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="receiver_title" class="formbold-form-label"> عنوان گیرنده نامه </label>
                <input type="text" name="receiver_title" id="receiver_title"
                       placeholder="عنوان گیرنده نامه را وارد کنید" class="formbold-form-input"/>
            </div>

            <div class=" row formbold-input-group">
                <label class="formbold-form-label"> ارجاع گیرندکان نامه </label>
                <div class="position-relative">
                    <div class="col-12 position-relative">
                        <input class="col-12 formbold-form-input" oninput="getReferences(event)" type="text"
                               placeholder="ارجاع گیرندگان نامه را وارد کنید"/>
                        <div id="person-ref-list" class="border col-12">
                        </div>
                    </div>
                </div>
                <div id="selected-list" class="col-12 row ">
                </div>
            </div>

            <div class="formbold-input-group">
                <label for="accessLevel" class="formbold-form-label">
                    سطح دسترسی نامه را انتخاب کنید
                </label>

                <select class="formbold-form-select" name="accessLevel" id="accessLevel">
                    <c:forEach var="accessLevel" items="${sessionScope.accessLevels}">
                        <option value="${accessLevel}">${accessLevel.title}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="formbold-input-group">
                <label for="transferMethod" class="formbold-form-label">
                    روش فرستادن نامه را انتخاب کنید
                </label>

                <select class="formbold-form-select" name="transferMethod" id="transferMethod">
                    <c:forEach var="transferMethod" items="${sessionScope.transferMethods}">
                        <option value="${transferMethod}">${transferMethod.title}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="formbold-input-group">
                <label for="letterType" class="formbold-form-label">
                    نوع نامه را انتخاب کنید
                </label>

                <select class="formbold-form-select" name="letterType" id="letterType">
                    <c:forEach var="letterType" items="${sessionScope.letterTypes}">
                        <option value="${letterType}">${letterType.title}</option>
                    </c:forEach>
                </select>
            </div>

            <div>
                <label for="context" class="formbold-form-label">
                    متن نامه را وارد کنید
                </label>
                <textarea
                        rows="10"
                        name="context"
                        id="context"
                        placeholder="متن نامه..."
                        class="formbold-form-input"
                ></textarea>
            </div>

            <div class="formbold-input-group">
                <label for="date" class="formbold-form-label"> تاریخ </label>
                <input type="text" name="date" id="date" placeholder="تاریخ نامه را وارد کنید"
                       class="formbold-form-input" required/>
            </div>

            <div class="formbold-form-file-flex">
                <label for="file" class="formbold-form-label">
                    تصویر نامه
                </label>
                <input
                        type="file"
                        name="file"
                        id="file"
                        class="formbold-form-file"
                />
            </div>

            <button class="a-btn">ثبت</button>
            <c:if test="${not empty param.selectedLetter}">
                <a class="a-btn" href="reference.do?letterIdRef=${param.selectedLetter}">ارجاع نامه ثبت شده</a>
            </c:if>

        </form>
        <!--end form-->
    </div>
</div>
<script src="../../../assets/js/jquery-3.7.1.min.js"></script>
<script src="../../../assets/js/kamadatepicker.holidays.js"></script>
<script src="../../../assets/js/kamadatepicker.min.js"></script>
<script src="../../../assets/js/referenceInput.js"></script>
<script>
    let myElement = document.querySelector('#date');
    kamaDatepicker(myElement);

    kamaDatepicker('date', {buttonsColor: "red", forceFarsiDigits: true});
</script>
</body>
</html>
