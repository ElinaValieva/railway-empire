<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 20.06.2018
  Time: 0:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>${startPage}</title>
    <style>
        <%@include file="/resources/css/login.css"%>
        <%@include file="/resources/css/style.css"%>
        <%@include file="/resources/css/railway.css"%>
    </style>
</head>
<body>
<h1>${message}</h1>
<a href="/home/update">USER PROFILE</a>
<a href="/station">STATION</a>
<a href="/train">TRAIN</a>
<a href="/schedule">SCHEDULE</a>

<div class="wrapper">
    <div class="background"></div>
    <div class="rocks_1"></div>
    <div class="rocks_2"></div>
    <div class="rails"></div>
    <div class="train"></div>
    <div class="ground"></div>
    <div class="lights"></div>
    <div class="moon"></div>
</div>
</body>
</html>
