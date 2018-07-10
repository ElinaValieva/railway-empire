<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 11.07.2018
  Time: 0:43
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
    <script src="/static/js/jquery-3.3.1.js"></script>
    <script src="/static/js/editItemsRailway.js"></script>
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
        <div class="col-4">
            <button id="addStationBtn" class="btn btn-circle btn-xl bg-warning btn-warning form-control">
                <img
                        src="/static/images/railroad.png"></button>
        </div>
        <div class="col-4">
            <button id="addScheduleBtn"
                    class="btn  btn-circle btn-xl bg-warning btn-outline-warning form-control">
                <img
                        src="/static/images/calendar.png"></button>
        </div>
        <div class="col-4">
            <button id="addTrainBtn"
                    class="btn  btn-circle btn-xl bg-warning btn-outline-warning form-control"><img
                    src="/static/images/subway.png"></button>
        </div>
    </div>
    <br>
    <table class="table table-striped table-dark text-center" id="stationTable">
        <thead class="font-weight-bold">
        <tr>
            <th class="text-center">Train</th>
            <th class="text-center">Count carriages</th>
            <th class="text-center">Count seats</th>
            <th></th>
        </tr>
        </thead>
        <tbody class="text-center text-warning">
        <tr>
            <td>Train</td>
            <td>Cnt carriage</td>
            <td>Cnt seats</td>
            <td>
                <button class="btn btn-outline-warning"><img src="/static/images/edit.png"></button>
                <button class="btn btn-outline-warning"><img src="/static/images/delete.png"></button>
            </td>
        </tr>
        </tbody>
    </table>
    <table class="table table-striped table-dark" id="trainTable">
        <thead class="font-weight-bold">
        <tr>
            <th class="text-center">Station name</th>
            <th class="text-center">Latitude</th>
            <th class="text-center">Longitude</th>
            <th></th>
        </tr>
        </thead>
        <tbody class="text-center text-warning">
        <tr>
            <td>Train</td>
            <td>Cnt carriage</td>
            <td>Cnt seats</td>
            <td>
                <button class="btn btn-outline-warning"><img src="/static/images/edit.png"></button>
                <button class="btn btn-outline-warning"><img src="/static/images/delete.png"></button>
            </td>
        </tr>
        </tbody>
    </table>
    <table class="table table-striped table-dark text-center" id="scheduleTable">
        <thead class="text-center font-weight-bold">
        <tr>
            <th class="text-center">Train</th>
            <th class="text-center">Station DEPARTURE</th>
            <th class="text-center">Station ARRIVAL</th>
            <th class="text-center">Date DEPARTURE</th>
            <th class="text-center">Date ARRIVAL</th>
            <th></th>
        </tr>
        </thead>
        <tbody class="text-center text-warning">
        <tr>
            <td>Train</td>
            <td>Cnt carriage</td>
            <td>Cnt seats</td>
            <td>24 Apr 12:00</td>
            <td>24 Apr 14:00</td>
            <td>
                <button class="btn btn-outline-warning"><img src="/static/images/edit.png"></button>
                <button class="btn btn-outline-warning"><img src="/static/images/delete.png"></button>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
