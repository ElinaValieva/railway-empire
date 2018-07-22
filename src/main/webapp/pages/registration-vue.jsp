<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 21.07.2018
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <sec:csrfMetaTags/>
    <title>RAILWAY EMPIRE</title>
    <link rel="stylesheet" href="/static/css/login.css">
    <link rel="stylesheet" href="/static/css/railway.css">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/sweetalert2.css">
    <script src="/static/js/plugins/sweetalert2.js"></script>
    <script src="/static/js/plugins/vue.js"></script>
    <script src="/static/js/plugins/axios.js"></script>
</head>
<body>
<div id="app">
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
            <form class="form" @submit.prevent="submitForm">
                <input v-model="firstName" id="firstName" type="text" placeholder="firstName" required
                       pattern="[A-Za-zА-Яа-яЁё]{3,15}" title="Name must contain only letters"/>

                <input v-model="lastName" id="lastName" type="text" placeholder="lastName" required
                       pattern="[A-Za-zА-Яа-яЁё]{3,15}" title="last name must contain only letters"/>

                <input v-model="login" id="login" type="email" placeholder="e-mail address" required minlength="5"/>

                <input v-model="password" id="password" type="password" placeholder="password" required minlength="6"
                       title="Password length mist be longer than 5 letters"/>

                <button id="registrationBtn" type="submit">create</button>
                <p class="message">Already registered? <a href="/login">Sign In</a></p>
            </form>
        </div>
    </div>
</div>
<script src="/static/js/vue/registration.js">
</script>
</body>
</html>
