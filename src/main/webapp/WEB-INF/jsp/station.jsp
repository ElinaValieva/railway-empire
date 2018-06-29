<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 25.06.2018
  Time: 18:08
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
<form method="post" action="/station/add">
    <dxa:csrf-token/>
    <input name="name" type="text" placeholder="name station" required>
    <button>add station</button>
</form>
<form method="get" action="/station/all">
    <table>
        <tr>name</tr>
        <c:forEach items="${stations}" var="stations">
            <tr>
                <td><c:out value="${stations.name}"/></td>
                <td><a href="<c:url value='/station/delete/${stations.id}'/>">delete</a></td>
                <td><a href="/station/update/${stations.id}">edit</a></td>
            </tr>
        </c:forEach>
    </table>
</form>
<form method="post" action="/station/update">
    <dxa:csrf-token/>
    <input name="id" hidden value="${station.id}">
    <input name="name" type="text" placeholder="name station" value="${station.name}" required>
    <button>update station</button>
</form>
</body>
</html>
