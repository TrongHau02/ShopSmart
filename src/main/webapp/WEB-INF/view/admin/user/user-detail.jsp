<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

  <title>User Detail ${id}</title>
</head>
<body>
<div class="container mt-5">
  <div class="row">
    <div class="col-12 mx-auto">
      <div class="d-flex justify-content-between">
        <h3>User Detail id = ${id}</h3>
      </div>
      <hr/>
      <div class="card" style="width: 60%">
        <div class="card-header">
          User Information
        </div>
        <ul class="list-group list-group-flush">
          <li class="list-group-item">ID: 1</li>
          <li class="list-group-item">Email: </li>
          <li class="list-group-item">FullName: </li>
          <li class="list-group-item">Address: </li>
        </ul>
      </div>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
