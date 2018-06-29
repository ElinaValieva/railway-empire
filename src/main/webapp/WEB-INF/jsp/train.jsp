<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 27.06.2018
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>HELLO</h1>
<script src="https://code.jquery.com/jquery-3.3.1.slim.js"></script>
<div>
    <form action="/train/add" method="post">
        <input type="text" name="name" id="nameTrain" placeholder="name train">
        <input type="number" name="cntCarriage" placeholder="Carriages">
        <input type="number" name="cntSeats" placeholder="Seats">
        <button>ADD TRAIN</button>
    </form>
</div>
<%--<script>--%>
<%--$(function () {--%>
<%--alert('hello');--%>
<%--$('#btnAdd').click(function () {--%>
<%--var train = {--%>
<%--name: $('#nameTrain').val(),--%>
<%--carriage: $('#cntCarriage').val()--%>
<%--};--%>
<%--alert(JSON.stringify(train));--%>

<%--$.ajax({--%>
<%--url: "http://localhost:8080/test/add/",--%>
<%--method: "POST",--%>
<%--data: JSON.stringify(train)--%>
<%--}).done(function () {--%>
<%--alert('access');--%>
<%--}).fail(function () {--%>
<%--alert('error')--%>
<%--});--%>
<%--});--%>
<%--});--%>
<%--</script>--%>
</body>
</html>
