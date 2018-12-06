<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 02.12.2018
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>MyJSP</title>
  </head>
  <body>
  <form action="SignIn" method="post">
    <input type="hidden" name="SignIn" value="SignIn"/>
    Enter login:<br/>
    <input type="text" name="login" value=""/><br/>
    Enter password:<br/>
    <input type="password" name="password" value=""/><br/>
    <input type="submit"  value="SignIn"/><br/>
  </form>
  <form action="Registrate" method="post">
    <input type="hidden" name="Registration" value="Registration"/>
    <input type="submit"  value="Registration"/><br/>
  </form>
  </body>
</html>
