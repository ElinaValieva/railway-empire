<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 10.07.2018
  Time: 14:15
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
    <link rel="stylesheet" href="/static/css/map.css">
    <link rel="stylesheet" href="/static/css/mail.css">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.25.0/dist/sweetalert2.all.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAKFWBqlKAGCeS1rMVoaNlwyayu0e0YRes"></script>
    <script src="/static/js/trains.js"></script>
    <script src="/static/js/stations.js"></script>
    <script src="/static/js/map.js"></script>
</head>
<body>
<div class="d-flex  align-items-center p-3 bg-dark  box-shadow">
    <h5 class="my-0 mr-md-auto font-weight-normal text-warning">RAILWAY EMPIRE</h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-light" href="#">Schedule</a>
        <a class="p-2 text-light" href="#">Train</a>
        <a class="p-2 text-light" href="#">Station</a>
        <a class="p-2 text-light" href="#">Profile</a>
    </nav>
    <a class="btn btn-outline-warning" href="#">Sign out</a>
</div>
<div id="map"></div>
<div class="card-footer bg-dark">
    <div class="row">
        <div class="col">
            <button id="addStationBtn" class="btn btn-circle btn-xl btn-warning border-light"><img
                    src="/static/images/railroad.png"></button>
            <label class="text-warning text-center">Check stations</label>
        </div>
        <div class="col">
            <button id="addTrainBtn" class="btn btn-circle btn-xl btn-warning border-light"><img
                    src="/static/images/subway.png"></button>
            <label class="text-warning text-center">Check trains</label>
        </div>
        <div class="col">
            <button id="addAllBtn" class="btn btn-circle btn-xl btn-warning border-light"><img
                    src="/static/images/icons8-train-track-26.png"></button>
            <label class="text-warning text-center">Check stations and trains</label>
        </div>
    </div>
</div>
</body>
</html>

