<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 25.06.2018
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>RAILWAY EMPIRE: PROFILE USER</title>
    <sec:csrfMetaTags/>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/profile.css">
    <link rel="stylesheet" href="/static/css/railway.css">
    <link rel="stylesheet" href="/static/css/sweetalert2.css">
    <script src="/static/js/plugins/jquery-3.3.1.js"></script>
    <script src="/static/js/plugins/sweetalert2.js"></script>
    <script src="/static/js/ajaxRequest.js"></script>
    <script src="/static/js/profile.js"></script>
</head>
<body>
<sec:authorize access="hasRole('ROLE_USER')">
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
                <a class="p-2 text-light" href="/showDeletedItems">Audit</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER')">
                <a class="p-2 text-light" href="/trips">Trips</a>
                <a class="p-2 text-light" href="/userMap">Show trips on map</a>
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
            <form class="form" action="/home/update" method="post">
                <input name="firstName" id="firstName" type="text" placeholder="firstName"
                       required/>
                <input name="lastName" id="lastName" type="text" placeholder="lastName"
                       required/>
                <input name="login" id="login" type="email" placeholder="login" required/>
                <input name="birthDay" id="birthday" type="date" placeholder="birthday"/>
                <div class="row align-items-center">
                    <div class="col">
                        <input type="radio" id="radioMale" name="sex" value="male"/>
                        <label>male</label>
                    </div>
                    <div class="col">
                        <input type="radio" id="radioFemale" name="sex"
                               value="female"/>
                        <label>female</label>
                    </div>
                </div>
                <button id="editProfile">edit</button>
            </form>
        </div>
    </div>
</sec:authorize>
</body>
</html>