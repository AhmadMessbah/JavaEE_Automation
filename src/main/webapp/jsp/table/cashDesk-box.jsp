<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../../jsp/css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../../assets/css/letterBox.css">
    <meta charset="UTF-8" lang="fa">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<!--nav bar-->
<jsp:include page="../../jsp/dashboard.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="card-body card-color text-white mailbox-widget pb-0">

                    <!--header-->
                    <h4 class="text-white pb-3">صندوق</h4>

                    <!--navbar start-->
                    <ul class="nav nav-tabs custom-tab border-bottom-0 mt-4" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="inbox-tab" data-bs-toggle="tab" aria-controls="cashDesk"
                               href="#cashDesk" role="tab" aria-selected="true">
                                <span class="d-block d-md-none"><i class="ti-email"></i></span>
                                <span class="d-none d-md-block"> بانک</span>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade active show" id="cashDesk" aria-labelledby="inbox-tab" role="tabpanel">
                        <div>
                            <div class="row p-4 no-gutters align-items-center">
                                <div class="col-sm-12 col-md-6">
                                    <h4 class="font-light mb-0"><i class="ti-email mr-2"></i>صندوق ها</h4>
                                </div>

                                <!--add new and delete-->
                                <div class="col-sm-12 col-md-6">
                                    <ul class="list-inline dl mb-0 d-flex flex-row-reverse">
                                        <li class="list-inline-item text-danger">
                                            <a href="#">
                                                <button class="btn btn-circle btn-danger text-white"
                                                        href="javascript:void(0)">
                                                    <i class="fa fa-trash"></i>
                                                </button>
                                                <span class="ml-2 font-normal text-dark">حذف</span>
                                            </a>
                                        </li>
                                        <li class="list-inline-item text-info mr-3">
                                            <a href="cashDesk.do">
                                                <button class="btn btn-circle btn-success text-white"
                                                        href="javascript:void(0)">
                                                    <i class="fa fa-plus"></i>
                                                </button>
                                                <span class="ml-2 font-normal text-dark">افزودن</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <!--add new and delete end-->

                            <div class="table-responsive">
                                <table class="table email-table no-wrap table-hover v-middle mb-0 font-14">
                                    <tbody>
                                    <c:forEach var="cashDesk" items="${sessionScope.cashDeskListByName}">
                                        <tr>

                                            <td class="pl-3">
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="cst1"/>
                                                    <label class="custom-control-label" for="cst1"> </label>
                                                </div>
                                            </td>

                                            <td><i class="fa fa-star text-warning"></i></td>
                                            <td>
                                                <span class="mb-0 text-muted">${cashDesk.name}</span>
                                            </td>

                                            <td>
                                                <span class="text-dark">${cashDesk.cashDeskNumber}</span>
                                            </td>

                                            <td class="d-flex justify-content-end">
                                                <a href="#" class="text-dark p-2 bg-warning rounded"
                                                   onclick="selectCashDesk(${cashDesk.id})">مشاهده صندوق </a>
                                                <a href="#" class="text-dark p-2 bg-warning rounded"
                                                   onclick="showEditCashDesk(${cashDesk.id})">ویرایش صندوق </a>
                                            </td>

                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../../jsp/js-import.jsp"></jsp:include>

<script src="../../assets/js/cashDesk.js"></script>
<script type="text/javascript"></script>

</body>
</html>