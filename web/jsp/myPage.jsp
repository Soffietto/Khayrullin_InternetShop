<%--
  Created by IntelliJ IDEA.
  User: soffietto
  Date: 14.11.16
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Page</title>
</head>
<body>
    <h1>What's up, ${name}</h1>
    <div>
        <span>Your phone: ${phoneNumber} </span>
        <span>Your address: ${addres}</span>
    </div>
    <a href="/addProduct">Add product!</a>
</body>
</html>
