<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
</head>
<body>
<div class="header">
    <div class="mainText"><h2>BGLand shop</h2></div>
    <div class="signInSignUp">
        <a class="signIn" href="/main">To main</a>
        <a class="signIn" href="/login">Sign In</a>
    </div>
</div>
<div class="products">
    <h1>Create new account!</h1>

    <form action="" method="post">
        <span style="color: red">${errfirstName}</span>
        <p><input name="firstName" type="text" value="${firstName}" placeholder="First name"></p>

        <span style="color: red">${errsecondName}</span>
        <p><input name="secondName" type="text" value="${secondName}" placeholder="Second Name"></p>

        <span style="color: red">${erremail}</span>
        <p><input name="email" type="text" value="${email}" placeholder="Email Address"></p>

        <span style="color: red">${errpassword}</span>
        <p><input name="password" type="password" placeholder="Password"></p>

        <span style="color: red">${errrepassword}</span></p>
        <p><input name="repassword" type="password" placeholder="Confirm Password"></p>

        <span style="color: red">${errphoneNumber}</span>
        <p><input name="phoneNumber" type="text" value="${phoneNumber}" placeholder="Phone number"></p>

        <span style="color: red">${erraddres}</span>
        <p><input name="addres" type="text" value="${addres}" placeholder="Addres!"></p>

        <input name="btn" type="submit" value="Create Account">
    </form>
</div>
</body>
</html>
