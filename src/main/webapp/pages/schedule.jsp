<%--
  Created by IntelliJ IDEA.
  User: Elina
  Date: 29.06.2018
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/schedule/add" method="post">
    <button>CREATE</button>
</form>
<form action="/schedule/all" method="get">
    <button>ALL</button>
</form>
<form action="/schedule/getByDate" method="get">
    <button>GET BY DATE</button>
</form>
<form action="/schedule/direct" method="get">
    <button>GET BY DATE and STATIONs</button>
</form>
<form action="/schedule/getByTrain" method="get">
    <button>GET BY DATE and TRAIN</button>
</form>
<form action="schedule/transfer" method="get">
    <button>TRANSFER</button>
</form>
</body>
</html>
