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
            <form:form action="/home/update" method="post">
                <dxa:csrf-token/>
                <input type="hidden" name="id" value="${user.id}">
                <input name="firstName" path="firstName" type="text" placeholder="firstName" value="${user.firstName}"
                       required/>
                <input name="lastName" path="lastName" type="text" placeholder="lastName" value="${user.lastName}"
                       required/>
                <input name="login" path="login" type="email" placeholder="login" value="${user.login}" required/>
                <input name="password" path="password" type="password" placeholder="password" value="${user.password}"
                       required/>
                <input name="birthDay" path="birthday" type="date" placeholder="birthday" value="${user.birthDay}"/>
                <input name="sex" path="sex" type="radio"
                       value="${user.sex}"  ${user.sex.equals('male') ? 'checked' : ''}>male
                <input name="sex" path="sex" type="radio"
                       value="${user.sex}" ${user.sex.equals('female') ? 'checked' : ''}/>female
                <%--<input name="sex" type="text" placeholder="sex" value="${user.sex}">--%>
                <button>edit</button>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>