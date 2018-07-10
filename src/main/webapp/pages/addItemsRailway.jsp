<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 09.07.2018
  Time: 1:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <sec:csrfMetaTags/>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/railway.css">
    <link rel="stylesheet" href="/static/css/itemsRailway.css">
    <link rel="stylesheet" href="/static/css/clean-blog.min.css">
    <script src="/static/js/jquery-3.3.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.25.0/dist/sweetalert2.all.min.js"></script>
    <script src="/static/js/addSchedule.js"></script>
    <script src="/static/js/trains.js"></script>
    <script src="/static/js/addItemsRailway.js"></script>

</head>
<body>
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
<div class="wrapper">
    <div class="background"></div>
    <div class="rocks_1"></div>
    <div class="rocks_2"></div>
    <div class="rails"></div>
    <div class="train"></div>
    <div class="ground"></div>
    <div class="lights"></div>
    <div class="moon"></div>
    <div class="login-page">
        <div class="form">
            <div class="row">
                <div class="col">
                    <button id="addStationBtn" class="btn btn-circle btn-xl btn-outline-warning"><img
                            src="/static/images/railroad.png"></button>
                </div>
                <div class="col">
                    <button id="addScheduleBtn" class="btn btn-circle btn-xl btn-outline-warning"><img
                            src="/static/images/calendar.png"></button>
                </div>
                <div class="col">
                    <button id="addTrainBtn" class="btn btn-circle btn-xl btn-outline-warning"><img
                            src="/static/images/subway.png"></button>
                </div>
            </div>
            <br>
            <form class="login-form">
                <input type="text" placeholder="station name" id="stationNameItemsRailway"/>
                <div class="row">
                    <div class="col">
                        <input type="text" placeholder="coordinates X" id="coordinatesX"/>
                    </div>
                    <div class="col">
                        <input type="text" placeholder="coordinates Y" id="coordinatesY"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <input type="text" placeholder="station departure" id="stationDepartureItemsRailway"/>
                    </div>
                    <div class="col">
                        <input type="text" placeholder="station arrival" id="stationArrivalItemsRailway"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <input type="datetime-local" value="2018-00-00T00:00:00" step="1"
                               class="form-control" id="dateDepartureItemsRailway"/>
                    </div>
                    <div class="col">
                        <input type="datetime-local" value="2018-00-00T00:00:00" step="1"
                               class="form-control" id="dateArrivalItemsRailway"/>
                    </div>
                </div>
                <div><input type="text" placeholder="train" id="trainItemsRailway"/></div>
                <div class="row">
                    <div class="col">
                        <input type="number" placeholder="count carriage" class="form-control" id="cntCarriageItemsRailway">
                    </div>
                    <div class="col">
                        <input type="number" class="form-control" id="cntSeatsItemsRailway" value="30" disabled>
                    </div>
                </div>
                <button id="addItem">ADD ITEM</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
