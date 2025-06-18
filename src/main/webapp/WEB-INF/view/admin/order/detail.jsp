<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content="Hỏi Dân IT - Dự án laptopshop"/>
    <meta name="author" content="Hỏi Dân IT"/>
    <title>Detail Order</title>
    <link href="/css/styles.css" rel="stylesheet"/>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>

<body class="sb-nav-fixed">
<jsp:include page="../layout/header.jsp"/>
<div id="layoutSidenav">
    <jsp:include page="../layout/sidebar.jsp"/>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Management Product</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="/admin/home">Dashboard</a></li>
                    <li class="breadcrumb-item"><a href="/admin/order">Orders</a></li>
                    <li class="breadcrumb-item active">Detail</li>
                </ol>
                <div class="mt-5">
                    <div class="row">
                        <div class="col-12 mx-auto">
                            <div class="d-flex justify-content-between">
                                <h3>Order detail with id = ${order.id} </h3>
                            </div>
                            <hr/>
                            <table class="table table-bordered table-hover align-middle text-center">
                                <thead class="table-light">
                                <tr>
                                    <th scope="col">Sản phẩm</th>
                                    <th scope="col">Tên</th>
                                    <th scope="col">Giá cả</th>
                                    <th scope="col">Số lượng</th>
                                    <th scope="col">Thành tiền</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="orderDetail" items="${order.orderDetails}">
                                    <tr>
                                        <td>
                                            <img src="/images/product/${orderDetail.product.image}"
                                                 alt="${orderDetail.product.name}"
                                                 class="img-thumbnail"
                                                 style="width: 70px; height: 70px; object-fit: cover;">
                                        </td>
                                        <td class="text-start align-middle"><a href="/admin/product/${orderDetail.product.id}">${orderDetail.product.name}</a></td>
                                        <td class="align-middle">
                                            <fmt:formatNumber type="number" value="${orderDetail.price}" /> VND
                                        </td>
                                        <td class="align-middle">${orderDetail.quantity}</td>
                                        <td class="align-middle">
                                            <fmt:formatNumber type="number" value="${orderDetail.price * orderDetail.quantity}" /> VND
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="../layout/footer.jsp"/>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="/js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
</body>

</html>