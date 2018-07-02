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
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/profile.css">
    <link rel="stylesheet" href="/static/css/railway.css">
</head>
<body>
<div class="d-flex  align-items-center p-3 bg-dark  box-shadow">
    <h5 class="my-0 mr-md-auto font-weight-normal text-warning">RAILWAY EMPIRE</h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-light" href="/home">Home</a>
        <a class="p-2 text-light" href="/schedule">Schedule</a>
        <a class="p-2 text-light" href="/train">Train</a>
        <a class="p-2 text-light" href="/station">Station</a>
    </nav>
    <a class="btn btn-outline-warning" href="/login">Sign out</a>
</div>
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
            <div class="row align-items-center">
                <div class="col">
                    <input type="radio" name="sex" value="male" ${user.sex.equals('male') ? 'checked' : ''}/>
                    <label>male</label>
                </div>
                <div class="col"><input type="radio" name="sex"
                                        value="female" ${user.sex.equals('female') ? 'checked' : ''}/>
                    <label>female</label>
                </div>
            </div>
            <button>edit</button>
        </form>
    </div>
</div>
</body>
</html>