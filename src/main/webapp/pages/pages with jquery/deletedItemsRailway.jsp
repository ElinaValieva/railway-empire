<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 13.07.2018
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>RAILWAY EMPIRE: AUDIT</title>
    <sec:csrfMetaTags/>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/button.css">
    <link rel="stylesheet" href="/static/css/sweetalert2.css">
    <script src="/static/js/plugins/jquery-3.3.1.js"></script>
    <script src="/static/js/plugins/sweetalert2.js"></script>
    <script src="/static/js/ajaxRequest.js"></script>
    <script src="/static/js/trains.js"></script>
    <script src="/static/js/stations.js"></script>
    <script src="/static/js/jquery/deletedItemsRailway.js"></script>
</head>
<body style="background: url('/static/images/bg.png') no-repeat center fixed;
-webkit-background-size: cover;
-moz-background-size: cover;
-o-background-size: cover;
background-size: cover">
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <div class="d-flex  align-items-center p-3 bg-dark  box-shadow">
        <h5 class="my-0 mr-md-auto font-weight-normal text-warning">RAILWAY EMPIRE</h5>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-light" href="/home">Home</a>
            <a class="p-2 text-light" href="/schedule">Schedule</a>
            <sec:authorize access="hasRole('ROLE_MANAGER')">
                <a class="p-2 text-light" href="/addItems">New</a>
                <a class="p-2 text-light" href="/editItems">Edit</a>
                <a class="p-2 text-light" href="/showItems">Map</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a class="p-2 text-light" href="/chart">Chart</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER')">
                <a class="p-2 text-light" href="/trips">Trips</a>
                <a class="p-2 text-light" href="/userMap">Show trips on map</a>
                <a class="p-2 text-light" href="/home/profile">Profile</a>
            </sec:authorize>
        </nav>
        <a class="btn btn-outline-warning" href="/logout">Sign out</a>
    </div>
    <div class="container">
        <br>
        <div class="row text-center">
            <div class="col-4">
                <button id="addStationBtn" class="btn btn-circle btn-xl bg-warning btn-warning form-control">
                    <img
                            src="/static/images/railroad.png"></button>
            </div>
            <div class="col-4">
                <button id="addTrainBtn"
                        class="btn  btn-circle btn-xl bg-warning btn-outline-warning form-control"><img
                        src="/static/images/subway.png"></button>
            </div>
            <div class="col-4">
                <button id="auditBtn"
                        class="btn  btn-circle btn-xl bg-warning btn-outline-warning form-control"><img
                        src="/static/images/graph-analysis.png"></button>
            </div>
        </div>
        <br>
        <table class="table table-striped table-dark text-center" hidden id="stationTable">
            <thead class="font-weight-bold">
            <tr>
                <th class="text-center">Station name</th>
                <th class="text-center">Latitude</th>
                <th class="text-center">Longitude</th>
                <th></th>
            </tr>
            </thead>
            <tbody class="text-center text-warning" id="stationTableId">
            </tbody>
        </table>

        <table class="table table-striped table-dark" hidden id="trainTable">
            <thead class="font-weight-bold">
            <tr>
                <th class="text-center">Train</th>
                <th class="text-center">Count carriages</th>
                <th class="text-center">Count seats</th>
                <th></th>
            </tr>
            </thead>
            <tbody class="text-center text-warning" id="trainTableId">
            </tbody>
        </table>

        <table class="table table-striped table-dark" hidden id="auditTable">
            <thead class="font-weight-bold">
            <tr>
                <th class="text-center">Changed date</th>
                <th class="text-center">Changed by</th>
                <th class="text-center">Event</th>
                <th class="text-center">New value</th>
                <th></th>
            </tr>
            </thead>
            <tbody class="text-center text-warning" id="auditTableId">
            </tbody>
        </table>
    </div>
</sec:authorize>
</body>
</html>

