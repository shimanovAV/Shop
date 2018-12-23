<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
  <link rel="stylesheet" href="css/bootstrap.min.css" >
  <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
  <link href="css/mainpage.css" rel="stylesheet">

  <fmt:setLocale value="${sessionScope.language}"/>
  <fmt:setBundle basename="resources.text" var="locale"/>

  <fmt:message bundle="${locale}" key="label.sign.in" var="signIn"/>
  <fmt:message bundle="${locale}" key="label.registration" var="registration"/>
  <fmt:message bundle="${locale}" key="label.menu.new" var="New"/>
  <fmt:message bundle="${locale}" key="label.menu.catalog" var="catalog"/>
  <fmt:message bundle="${locale}" key="label.menu.sales" var="sales"/>
  <fmt:message bundle="${locale}" key="label.language.en" var="langEn"/>
  <fmt:message bundle="${locale}" key="label.language.ru" var="langRu"/>
  <fmt:message bundle="${locale}" key="hint.search" var="search"/>

</head>
<body>
<div class="container-fluid bg-light font-italic text-right ">
  <c:if test="${sessionScope.language==\"ru\"}" var="rusLang">
    <a href="ChangeLanguage?Command=CHANGELANGUAGE&language=en&page=${pageContext.request.requestURL}">${langEn}</a>
  </c:if>
  <c:if test="${rusLang==false}">
    <a href="ChangeLanguage?Command=CHANGELANGUAGE&language=ru&page=${pageContext.request.requestURL}">${langRu}</a>
  </c:if>
  <div class="search-box">
    <input class="search-txt" type="search" placeholder=${search}>
    <div class="search-btn">
     <a> <i class="fa fa-search"></i></a>
    </div>
  </div>
   <a href="Signin">${signIn}</a>/<a href="Registrate">${registration}</a>
</div>

<div class="container-fluid bg-light ">
  <a href="index.jsp"><img src="ShopName.png" class="rounded mx-auto d-block" alt="Responsive image"></a>
</div>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <button class="navbar-toggler" type="button" data-toggle="collapse"
          data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav m-md-auto">
      <li class="nav-item">
        <a class="nav-link" href="#">${New}</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">${catalog}</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">${sales}</a>
      </li>
    </ul>
  </div>
</nav>
<script src="js/bootstrap.min.js"/>
</body>
</html>