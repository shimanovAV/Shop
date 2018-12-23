
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="resources.text" var="locale"/>

    <fmt:message bundle="${locale}" key="hint.login" var="login"/>
    <fmt:message bundle="${locale}" key="hint.password" var="password"/>
    <fmt:message bundle="${locale}" key="label.language.en" var="langEn"/>
    <fmt:message bundle="${locale}" key="label.language.ru" var="langRu"/>
</head>
<body>
<form action="SignIn" method="post">
    <c:if test="${sessionScope.language==\"ru\"}" var="rusLang">
        <a href="ChangeLanguage?Command=CHANGELANGUAGE&language=en&page=${pageContext.request.requestURL}">${langEn}</a>
    </c:if>
    <c:if test="${rusLang==false}">
        <a href="ChangeLanguage?Command=CHANGELANGUAGE&language=ru&page=${pageContext.request.requestURL}">${langRu}</a>
    </c:if>
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

