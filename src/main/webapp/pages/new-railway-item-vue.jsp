<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 23.07.2018
  Time: 12:13
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
    <script src="/static/js/plugins/vue.js"></script>
    <script src="/static/js/plugins/axios.js"></script>
    <script src="/static/js/plugins/sweetalert2.js"></script>
</head>
<body>
<sec:authorize access="hasRole('ROLE_MANAGER')">
    <div class="d-flex  align-items-center p-3 bg-dark  box-shadow">
        <h5 class="my-0 mr-md-auto font-weight-normal text-warning">RAILWAY EMPIRE</h5>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-light" href="/home">Home</a>
            <a class="p-2 text-light" href="/schedule">Schedule</a>
            <sec:authorize access="hasRole('ROLE_MANAGER')">
                <a class="p-2 text-light" href="/editItems">Edit</a>
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
    <div class="wrapper" id="app-1">
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
                                src="/static/images/railroad.png"
                                @click="stationShow">
                        </button>
                    </div>
                    <div class="col">
                        <button id="addScheduleBtn" class="btn btn-circle btn-xl btn-outline-warning"><img
                                src="/static/images/calendar.png"
                                @click="scheduleShow">
                        </button>
                    </div>
                    <div class="col">
                        <button id="addTrainBtn" class="btn btn-circle btn-xl btn-outline-warning"><img
                                src="/static/images/subway.png"
                                @click="trainShow">
                        </button>
                    </div>
                </div>
                <br>
                <form class="login-form" id="stationId" hidden>
                    <datalist id="stationsList">
                        <auto-stations></auto-stations>
                    </datalist>
                    <datalist id="trainsList">
                        <auto-trains></auto-trains>
                    </datalist>

                    <form>
                        <input type="text" placeholder="station name"
                               pattern="[A-Za-zА-Яа-яЁё]{3,15}" title="Name must contain only letters"
                               required v-model="stationName"/>
                        <div class="row">
                            <div class="col">
                                <input type="text" placeholder="latitude [empty]" id="coordinatesX"
                                       pattern="\d+(\.\d{2, 8})?"
                                       v-model="latitude"/>
                            </div>
                            <div class="col">
                                <input type="text" placeholder="longitude [empty]" id="coordinatesY"
                                       pattern="\d+(\.\d{2, 8})?"
                                       v-model="longitude"/>
                            </div>
                        </div>
                        <button @click="addStationBtn">
                            ADD ITEM
                        </button>
                    </form>

                    <form @submit.prevent="addScheduleBtn" id="scheduleId" hidden>
                        <div class="row">
                            <div class="col">
                                <input type="text" placeholder="station departure"
                                       autocomplete="off" list="stationsList" v-model="stationDeparture"
                                       required pattern="[A-Za-zА-Яа-яЁё]{3,15}"
                                       title="Name must contain only letters"/>
                            </div>
                            <div class="col">
                                <input type="text" placeholder="station arrival"
                                       autocomplete="off" list="stationsList" v-model="stationArrival"
                                       required pattern="[A-Za-zА-Яа-яЁё]{3,15}"
                                       title="Name must contain only letters"/>
                            </div>
                            <div class="col">
                                <input type="text" placeholder="train"
                                       autocomplete="off" list="trainsList" v-model="trainSchedule"
                                       required pattern="[A-Za-zА-Яа-яЁё]{1}[0-9]{3,5}"
                                       title="Name must contain letter then number"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="datetime-local" value="2018-00-00T00:00:00" step="1"
                                       class="form-control" v-model="dateDeparture" required/>
                            </div>
                            <div class="col">
                                <input type="datetime-local" value="2018-00-00T00:00:00" step="1"
                                       class="form-control" v-model="dateArrival"/>
                            </div>
                        </div>
                        <button>ADD ITEM</button>
                    </form>

                    <form @submit.prevent="addTrainBtn" id="trainId" hidden>
                        <div><input type="text" placeholder="train" v-model="train" autocomplete="off"
                                    list="trainsList"
                                    pattern="[A-Za-zА-Яа-яЁё]{1}[0-9]{3,5}"
                                    title="Name must contain letter then number"
                                    required minlength="4" maxlength="6"/></div>
                        <div class="row">
                            <div class="col">
                                <input type="number" placeholder="count carriage" class="form-control"
                                       v-model="cntCarriage"
                                       required min="5" max="25">
                            </div>
                            <div class="col">
                                <input type="number" class="form-control" v-model="cntSeats" value="30"
                                       disabled>
                            </div>
                        </div>
                        <button>ADD ITEM</button>
                    </form>
                </form>
            </div>
        </div>
    </div>
</sec:authorize>
</body>
<script src="/static/js/vue/components.js"></script>
<script src="/static/js/plugins/jquery-3.3.1.js"></script>
<script src="/static/js/vue/new-item.js"></script>
</html>