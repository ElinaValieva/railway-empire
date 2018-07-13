<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 12.07.2018
  Time: 0:42
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
    <link rel="stylesheet" href="/static/css/map.css">
    <link rel="stylesheet" href="/static/css/sweetalert2.css">
    <script src="/static/js/plugins/jquery-3.3.1.js"></script>
    <script src="/static/js/plugins/sweetalert2.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAKFWBqlKAGCeS1rMVoaNlwyayu0e0YRes"></script>
    <script src="/static/js/ajaxRequest.js"></script>
    <script src="/static/js/tickets.js"></script>
    <script src="/static/js/usersMap.js"></script>
</head>
<body class="bg-dark">
<div class="d-flex  align-items-center p-3 bg-dark  box-shadow">
    <h5 class="my-0 mr-md-auto font-weight-normal text-warning">RAILWAY EMPIRE</h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-light" href="#">Schedule</a>
        <a class="p-2 text-light" href="#">Train</a>
        <a class="p-2 text-light" href="#">Station</a>
        <a class="p-2 text-light" href="#">Profile</a>
    </nav>
    <a class="btn btn-outline-warning" href="#">Sign out</a>
</div>
<div id="mapUser"></div>
</body>
</html>
