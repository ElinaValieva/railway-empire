<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 09.07.2018
  Time: 0:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>RAILWAY EMPIRE: SEATS</title>
    <sec:csrfMetaTags/>
    <link rel="stylesheet" href="static/css/seat.css">
    <link rel="stylesheet" href="/static/css/sweetalert2.css">
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="/static/js/plugins/jquery-3.3.1.js"></script>
    <script src="/static/js/plugins/sweetalert2.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/static/js/ajaxRequest.js"></script>
    <script src="/static/js/getSeats.js"></script>
    <script src="/static/js/tickets.js"></script>
    <script src="static/js/seats.js"></script>
</head>
<body class="bg-light">
<sec:authorize access="hasRole('ROLE_USER')">
    <div class="d-flex  align-items-center p-3 bg-dark  box-shadow">
        <h5 class="my-0 mr-md-auto font-weight-normal text-warning">RAILWAY EMPIRE</h5>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-light" href="/home">Home</a>
            <a class="p-2 text-light" href="/schedule">Schedule</a>
            <sec:authorize access="hasRole('ROLE_MANAGER')">
                <a class="p-2 text-light" href="/addItems">New</a>
                <a class="p-2 text-light" href="/editItems">Modifications</a>
                <a class="p-2 text-light" href="/showItems">Map</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a class="p-2 text-light" href="/chart">Chart</a>
                <a class="p-2 text-light" href="/showDeletedItems">Audit</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER')">
                <a class="p-2 text-light" href="/trips">Trips</a>
                <a class="p-2 text-light" href="/userMap">Show trips on map</a>
                <a class="p-2 text-light" href="/home/profile">Profile</a>
            </sec:authorize>
        </nav>
        <a class="btn btn-outline-warning" href="/logout">Sign out</a>
    </div>
    <div class="carousel">
        <div class="carousel-inner" style="background: url('/static/images/bg.png')">
            <div class="text-xl-center">
                <h3 id="carriageNumber" class="font-weight-bold text-warning">Carriage № 1</h3>
            </div>
            <div id="holder">
                <ul id="place">

                </ul>
            </div>
            <div class="text-center">
                <button class="btn btn-warning center-block" id="btnBookTicket">BOOK TICKET</button>
            </div>
            <br>
            <br>
        </div>
        <a class="left carousel-control" href="#myCarousel" data-slide="prev" id="btnPrev">
            <span class="glyphicon glyphicon-chevron-left"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next" id="btnNext">
            <span class="glyphicon glyphicon-chevron-right"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <div class="modal-footer bg-light text-center">
        <ul id="seatDescription" class="center-block">
            <li style="background:url('/static/images/available_seat_img.gif') no-repeat scroll 0 0 transparent;">
                <h4 class="text-warning"> Available Seat</h4>
            </li>
            <li style="background:url('/static/images/booked_seat_img.gif') no-repeat scroll 0 0 transparent;">
                <h4 class="text-warning">Booked Seat</h4>
            </li>
            <li style="background:url('/static/images/selected_seat_img.gif') no-repeat scroll 0 0 transparent;">
                <h4 class="text-warning">Selected Seat</h4>
            </li>
        </ul>
    </div>
</sec:authorize>
</body>
</html>
