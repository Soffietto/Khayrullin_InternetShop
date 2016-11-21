<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
</head>

<body>

<div class="header">
    <div class="mainText"><h2>BGLand shop</h2></div>
    <div class="backTo">
        <a class="backToText" href="/admin">Go back</a>
    </div>
    <div class="welcome"><h3>Welcome, Admin!</h3></div>
    <form action="/main" method="post"><input class="logout" type="submit" value="Logout"></form>
</div>

<div class="products">
    <h1>Add new product: </h1>
    <form action="" method="post">
        <p><input name="productName" type="text" value="${productName}" placeholder="Product name"></p>

        <p><input name="productCost" type="number" value="${productCost}" placeholder="Product cost"></p>

        <p><input class="description" name="productDescription" type="text" value="${productDescription}" placeholder="Product Description"></p>

        <input name="btn" type="submit" value="Add product">
    </form>
</div>
</body>
</html>
