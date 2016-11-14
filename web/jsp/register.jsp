<%--
  Created by IntelliJ IDEA.
  User: soffietto
  Date: 12.11.16
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
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
</body>
</html>
