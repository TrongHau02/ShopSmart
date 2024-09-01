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
    <title>Update User</title>
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
                    <li class="breadcrumb-item"><a href="/admin/product">Products</a></li>
                    <li class="breadcrumb-item active">Update</li>
                </ol>
                <div class="mt-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <h3>Update product with id = ${productUpdate.id}</h3>
                            <hr/>
                            <form:form action="/admin/product/update" method="POST"
                                       modelAttribute="productUpdate" class="row"
                                       enctype="multipart/form-data">
                                <div class="mb-3" style="display: none">
                                    <label class="form-label">Id:</label>
                                    <form:input type="id" class="form-control" path="id"/>
                                </div>
                                <div class="mb-3 col-12 col-md-6">
                                    <c:set var="errorName">
                                        <form:errors path="name" cssClass="invalid-feedback"/>
                                    </c:set>
                                    <label class="form-label">Name:</label>
                                    <form:input type="text"
                                                class="form-control ${not empty errorName ? 'is-invalid' : ''}"
                                                path="name"/>
                                        ${errorName}
                                </div>
                                <div class="mb-3 col-12 col-md-6">
                                    <c:set var="errorPrice">
                                        <form:errors cssClass="invalid-feedback" path="price"/>
                                    </c:set>
                                    <label class="form-label">Price:</label>
                                    <form:input type="number"
                                                class="form-control ${not empty errorPrice ? 'is-invalid': ''}"
                                                path="price"/>
                                        ${errorPrice}
                                </div>
                                <div class="mb-3 col-12">
                                    <c:set var="errorShortDesc">
                                        <form:errors cssClass="invalid-feedback" path="shortDesc"/>
                                    </c:set>
                                    <label class="form-label">Short Description:</label>
                                    <form:input type="text"
                                                class="form-control ${not empty errorShortDesc ? 'is-invalid' : ''}"
                                                path="shortDesc"/>
                                        ${errorShortDesc}
                                </div>
                                <div class="mb-3 col-12">
                                    <c:set var="errorDetailDesc">
                                        <form:errors cssClass="invalid-feedback" path="detailDesc"/>
                                    </c:set>
                                    <label class="form-label">Detail Description:</label>
                                    <form:textarea type="text"
                                                class="form-control ${not empty errorDetailDesc ? 'is-invalid' : ''}"
                                                path="detailDesc"/>
                                        ${errorDetailDesc}
                                </div>
                                <div class="mb-3 col-12 col-md-6" disabled="true">
                                    <label class="form-label">Factory:</label>
                                    <form:select class="form-select" path="factory" disabled="true">
                                        <c:forEach var="factory" items="${factorys}">
                                            <form:option value="${factory.name}">${factory.name}</form:option>
                                        </c:forEach>
                                    </form:select>
                                </div>
                                <div class="mb-3 col-12 col-md-6">
                                    <label class="form-label">Target:</label>
                                    <form:select class="form-select" path="target">
                                        <c:forEach var="target" items="${targets}">
                                            <form:option value="${target.name}">${target.name}</form:option>
                                        </c:forEach>
                                    </form:select>
                                </div>
                                <div class="mb-3 col-12 col-md-6">
                                    <label for="imageProduct" class="form-label">Avatar:</label>
                                    <input class="form-control" type="file" id="imageProduct" accept=".png, .jpg, .jpeg"
                                           name="imageProduct"/>
                                </div>
                                <div class="mb-3 col-12 col-md-6">
                                    <c:set var="errorQuantity">
                                        <form:errors cssClass="invalid-feedback" path="quantity"/>
                                    </c:set>
                                    <label class="form-label">Quantity:</label>
                                    <form:input path="quantity"
                                                class="form-control ${not empty errorQuantity ? 'is-invalid' : ''}"
                                                type="number"/>
                                        ${errorQuantity}
                                </div>
                                <c:if test="${not empty productUpdate.image}">
                                    <div class="col-12 mb-3">
                                        <img style="max-height: 250px" alt="product preview"
                                             src="<c:url value="/images/product/${productUpdate.image}"/>"
                                             id="ImagePreview"/>
                                    </div>
                                </c:if>
                                <c:if test="${empty productUpdate.image}">
                                    <div class="col-12 mb-3">
                                        <img style="max-height: 250px; display: none;" alt="product preview"
                                             id="ImagePreview"/>
                                    </div>
                                </c:if>
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