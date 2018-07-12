<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 08.07.2018
  Time: 11:28
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/mail.css">
    <link href="/static/css/style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="static/css/clean-blog.min.css" type="text/css">
    <script src="/static/js/ajaxRequest.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.25.0/dist/sweetalert2.all.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/static/js/searchingSchedule.js"></script>
</head>
<body class="bg-dark pointer">
<div class="d-flex  align-items-center p-3 bg-dark  box-shadow">
    <h4 class="my-0 mr-md-auto font-weight-normal text-warning">RAILWAY EMPIRE</h4>
    <nav class="my-1 my-md-0 mr-md-3">
        <a class="p-4 text-light" href="/home">Home</a>
        <a class="p-4 text-light" href="/createSchedule">Add schedule</a>
        <a class="p-4 text-light" href="/train">Train</a>
        <a class="p-4 text-light" href="/station">Station</a>
        <a class="p-4 text-light" href="/home/update">Profile</a>
    </nav>
    <a class="btn btn-outline-warning btn-lg" href="#">Sign out</a>
</div>
<div id="myCarousel" class="carousel" data-interval="false">
    <!-- Indicators -->
    <ol class=" carousel-indicators">
        <li data-slide-to="0" class="active border-light bg-warning"></li>
        <li data-slide-to="1" class="border-light bg-warning"></li>
        <li data-slide-to="2" class="border-light bg-warning"></li>
        <li data-slide-to="3" class="border-light bg-warning"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">

        <div class="item active">
            <header class="masthead" style="background-image: url('/static/images/bg.png')">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 col-md-10 mx-auto">
                            <div class="site-heading">
                                <form class="form-horizontal login_box2 align-items-center">
                                    <div class="row">
                                        <div class="col form-group">
                                            <label class="text-warning font-weight-bold">FROM</label>
                                            <input id="stationDepartureSearchingByAllParameters" type="text"
                                                   class="form-control text-center text-dark"
                                                   placeholder="DEPARTURE STATION" required>
                                        </div>
                                        <div class="col-1"></div>
                                        <div class="col form-group">
                                            <label class="text-warning font-weight-bold">TO</label>
                                            <input id="stationArrivalSearchingByAllParameters" type="text"
                                                   class="form-control text-center text-dark"
                                                   placeholder="ARRIVAL STATION" required>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <div class="col form-group-4">
                                            <label class="text-warning font-weight-bold">DATE DEPARTURE</label>
                                            <input id="dateDepartureSearchingByAllParameters" type="date"
                                                   class="form-control text-center" width="50" required>
                                        </div>
                                        <div class="col-1"></div>
                                        <div class="col form-group4">
                                            <label class="text-warning font-weight-bold">DATE ARRIVAL</label>
                                            <input id="dateArrivalSearchingByAllParameters" type="date"
                                                   class="form-control text-center" width="50" required>
                                        </div>
                                        <div class="col-1"></div>
                                        <div class="col form-group">
                                            <label class="text-warning font-weight-bold">TRAIN</label>
                                            <input id="trainSearchingByAllParameters" type="text"
                                                   class="form-control text-center" width="50" required>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="text-center">
                                        <button id="searchingBtnByAllParameters" type="button"
                                                class="btn btn-lg btn-outline-warning">search schedule
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
        </div>

        <div class="item">
            <header class="masthead" style="background-image: url('/static/images/bg.png')">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 col-md-10 mx-auto">
                            <div class="site-heading">
                                <form class="form-horizontal login_box align-items-center">
                                    <div class="row text-center">
                                        <div class="col form-group">
                                            <label class="text-warning font-weight-bold">DATE DEPARTURE</label>
                                            <input id="dateDepartureInSearchingByDates" type="date"
                                                   class="form-control text-center text-dark"
                                                   placeholder="DEPARTURE DATE" required>
                                        </div>
                                        <div class="col-1"></div>
                                        <div class="col form-group">
                                            <label class="text-warning font-weight-bold">DATE ARRIVAL</label>
                                            <input id="dateArrivalInSearchingByDates" type="date"
                                                   class="form-control text-center text-dark"
                                                   placeholder="ARRIVAL DATE" required>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="text-center">
                                        <button type="button" id="searchingBtnByDates"
                                                class="btn btn-lg btn-outline-warning">search schedule
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </header>


        </div>

        <div class="item">
            <header class="masthead" style="background-image: url('/static/images/bg.png')">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 col-md-10 mx-auto">
                            <div class="site-heading">
                                <form class="form-horizontal login_box align-items-center">
                                    <div class="row">
                                        <div class="col form-group">
                                            <label class="text-warning font-weight-bold">FROM</label>
                                            <input id="stationDepartureSearchingByStationsAndDates"
                                                   type="text" class="form-control text-dark text-xl-center"
                                                   placeholder="DEPARTURE STATION" required>
                                        </div>
                                        <div class="col-1"></div>
                                        <div class="col form-group">
                                            <label class="text-warning font-weight-bold">TO</label>
                                            <input id="stationArrivalSearchingByStationsAndDates"
                                                   type="text" class="form-control text-center text-dark"
                                                   placeholder="ARRIVAL STATION" required>
                                        </div>
                                        <div class="col-1"></div>
                                        <div class="col form-group">
                                            <label class="text-warning font-weight-bold">DATE DEPARTURE</label>
                                            <input id="dateDepartureSearchingByStationsAndDates"
                                                   type="date" class="form-control text-center" width="50" required>
                                        </div>
                                        <div class="col-1"></div>
                                        <div class="col form-group">
                                            <label class="text-warning font-weight-bold">DATE ARRIVAL</label>
                                            <input id="dateArrivalSearchingByStationsAndDates"
                                                   type="date" class="form-control text-center" width="50" required>
                                        </div>
                                    </div>
                                    <div id="checkBoxId">
                                        <input type="checkbox" id="with transfer"
                                               class="text-warning font-weight-normal">
                                        <label class="text-warning font-weight-normal">with transfer</label>
                                    </div>
                                    <div class="text-center">
                                        <button id="searchingBtnByStationsAndDates" type="button"
                                                class="btn btn-lg btn-outline-warning">search schedule
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </header>

        </div>

        <div class="item">
            <header class="masthead" style="background-image: url('/static/images/bg.png')">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 col-md-10 mx-auto">
                            <div class="site-heading">
                                <form class="form-horizontal login_box align-items-center">
                                    <div class="row text-center">
                                        <div class="col form-group">
                                            <label class="text-warning font-weight-bold">DATE DEPARTURE</label>
                                            <input id="dateDepartureInSearchingByDatesAndTrain" type="date"
                                                   class="form-control text-center text-dark"
                                                   placeholder="DEPARTURE DATE" required>
                                        </div>
                                        <div class="col-1"></div>
                                        <div class="col form-group">
                                            <label class="text-warning font-weight-bold">DATE ARRIVAL</label>
                                            <input id="dateArrivalInSearchingByDatesAndTrain" type="date"
                                                   class="form-control text-center text-dark"
                                                   placeholder="ARRIVAL DATE" required>
                                        </div>
                                        <div class="col-1"></div>
                                        <div class="col form-group">
                                            <label class="text-warning font-weight-bold">TRAIN</label>
                                            <input id="trainInSearchingByDatesAndTrain" type="text"
                                                   class="form-control text-center text-dark"
                                                   placeholder="TRAIN" required>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="text-center">
                                        <button type="button" id="searchingBtnByDatesAndTrain"
                                                class="btn btn-lg btn-outline-warning">search schedule
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </header>


        </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<div id="containerForSearching" style="display: none">
    <table class="table table-hover table-dark">
        <tbody class="text-warning text-center" id="containerForSearchingDirect">
        </tbody>
    </table>
    <table class="table table-hover table-dark">
        <tbody class="text-warning text-center" id="containerForSearchingTransfer">
        </tbody>
    </table>
</div>
</body>
</html>
