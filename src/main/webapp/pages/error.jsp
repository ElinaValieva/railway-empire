<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 16.08.2018
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body style="background: url('/static/images/bg.png') no-repeat center fixed;
-webkit-background-size: cover;
-moz-background-size: cover;
-o-background-size: cover;
background-size: cover">
<h1 class="text-light text-center">Oops..</h1>
<h4 class="text-light text-center">Something went wrong</h4>
<img src="/static/images/error.png" style="display: block;
  margin-left: auto;
  margin-right: auto;
  margin-top: 5%;
  width: 32%;">
<button style="display: block;
  margin-left: 3%;"
        class="btn btn-outline-light text-light"
        onclick="location.href='http://localhost:8000/home'">Back
</button>
</body>
</html>
