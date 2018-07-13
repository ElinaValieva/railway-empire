<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 20.06.2018
  Time: 0:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>${startPage}</title>
    <link rel="stylesheet" href="/static/css/login.css">
    <link rel="stylesheet" href="/static/css/railway.css">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <script src="/static/js/ajaxRequest.js"></script>
</head>
<body>
<div>
    <div class="d-flex  align-items-center p-3 bg-dark  box-shadow">
        <h5 class="my-0 mr-md-auto font-weight-normal text-warning">RAILWAY EMPIRE</h5>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-light" href="/schedule">Schedule</a>
            <a class="p-2 text-light" href="/test">new Schedule</a>
            <a class="p-2 text-light" href="/userMap">Show markers</a>
            <a class="p-2 text-light" href="/chart">Chart</a>
            <a class="p-2 text-light" href="/home/update">Profile</a>
            <a class="p-2 text-light" href="/addItems">AddItems</a>
            <a class="p-2 text-light" href="/showItems">ShowOnMap</a>
            <a class="p-2 text-light" href="/editItems">EditItems</a>
            <a class="p-2 text-light" href="/showDeletedItems">Deleted</a>


        </nav>
        <a class="btn btn-outline-warning" href="/login">Sign up</a>
    </div>
    <div class="wrapper">
        <div class="background"></div>
        <div class="rocks_1"></div>
        <div class="rocks_2"></div>
        <div class="rails"></div>
        <div class="train"></div>
        <div class="ground"></div>
        <div class="lights"></div>
        <div class="moon"></div>
    </div>
</div>
</body>
</html>
