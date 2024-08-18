<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Users</title>
    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- <link href="/css/demo.css" rel="stylesheet"> -->

</head>

<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 col-12 mx-auto">
            <h3>Create a user</h3>
            <hr/>
            <form:form action="/admin/user/create" method="POST" modelAttribute="newUser">
                <div class="mb-3">
                    <form:label class="form-label" path="email">Email:</form:label>
                    <form:input type="email" class="form-control" path="email"/>
                </div>
                <div class="mb-3">
                    <form:label class="form-label" path="password">Password:</form:label>
                    <form:input type="password" class="form-control" path="password"/>
                </div>
                <div class="mb-3">
                    <form:label class="form-label" path="phone">Phone number:</form:label>
                    <form:input type="text" class="form-control" path="phone"/>
                </div>
                <div class="mb-3">
                    <form:label class="form-label" path="fullName">Full Name:</form:label>
                    <form:input type="text" class="form-control" path="fullName"/>
                </div>
                <div class="mb-3">
                    <form:label class="form-label" path="address">Address:</form:label>
                    <form:input type="text" class="form-control" path="address"/>
                </div>

                <button type="submit" class="btn btn-primary">Create</button>
            </form:form>
        </div>

    </div>

</div>

</body>

</html>