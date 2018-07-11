<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 07.07.2018
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <sec:csrfMetaTags/>
    <title>Title</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/static/css/style.css" rel="stylesheet" type="text/css">
    <script src="/static/js/ajaxRequest.js"></script>
    <script src="/static/js/jquery-3.3.1.js"></script>
    <script src="/static/js/addSchedule.js"></script>
</head>
<body class="bg-light pointer">
<div class="d-flex  align-items-center p-3 bg-dark  box-shadow">
    <h5 class="my-0 mr-md-auto font-weight-normal text-warning">RAILWAY EMPIRE</h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-light" href="/home">Home</a>
        <a class="p-2 text-light" href="/schedule">Schedule</a>
        <a class="p-2 text-light" href="/train">Train</a>
        <a class="p-2 text-light" href="/station">Station</a>
        <a class="p-2 text-light" href="/home/update">Profile</a>
    </nav>
    <a class="btn btn-outline-warning" href="#">Sign out</a>
</div>
<div class="container shadow p-3 mb-5 bg-white rounded">
    <div class="row align-items-center">
        <div class="col-lg-5">
            <img src="static/images/train.jpg" width="400" height="300">
        </div>
        <div class="col-lg-7 align-items-center">
            <form class="form-control bg-danger text-center">
                <div><h3 class="display-5">Creating schedule</h3></div>
                <div class="row">
                    <div class="col input-group">
                        <input type="text" class="form-control" placeholder="STATION DEPARTURE" id="stationDeparture"><br>
                    </div>
                    <div class="col input-group">
                        <input type="text" class="form-control" placeholder="STATION ARRIVAL" id="stationArrival" aria-label="Large"
                               aria-describedby="inputGroup-sizing-sm">
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col col-5 input-group">
                        <input type="datetime-local" class="form-control" value="2018-00-00T00:00:00" step="1" id="dateDeparture">
                    </div>
                    <div class="col col-5 input-group">
                        <input type="datetime-local" class="form-control" value="2018-00-00T00:00:00" step="1" id="dateArrival">
                    </div>
                    <div class="col col-2 input-group">
                        <input type="text" placeholder="TRAIN" class="form-control" aria-label="Large"
                               aria-describedby="inputGroup-sizing-sm" id="train">
                    </div>
                </div>
                <br>
                <div class="text-center">
                    <button type="button" class="btn btn-outline-dark" id="createScheduleId">add train</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

