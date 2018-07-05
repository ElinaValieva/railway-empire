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
    <sec:csrfMetaTags />
    <title>Title</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="/static/js/jquery-3.3.1.js"></script>
    <script src="/static/js/schedule.js"></script>
</head>
<body class="bg-light">
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
<div class="container shadow p-3 mb-5 bg-white rounded">
    <div class="row align-items-center">
        <div class="col-lg-5">
            <img src="/static/images/train.jpg" width="400" height="300">
        </div>
        <div class="col-lg-7">
            <form class="form-control bg-danger text-center">
                <div><h3 class="display-5">Searching schedule</h3></div>
                <div>
                    <input id="stationDeparture" type="text" class="form-control" placeholder="from"><br>
                </div>
                <div>
                    <input id="stationArrival" type="text" class="form-control" placeholder="to"><br>
                </div>
                <div class="row">
                    <div class="col input-group">
                        <input id="date" type="date" class="form-control" placeholder="from"
                               aria-label="Large"
                               aria-describedby="inputGroup-sizing-sm">
                    </div>
                    <div class="col input-group">
                        <input type="number" placeholder="count tickets" class="form-control"
                               aria-label="Large"
                               aria-describedby="inputGroup-sizing-sm">
                    </div>
                </div>
                <br>
                <div class="text-center">
                    <button id="btnSearchSchedule" type="submit" class="btn btn-outline-dark">search schedule</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="container shadow p-3 mb-5 bg-white rounded">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">first</th>
            <th scope="col">second</th>
            <th scope="col">third</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${schedules}" var="stations">
        <tr>
            <td><c:out value="${schedule.date}"/></td>
        </tr>
        </c:forEach>
        <tr>
            <th scope="row">1</th>
            <th scope="row">Elina</th>
            <th scope="row">Valieva</th>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
