
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="resource.text" var="locale"/>

    <fmt:message bundle="${locale}" key="hint.login" var="login"/>
    <fmt:message bundle="${locale}" key="hint.password" var="password"/>
    <fmt:message bundle="${locale}" key="label.language" var="language"/>
</head>
<body>
<form action="SignIn" method="post">
    <ctg:language value="${language}" pageURL="${pageContext.request.requestURL}"/>
    <input type="hidden" name="Command" value="SignIn"/>
    ${login}<br/>
    <input type="text" name="login" value=""/><br/>
    ${password}<br/>
    <input type="password" name="password" value=""/><br/>
    <input type="submit"  value="SignIn"/><br/>
</form>
<form action="Registrate" method="post">
    <input type="hidden" name="Registration" value="Registration"/>
    <input type="submit"  value="Registration"/><br/>
</form>
</body>
</html>

