<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 24.07.2018
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>RAILWAY EMPIRE: AUDIT</title>
    <sec:csrfMetaTags/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/button.css">
    <link rel="stylesheet" href="/static/css/sweetalert2.css">
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
    <div class="container" id="app-deleted">
        <br>
        <div class="row text-center">
            <div class="col-4">
                <button @click="addStationBtn" class="btn btn-circle btn-xl bg-warning btn-warning form-control">
                    <img src="/static/images/railroad.png">
                </button>
            </div>
            <div class="col-4">
                <button @click="addTrainBtn" class="btn  btn-circle btn-xl bg-warning btn-outline-warning form-control">
                    <img src="/static/images/subway.png">
                </button>
            </div>
            <div class="col-4">
                <button @click="auditBtn" class="btn  btn-circle btn-xl bg-warning btn-outline-warning form-control">
                    <img src="/static/images/graph-analysis.png">
                </button>
            </div>
        </div>
        <br>
        <table class="table table-striped text-center" id="stationTable" hidden style="background: rgba(0,0,0,0.6);">
            <thead class="font-weight-bold text-warning">
            <tr>
                <th class="text-center">Station name</th>
                <th class="text-center">Latitude</th>
                <th class="text-center">Longitude</th>
                <th></th>
            </tr>
            </thead>
            <tbody class="text-center text-warning">
            <tr v-for="station in stations">
                <td>{{station.name}}</td>
                <td>{{station.latitude}}</td>
                <td>{{station.longitude}}</td>
                <td>
                    <button class='btn btn-outline-warning' @click="reestablishStation(station.name)">
                        <img src='static/images/restore.png'>
                    </button>
                </td>
            </tr>
            </tbody>
        </table>

        <table class="table table-striped" id="trainTable" hidden style="background: rgba(0,0,0,0.6);">
            <thead class="font-weight-bold text-warning">
            <tr>
                <th class="text-center">Train</th>
                <th class="text-center">Count carriages</th>
                <th class="text-center">Count seats</th>
                <th></th>
            </tr>
            </thead>
            <tbody class="text-center text-warning">
            <tr v-for="train in trains">
                <td>{{train.trainName}}</td>
                <td>{{train.cntCarriage}}</td>
                <td>{{train.cntSeats}}</td>
                <td>
                    <button class='btn btn-outline-warning' @click="reestablishTrain(train.trainName)">
                        <img src='static/images/restore.png'>
                    </button>
                </td>
            </tr>
            </tbody>
        </table>

        <table class="table" id="auditTable" hidden style="background: rgba(0,0,0,0.6);">
            <thead class="font-weight-bold text-warning">
            <tr v-if="audits!=[]">
                <th class="text-center">Changed date</th>
                <th class="text-center">Changed by</th>
                <th class="text-center">Event</th>
                <th class="text-center">New value</th>
                <th></th>
            </tr>
            </thead>
            <tbody class="text-center text-warning">
            <tr v-for="audit in audits">
                <td>{{audit.date}}</td>
                <td>{{audit.userLogin}}</td>
                <td>{{audit.operations}}</td>
                <td>{{audit.newValue}}</td>
                <td>
                    <button class='btn btn-outline-warning' @click="auditInfo(audit.id)">
                        <img src='static/images/restore.png'></button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</sec:authorize>
<jsp:include page="footer.jsp"></jsp:include>
</body>
<script src="/static/js/plugins/jquery-3.3.1.js"></script>
<script>
    new Vue({
        el: '#app-deleted',
        data() {
            return {
                trains: [],
                stations: [],
                audits: []
            }
        },
        mounted() {
            axios
                .get('/train/deletedTrains')
                .then(response => {
                    this.trains = response.data;
                })
                .catch(error => {
                    alert(JSON.stringify(error));
                    console.log(JSON.stringify(error));
                    swal({
                        title: 'Oops..',
                        text: error.response.data.message.split('[MESSAGE]:')[1],
                        type: 'error'
                    });
                });
            axios
                .get('/station/deletedStations')
                .then(response => {
                    this.stations = response.data;
                })
                .catch(error => {
                    console.log(JSON.stringify(error));
                    swal({
                        title: 'Oops..',
                        text: error.response.data.message.split('[MESSAGE]:')[1],
                        type: 'error'
                    });
                });
            axios
                .get('/audit')
                .then(response => {
                    this.audits = response.data;
                })
                .catch(error => {
                    console.log(JSON.stringify(error));
                    swal({
                        title: 'Oops..',
                        text: error.response.data.message.split('[MESSAGE]:')[1],
                        type: 'error'
                    });
                });
        },
        methods: {
            addStationBtn: function () {
                $('#stationTable').prop('hidden', false);
                $('#trainTable').prop('hidden', true);
                $('#auditTable').prop('hidden', true);
            },
            addTrainBtn: function () {
                $('#stationTable').prop('hidden', true);
                $('#trainTable').prop('hidden', false);
                $('#auditTable').prop('hidden', true);
            },
            auditBtn: function () {
                $('#stationTable').prop('hidden', true);
                $('#trainTable').prop('hidden', true);
                $('#auditTable').prop('hidden', false);
            },
            reestablishTrain: function (trainName) {
                axios
                    .get("/train/reestablish/" + trainName)
                    .then(response => {
                        console.log(JSON.stringify(response));
                        var index = this.audits.findIndex(x => x.trainName == trainName);
                        this.trains.splice(index, 1);
                        swal({
                            title: 'Congratulation!', text: 'You reestablish train!', type: 'success'
                        });
                    })
                    .catch(error => {
                        console.log(JSON.stringify(error));
                        swal({
                            title: 'Oops..',
                            text: error.response.data.message.split('[MESSAGE]:')[1],
                            type: 'error'
                        });
                    })
            },
            reestablishStation: function (name) {
                axios
                    .get("/station/reestablish/" + name)
                    .then(response => {
                        console.log(JSON.stringify(response));
                        var index = this.audits.findIndex(x => x.name == name);
                        this.stations.splice(index, 1);
                        swal({
                            title: 'Congratulation!', text: 'You reestablish station!', type: 'success'
                        });
                    })
                    .catch(error => {
                        console.log(JSON.stringify(error));
                        swal({
                            title: 'Oops..',
                            text: error.response.data.message.split('[MESSAGE]:')[1],
                            type: 'error'
                        });
                    })
            },
            auditInfo: function (id) {
                var audit = this.audits.filter(x => x.id == id);
                swal({
                    title: 'AUDIT HISTORY',
                    html:
                    "<input id='swal-input1' class='swal2-input' value='" + audit[0].userFirstName + audit[0].userLastName + "' disabled>" +
                    "<input id='swal-input2' class='swal2-input' value='" + audit[0].userLogin + "' disabled>" +
                    "<input id='swal-input3' class='swal2-input' value='" + (audit[0].oldValue == null ? '-' : audit[0].oldValue) + "' disabled>" +
                    "<textarea id='swal-input3' class='swal2-input' disabled style='height: 100px'>" + audit[0].newValue + "</textarea>",
                    icon: "info",
                });
            }

        }
    })
</script>
</html>
