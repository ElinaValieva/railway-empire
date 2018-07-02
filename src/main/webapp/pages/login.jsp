<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 22.06.2018
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Title</title>
    <link href="<c:url value ="/static/css/login.css" />" rel="stylesheet"/>
    <link href="<c:url value="/static/css/railway.css" />" rel="stylesheet"/>
    <link href="<c:url value="/static/css/style.css" />" rel="stylesheet"/>
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
