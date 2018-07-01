<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 22.06.2018
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/static/css/login.css"%>
        <%@include file="/static/css/railway.css"%>
        <%@include file="/static/css/style.css"%>
        <%@include file="/static/js/login.js"%>
    </style>

    <%--<script rel="script" type="text/javascript" src="<c:url value="/static/js/login.js"/>"></script>--%>
    <%--<link rel="stylesheet" type="text/css" href="/static/css/test.css">--%>
    <%--<link rel="stylesheet" type="text/css" href="/static/css/login.css">--%>
    <%--<link rel="stylesheet" type="text/css" href="/static/css/railway.css">--%>
    <%--<link rel="stylesheet" type="text/css" href="/static/css/style.css">--%>

    <base href="/">
</head>
<body>
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
        <form class="form" action="/login" method="post">
            <input name="username" type="email" placeholder="login" required/>
            <input name="password" type="password" placeholder="password" required/>
            <button>login</button>
            <p class="message">Not registered? <a href="/registration">Create an account</a></p>
        </form>
    </div>
</div>
</body>
</html>
