<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 25.06.2018
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
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
            <form class="register-form" method="post" action="/home/profile">
                <input name="firstName" path="firstName" type="text" placeholder="firstName"/>
                <errors path="firstName"/>
                <input name="lastName" path="lastName" type="text" placeholder="lastName"/>
                <errors path="lastName"/>
                <input name="login" path="login" type="text" placeholder="e-mail address"/>
                <errors path="login"/>
                <input name="password" path="password" type="password" placeholder="password"/>
                <errors path="password"/>
                <button>edit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>