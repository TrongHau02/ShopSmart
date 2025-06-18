<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content="Hỏi Dân IT - Dự án laptopshop"/>
    <meta name="author" content="Hỏi Dân IT"/>
    <title>Update Order</title>
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
                <h1 class="mt-4">Update Product</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="/admin/home">Dashboard</a></li>
                    <li class="breadcrumb-item"><a href="/admin/order">Order</a></li>
                    <li class="breadcrumb-item active">Update</li>
                </ol>
                <div class="mt-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <h3>Update a order</h3>
                            <hr/>
                            <form:form action="/admin/order/update" method="POST"
                                       modelAttribute="order" class="row">
                                <div class="mb-3 col-12 col-md-6">
                                    <label class="form-label">Order Id:</label>
                                    <form:input type="text" class="form-control" path="id" readonly="true"/>
                                </div>
                                <div class="mb-3 col-12 col-md-6">
                                    <label class="form-label">Price:</label>
                                    <form:input type="number" class="form-control" path="totalPrice" readonly="true"/>
                                </div>
                                <div class="mb-3 col-12 col-md-6">
                                    <label class="form-label">User:</label>
                                    <form:input type="text" class="form-control" path="user.fullName" readonly="true"/>
                                </div>
                                <div class="mb-3 col-12 col-md-6">
                                    <label class="form-label">Status:</label>
                                    <form:select class="form-select" path="status">
                                        <form:option value="PENDING">PENDING</form:option>
                                        <form:option value="SHIPPING">SHIPPING</form:option>
                                        <form:option value="COMPLETE">COMPLETE</form:option>
                                        <form:option value="CANCEL">CANCEL</form:option>
                                    </form:select>
                                </div>
                                <div class="mb-3">
                                    <button type="submit" class="btn btn-primary">Update</button>
                                </div>
                            </form:form>
                        </div>

                    </div>

                </div>
            </div>
        </main>
        <jsp:include page="../layout/footer.jsp"/>
    </div>
</div>
<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
    $(document).ready(() => {
        const imageProduct = $("#imageProduct");
        imageProduct.change(function (e) {
            const imgURL = URL.createObjectURL(e.target.files[0]);
            $("#ImagePreview").attr("src", imgURL);
            $("#ImagePreview").css({"display": "block"});
        });
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="/js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
</body>

</html>