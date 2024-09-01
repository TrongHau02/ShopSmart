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
    <title>Detail Product</title>
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
                    <li class="breadcrumb-item"><a href="/admin/product">Products</a></li>
                    <li class="breadcrumb-item active">Detail</li>
                </ol>
                <div class="mt-5">
                    <div class="row">
                        <div class="col-12 mx-auto">
                            <div class="d-flex justify-content-between">
                                <h3>Product Detail id = ${productDetail.id}</h3>
                            </div>
                            <hr/>
                            <div class="d-flex justify-content-center">
                                <div class="card" style="width: 60%">
                                    <img src="/images/product/${productDetail.image}" class="img-fluid"
                                         alt="${productDetail.name}"/>

                                    <div class="card-header text-center">
                                        Product Information
                                    </div>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">Name: ${productDetail.name}</li>
                                        <li class="list-group-item">Price: ${productDetail.price}</li>
                                        <li class="list-group-item">Short Description: ${productDetail.shortDesc}</li>
                                        <li class="list-group-item">Detail Description: ${productDetail.detailDesc}</li>
                                        <li class="list-group-item">Factory: ${productDetail.factory}</li>
                                        <li class="list-group-item">Target: ${productDetail.target}</li>
                                    </ul>
                                </div>
                            </div>
                            <hr/>
                            <a onclick="history.back()" class="btn btn-success mb-3">Back</a>
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