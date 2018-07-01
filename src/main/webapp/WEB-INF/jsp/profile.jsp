<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 25.06.2018
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        <%@include file="/resources/css/profile.css"%>
        <%@include file="/resources/css/style.css"%>
        <%@include file="/resources/css/railway.css"%>
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
        <form class="form" action="/home/update" method="post">
            <dxa:csrf-token/>
            <input type="hidden" name="id" value="${user.id}">
            <input name="firstName" path="firstName" type="text" placeholder="firstName" value="${user.firstName}"
                   required/>
            <input name="lastName" path="lastName" type="text" placeholder="lastName" value="${user.lastName}"
                   required/>
            <input name="login" path="login" type="email" placeholder="login" value="${user.login}" required/>
            <input name="password" hidden path="password" type="password" placeholder="password"
                   value="${user.password}"
                   required/>
            <input name="birthDay" path="birthday" type="date" placeholder="birthday" value="${user.birthDay}"/>
            <div>
                <input type="radio" name="sex" value="male" ${user.sex.equals('male') ? 'checked' : ''}/>
                <label>male</label>
                <input type="radio" name="sex" value="female" ${user.sex.equals('female') ? 'checked' : ''}/>
                <label>female</label>
            </div>
            <button>edit</button>
        </form>
    </div>
</div>
</body>
</html>