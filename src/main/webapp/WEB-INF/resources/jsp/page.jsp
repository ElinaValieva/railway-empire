<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 24.06.2018
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <style>
        <%@include file="../css/style.css"%>
    </style>
    <title>WELCOME BEACH</title>
</head>
<body>
<form:form method="post" action="/page">
    <form:errors path="*" cssClass="error"/>
    <input path="firstName" name="firstName" type="text" placeholder="name"/>
    <errors path="firstName" class = "error"/>
    <div c:if ="${param.error}">A</div>
    <button>past</button>
</form:form>
<div c:if ="${param.error}">AAA</div>
</body>
</html>
