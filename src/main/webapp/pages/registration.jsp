<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 22.06.2018
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <sec:csrfMetaTags />
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/login.css">
    <link rel="stylesheet" href="/static/css/railway.css">
    <link rel="stylesheet" href="/static/css/style.css">
    <script src="/static/js/jquery-3.3.1.js"></script>
    <script src="/static/js/registration.js"></script>
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
        <form class="form">
            <input id="firstName" type="text" placeholder="firstName" required/>
            <input id="lastName" type="text" placeholder="lastName" required/>
            <input id="login" type="email" placeholder="e-mail address" required/>
            <input id="password" type="password" placeholder="password" required/>
            <button id="registrationBtn" type="submit">create</button>
            <p class="message">Already registered? <a href="/login">Sign In</a></p>
        </form>
    </div>
</div>
</body>
</html>
