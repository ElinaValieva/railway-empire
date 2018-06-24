<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 22.06.2018
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        <%@include file="../css/login.css"%>
        <%@include file="../css/style.css"%>
        <%@include file="../css/railway.css"%>
    </style>
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
        <div class="form">
            <form class="login-form" action="/login" method="post">
                <input name="login" type="text" placeholder="username"/>
                <input name="password" type="password" placeholder="password"/>
                <button>login</button>
                <p class="message">Not registered? <a href="/registration">Create an account</a></p>
            </form>
        </div>
    </div>
</div>
</body>
</html>
