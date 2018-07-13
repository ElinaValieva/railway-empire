<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 12.07.2018
  Time: 12:30
  To change this template use File | Settings | File Templates.
  --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
    <sec:csrfMetaTags/>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/button.css">
    <link rel="stylesheet" href="/static/css/sweetalert2.css">
    <script src="/static/js/plugins/jquery-3.3.1.js"></script>
    <script src="/static/js/plugins/randomColor.js"></script>
    <script src="/static/js/plugins/sweetalert2.js"></script>
    <script src="/static/js/plugins/Chart.js"></script>
    <script src="/static/js/ajaxRequest.js"></script>
    <script src="/static/js/chartShowing.js"></script>
</head>
<body style="background: url('/static/images/bg.png')">
<div class="d-flex align-items-center p-3 bg-dark  box-shadow">
    <h5 class="my-0 mr-md-auto font-weight-normal text-warning">RAILWAY EMPIRE</h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-light" href="#">Schedule</a>
        <a class="p-2 text-light" href="#">Train</a>
        <a class="p-2 text-light" href="#">Station</a>
        <a class="p-2 text-light" href="#">Profile</a>
    </nav>
    <a class="btn btn-outline-warning" href="#">Sign out</a>
</div>
<div class="container">
    <br>
    <div class="row text-center">
        <div class="col-3">
            <button id="chartStationBtn" class="btn btn-circle btn-xl bg-warning btn-warning form-control">
                <img
                        src="/static/images/railroad.png"></button>
        </div>
        <div class="col-3">
            <button id="chartStatisticAgesBtn"
                    class="btn  btn-circle btn-xl bg-warning btn-outline-warning form-control">
                <img
                        src="/static/images/circular-line-with-word-age-in-the-center.png"></button>
        </div>
        <div class="col-3">
            <button id="chartTicketCntBtn"
                    class="btn  btn-circle btn-xl bg-warning btn-outline-warning form-control"><img
                    src="/static/images/price.png"></button>
        </div>
        <div class="col-3">
            <button id="chartProfitBtn"
                    class="btn  btn-circle btn-xl bg-warning btn-outline-warning form-control"><img
                    src="/static/images/money.png"></button>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col">
            <h4 class="text-warning font-weight-bold">DATE FROM</h4><input type="date" id="dateFrom"
                                                                           class="form-control">
        </div>
        <div class="col">
            <h4 class="text-warning font-weight-bold">DATE TO</h4><input type="date" id="dateTo" class="form-control">
        </div>
    </div>
    <br>
    <div style="width: 1100px; height: 500px">
        <canvas hidden id="bar-chart" height="50" style="background: #1d2124"></canvas>
        <canvas hidden id="pie-chart" height="120" style="background: #1d2124"></canvas>
        <canvas hidden id="line-chart" height="50" style="background: #1d2124"></canvas>
    </div>
</div>
</body>
</html>
