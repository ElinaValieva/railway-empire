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
    <style type="text/css">
        <%@include file="/resources/css/login.css"%>
        <%@include file="/resources/css/railway.css"%>
        <%@include file="/resources/css/style.css"%>
    </style>
    <link href="<c:url value="/resources/css/login.css"/>" rel="stylesheet"/>
    <script src="/resources/js/login.js" rel="script"></script>
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
