<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello!</title>
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
</head>
<body>
<div class="header">
    <div class="mainText"><h2>BGLand shop</h2></div>
    <div class="welcome"><h3>Welcome, ${user.getName()}!</h3></div>
    <form action="/main" method="post"><input class="logout" type="submit" value="Logout"></form>
</div>
<div class="products">
    <c:forEach items="${products}" var="item">
        <p>Product: ${item.getName()}<br>
            Cost: ${item.getCost()}$<br>
            Description: ${item.getDescription()}</p>
            <form action="/bucket" method="post">
                <button name="button" value="${item.getName()}" onclick="alert('${item.getName()} added to bucket!')">
                    Add to bucket!
                </button>
            </form>
        <hr>
    </c:forEach>
    <a href="/bucket">To bucket</a>
</div>
</body>
</html>
