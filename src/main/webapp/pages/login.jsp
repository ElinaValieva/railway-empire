<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 22.06.2018
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <sec:csrfMetaTags />
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/login.css">
    <link rel="stylesheet" href="/static/css/railway.css">
    <link rel="stylesheet" href="/static/css/style.css">
    <script src="/static/js/ajaxRequest.js"></script>
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
            <input name="username" id="userNameUser" type="email" placeholder="login" required/>
            <input name="password" id="passwordUser" type="password" placeholder="password" required/>
            <button id="loginUser">Sign in</button>
            <p class="message">Not registered? <a href="/registration">Create an account</a></p>
        </form>
    </div>
</div>
</body>
</html>
