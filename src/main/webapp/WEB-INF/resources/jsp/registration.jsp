<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 22.06.2018
  Time: 14:20
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
            <form:form class="register-form" method="post" action="/registration">
                <input name="firstName" path="firstName" type="text" placeholder="firstName"/>
                <form:errors path="firstName"/>
                <input name="lastName" path="lastName" type="text" placeholder="lastName"/>
                <form:errors path="lastName"/>
                <input name="login" path="login" type="text" placeholder="e-mail address"/>
                <form:errors path="login"/>
                <input name="password" path="password" type="password" placeholder="password"/>
                <form:errors path="password"/>
                <button>create</button>
                <p class="message">Already registered? <a href="/login">Sign In</a></p>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
