<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thank You!</title>
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
</head>
<body>
<div class="header">
    <div class="mainText"><h2>BGLand shop</h2></div>
    <div class="welcome"><h3>Welcome, ${user.getName()}!</h3></div>
    <form action="/main" method="post"><input class="logout" type="submit" value="Logout"></form>
</div>
<div class="products">
    <h1>Thank you for your purchase!</h1>
    <a href="/main">Back to shop!</a>
</div>
</body>
</html>
