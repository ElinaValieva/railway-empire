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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>RAILWAY EMPIRE: NEW</title>
    <sec:csrfMetaTags/>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/railway.css">
    <link rel="stylesheet" href="/static/css/itemsRailway.css">
    <link rel="stylesheet" href="/static/css/clean-blog.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert2.css">
    <script src="/static/js/plugins/jquery-3.3.1.js"></script>
    <script src="/static/js/plugins/sweetalert2.js"></script>
    <script src="/static/js/ajaxRequest.js"></script>
    <script src="/static/js/searchingSchedule.js"></script>
    <script src="/static/js/trains.js"></script>
    <script src="/static/js/stations.js"></script>
    <script src="/static/js/addItemsRailway.js"></script>

</head>
<body>
<sec:authorize access="hasRole('ROLE_MANAGER')">
    <div class="d-flex  align-items-center p-3 bg-dark  box-shadow">
        <h5 class="my-0 mr-md-auto font-weight-normal text-warning">RAILWAY EMPIRE</h5>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-light" href="/home">Home</a>
            <a class="p-2 text-light" href="/schedule">Schedule</a>
            <sec:authorize access="hasRole('ROLE_MANAGER')">
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
                <form class="login-form" id="newItemForm" hidden>
                    <input type="text" placeholder="station name" id="stationNameItemsRailway"/>
                    <div class="row">
                        <div class="col">
                            <input type="text" placeholder="latitude [empty]" id="coordinatesX"/>
                        </div>
                        <div class="col">
                            <input type="text" placeholder="longitude [empty]" id="coordinatesY"/>
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
                            <input type="number" placeholder="count carriage" class="form-control"
                                   id="cntCarriageItemsRailway">
                        </div>
                        <div class="col">
                            <input type="number" class="form-control" id="cntSeatsItemsRailway" value="30" disabled>
                        </div>
                    </div>
                    <button id="addItem1">ADD ITEM</button>
                    <button id="addItem2">ADD ITEM</button>
                    <button id="addItem3">ADD ITEM</button>

                </form>
            </div>
        </div>
    </div>
</sec:authorize>
</body>
</html>
