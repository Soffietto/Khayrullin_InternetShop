<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket</title>
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
</head>
<body>
<div class="header">
    <div class="mainText"><h2>BGLand shop</h2></div>
    <div class="welcome"><h3>Welcome, ${user.getName()}!</h3></div>
    <form action="/main" method="post"><input class="logout" type="submit" value="Logout"></form>
</div>
<div class="products">
    <c:forEach items="${bucket}" var="item">
        <p>Product: ${item.getName()}<br>
            Cost: ${item.getCost()}$<br>
            Description: ${item.getDescription()}</p>
        <form action="/removebucket" method="post">
            <button name="button" type="submit" value="${item.getName()}" onclick="return confirm('Are you sure you want to delete ${item.getName()} from your bucket&')">Delete</button>
        </form>
        <hr>
    </c:forEach>
    <c:choose>
        <c:when test="${!bucket.isEmpty()}">
            <h3>Total cost: ${cost}$</h3>
            <form action="/thankyou" method="post"><input type="submit" value="Buy"></form>
        </c:when>
        <c:otherwise>
            <h1>Sorry, your bucket is empty!</h1>
        </c:otherwise>
    </c:choose>
    <a href="/main">Back to main</a>

</div>
</body>
</html>
