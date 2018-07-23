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
                                @click="show = 'station'">
                        </button>
                    </div>
                    <div class="col">
                        <button id="addScheduleBtn" class="btn btn-circle btn-xl btn-outline-warning"><img
                                src="/static/images/calendar.png"
                                @click="show = 'schedule'">
                        </button>
                    </div>
                    <div class="col">
                        <button id="addTrainBtn" class="btn btn-circle btn-xl btn-outline-warning"><img
                                src="/static/images/subway.png"
                                @click="show = 'train'">
                        </button>
                    </div>
                </div>
                <br>
                <form class="login-form" id="newItemForm" v-if="show!=''">

                    <div v-if="show == 'station'">
                        <form>
                            <input type="text" placeholder="station name"
                                   pattern="[A-Za-zА-Яа-яЁё]{3,15}" title="Name must contain only letters"
                                   required v-model="stationName"/>
                            <div class="row">
                                <div class="col">
                                    <input type="text" placeholder="latitude [empty]" id="coordinatesX"

                                           v-model="latitude"/>
                                </div>
                                <div class="col">
                                    <input type="text" placeholder="longitude [empty]" id="coordinatesY"
                                           v-model="longitude"/>
                                </div>
                            </div>
                            <button id="addItemTrain" type="submit" @click="addStationBtn">
                                ADD ITEM
                            </button>
                        </form>
                    </div>

                    <div v-if="show == 'schedule'">
                        <datalist id="stationsList">
                            <auto-stations></auto-stations>
                        </datalist>
                        <datalist id="trainsList">
                            <auto-trains></auto-trains>
                        </datalist>
                        <div class="row">
                            <div class="col">
                                <input type="text" placeholder="station departure"
                                       autocomplete="off" list="stationsList" v-model="stationDeparture"/>
                            </div>
                            <div class="col">
                                <input type="text" placeholder="station arrival"
                                       autocomplete="off" list="stationsList" v-model="stationArrival"/>
                            </div>
                            <div class="col">
                                <input type="text" placeholder="train"
                                       autocomplete="off" list="trainsList" v-model="trainSchedule"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <input type="datetime-local" value="2018-00-00T00:00:00" step="1"
                                       class="form-control" v-model="dateDeparture"/>
                            </div>
                            <div class="col">
                                <input type="datetime-local" value="2018-00-00T00:00:00" step="1"
                                       class="form-control" v-model="dateArrival"/>
                            </div>
                        </div>
                        <button id="addItem2" @click="addScheduleBtn">ADD ITEM</button>
                    </div>

                    <div v-if="show == 'train'">
                        <div><input type="text" placeholder="train" v-model="train" autocomplete="off"
                                    list="trainsList" required minlength="4" maxlength="6"/></div>
                        <div class="row">
                            <div class="col">
                                <input type="number" placeholder="count carriage" class="form-control"
                                       v-model="cntCarriage" required min="5" max="25">
                            </div>
                            <div class="col">
                                <input type="number" class="form-control" v-model="cntSeats" value="30" disabled>
                            </div>
                        </div>
                        <button id="addItem3" @click="addTrainBtn">ADD ITEM</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</sec:authorize>
</body>
<script src="/static/js/vue/components.js"></script>
<script>
    new Vue({
        el: '#app-1',
        data() {
            return {
                show: '',
                stationName: '',
                latitude: '',
                longitude: '',
                stationDeparture: '',
                stationArrival: '',
                trainSchedule: '',
                dateDeparture: '',
                dateArrival: '',
                cntCarriage: 0,
                cntSeats: 30,
                train: ''
            }
        },
        methods: {
            addStationBtn: function (event) {
                event.preventDefault();
                if (this.latitude == '' || this.longitude == '') {
                    this.getCoordinates(this.stationName)
                }
                else this.addStation(this.latitude, this.longitude);
            },
            addScheduleBtn: function (event) {
                event.preventDefault();
                this.addSchedule();
            },
            addTrainBtn: function (event) {
                event.preventDefault();
                this.addTrain();
            },
            getCoordinates: function (city) {
                var url = "https://maps.googleapis.com/maps/api/geocode/json?address=$" + city;
                var cntx = this;
                axios
                    .get(url)
                    .then(response => {
                        var location = JSON.stringify(response.data.results[0].geometry.location);
                        var longitude = location.lng;
                        var latitude = location.lat;
                        cntx.addStation(latitude, longitude);
                    })
                    .catch(error => {
                        console.log(error);
                        swal({
                            title: 'Oops..', text: "Wrong station name, can't find anything", type: 'error'
                        });
                    });
            },
            addStation: function (latitude, longitude) {
                swal({
                    title: this.stationName, text: 'latitude = ' + latitude + ', longitude = ' + longitude,
                    type: 'info',
                    showCancelButton: true, cancelButtonText: 'Wrong parameters ...', confirmButtonText: "It's OK!"
                }).then((result) => {
                    if (result.value) {
                        axios
                            .post('/station/add', {
                                name: this.stationName,
                                latitude: latitude,
                                longitude: longitude
                            })
                            .then(response => {
                                console.log(JSON.stringify(response));
                                swal({
                                    title: 'Congratulations!', text: 'You add new station!', type: 'success'
                                });
                            })
                            .catch(error => {
                                console.log(JSON.stringify(error));
                                swal({
                                    title: 'Oops..',
                                    text: error.response.data.message.split('[MESSAGE]:')[1],
                                    type: 'error'
                                });
                            });
                    }
                });
            },
            addSchedule: function () {
                axios
                    .post('/schedule/add', {
                        stationDepartureName: this.stationDeparture,
                        stationArrivalName: this.stationArrival,
                        trainName: this.trainSchedule,
                        dateDeparture: this.dateDeparture.replace("T", " ") + ":00",
                        dateArrival: this.dateArrival != "" ? this.dateArrival.replace("T", " ") + ":00" : ''
                    })
                    .then(response => {
                        console.log(JSON.stringify(response));
                        swal({
                            title: 'Congratulations!', text: 'You add new schedule!', type: 'success'
                        });
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
            addTrain: function () {
                axios
                    .post('/train/add', {
                        trainName: this.train,
                        cntCarriage: this.cntCarriage,
                        cntSeats: this.cntSeats
                    })
                    .then(response => {
                        console.log(JSON.stringify(response));
                        swal({
                            title: 'Congratulations!', text: 'You add new train!', type: 'success'
                        });
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
        }
    });
</script>
</html>