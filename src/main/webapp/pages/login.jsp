<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 22.06.2018
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <sec:csrfMetaTags/>
    <title>RAILWAY EMPIRE: LOGIN</title>
    <link rel="stylesheet" href="/static/css/login.css">
    <link rel="stylesheet" href="/static/css/railway.css">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/sweetalert2.css">
    <script src="/static/js/plugins/jquery-3.3.1.js"></script>
    <script src="/static/js/plugins/sweetalert2.js"></script>
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
        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
            <script>
                swal({
                    title: 'Oops..',
                    text: 'Wrong login or password. Try again!',
                    type: 'error'
                });
            </script>
        </c:if>
    </div>
</div>
</body>
</html>
