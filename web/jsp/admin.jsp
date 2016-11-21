<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
</head>
<body>
<div class="header">
    <div class="mainText"><h2>BGLand shop</h2></div>
    <div class="welcome"><h3>Welcome, Admin!</h3></div>
    <form action="/main" method="post"><input class="logout" type="submit" value="Logout"></form>
</div>
<div class="products">
    <c:forEach items="${products}" var="item">
        <p>Product: ${item.getName()}<br>
            Cost: ${item.getCost()}$<br>
            Description: ${item.getDescription()}</p>
        <form action="/admin" method="post">
            <button name="button" type="submit" value="${item.getName()}" onclick="return confirm('Are you sure you want to delete ${item.getName()} from the shop?')">Delete</button>
        </form>
        <hr>
    </c:forEach>
    <a href="/addproduct">Add product</a>
</div>
</body>
</html>
