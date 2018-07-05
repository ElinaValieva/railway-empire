<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 29.06.2018
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <sec:csrfMetaTags/>
    <title>Title</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/static/css/style.css" rel="stylesheet" type="text/css">
    <script src="/static/js/jquery-3.3.1.js"></script>
    <script src="/static/js/schedule.js"></script>
</head>
<body class="bg-light pointer">
<div class="d-flex  align-items-center p-3 bg-dark  box-shadow">
    <h5 class="my-0 mr-md-auto font-weight-normal text-warning">RAILWAY EMPIRE</h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-light" href="/home">Home</a>
        <a class="p-2 text-light" href="/train">Train</a>
        <a class="p-2 text-light" href="/station">Station</a>
        <a class="p-2 text-light" href="/home/update">Profile</a>
    </nav>
    <a class="btn btn-outline-warning" href="#">Sign out</a>
</div>
<div class="container shadow p-3 mb-5 bg-light rounded">
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link active" id="searchingDirectForAllParameters">Searching by all</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="searchingByStationsAndDate">Searching by stations</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="searchingByTrainAndDate">Searching by train</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="searchingByDate">Searching by date</a>
        </li>
    </ul>
    <div class="row align-items-center">
        <div class="col-lg-5">
            <img src="/static/images/train.jpg" width="400" height="300">
        </div>
        <div class="col-lg-7">
            <form class="form-control bg-danger text-center">
                <div><h3 class="display-5 text-white">Searching schedule</h3></div>
                <div>
                    <input id="stationDeparture" required type="text" class="form-control" placeholder="from"><br>
                </div>
                <div>
                    <input id="stationArrival" required type="text" class="form-control" placeholder="to"><br>
                </div>
                <div class="row">
                    <div class="col input-group">
                        <input id="dateDeparture" required type="date" class="form-control" placeholder="from"
                               aria-label="Large"
                               aria-describedby="inputGroup-sizing-sm">
                    </div>
                    <div class="col input-group">
                        <input id="dateArrival" type="date" class="form-control" placeholder="to"
                               aria-label="Large"
                               aria-describedby="inputGroup-sizing-sm" required>
                    </div>
                    <div class="col input-group">
                        <input id="train" type="text" placeholder="train" class="form-control"
                               aria-label="Large"
                               aria-describedby="inputGroup-sizing-sm" aria-required="true">
                    </div>
                </div>
                <br>
                <div class="text-center">
                    <button id="btnSearchSchedule" type="submit" class="btn btn-outline-dark">search schedule</button>
                </div>
                <br>
            </form>
        </div>
    </div>
</div>
<div class="container shadow p-3 mb-5 bg-white rounded" id="mainScheduleContainer" style="display: none">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Station departure</th>
            <th scope="col">Station arrival</th>
            <th scope="col">Train</th>
            <th scope="col">Date departure</th>
            <th scope="col">Date arrival</th>
        </tr>
        </thead>
        <tbody id="tableSchedule">
        </tbody>
    </table>
</div>
</body>
</html>
