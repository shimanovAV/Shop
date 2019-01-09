<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.12.2018
  Time: 6:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="AddProduct" method="post">
    <input type="hidden" name="Command" value="AddProduct"/>
    Enter Name:<br/>
    <input type="text" name="name" value=""/><br/>
    Enter Email:<br/>
    <input type="text" name="email" value=""/><br/>
    Enter login:<br/>
    <input type="text" name="login" value=""/><br/>
    Enter password:<br/>
    <input type="password" name="password" value=""/><br/>
    <input type="submit"  value="Registration"/><br/>
</form>

</body>
</html>
