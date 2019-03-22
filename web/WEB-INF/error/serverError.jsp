<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
 <link href="css/mainpage.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="resource.text" var="locale"/>

    <fmt:message bundle="${locale}" key="error.message" var="sorry"/>
    <fmt:message bundle="${locale}" key="error.message.500" var="serverError"/>

</head>
<body class="text-center">
<nav class="navbar navbar-expand navbar-accent">
</nav>
<div class="col-sm-8 mx-auto">
    <h1>${sorry}</h1>
    <p>${serverError}</p>
</div>
<script src="/assets/js/bootstrap.min.js"/>
</body>
</html>