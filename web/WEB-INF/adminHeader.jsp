<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" href="css/bootstrap.min.css" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/mainpage.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="resources.text" var="locale"/>

    <fmt:message bundle="${locale}" key="label.sign.in" var="signIn"/>
    <fmt:message bundle="${locale}" key="label.sign.out" var="signOut"/>
    <fmt:message bundle="${locale}" key="label.registration" var="registration"/>
    <fmt:message bundle="${locale}" key="label.language.en" var="langEn"/>
    <fmt:message bundle="${locale}" key="label.language.ru" var="langRu"/>
    <fmt:message bundle="${locale}" key="hint.search" var="search"/>
    <fmt:message bundle="${locale}" key="hint.login" var="login"/>
    <fmt:message bundle="${locale}" key="hint.password" var="password"/>
    <fmt:message bundle="${locale}" key="hint.name" var="name"/>
    <fmt:message bundle="${locale}" key="hint.email" var="email"/>
    <fmt:message bundle="${locale}" key="hint.birthday" var="birthday"/>
    <fmt:message bundle="${locale}" key="label.menu.new" var="New"/>
    <fmt:message bundle="${locale}" key="label.menu.catalog" var="catalog"/>
    <fmt:message bundle="${locale}" key="label.menu.sales" var="sales"/>
</head>
<body>
    <div class="container-fluid bg-light font-italic">
        <div>
            <c:if test="${sessionScope.language==\"ru\"}" var="rusLang">
                <a class="lang" href="ChangeLanguage?Command=CHANGELANGUAGE&language=en&page=${pageContext.request.requestURL}">${langEn}</a>
            </c:if>
            <c:if test="${rusLang==false}">
                <a class="lang" href="ChangeLanguage?Command=CHANGELANGUAGE&language=ru&page=${pageContext.request.requestURL}">${langRu}</a>
            </c:if>
        </div>
        <div class="search-box">
            <input class="search-txt" type="search" placeholder=${search}>
            <div class="search-btn">
                <a> <i class="fa fa-search"></i></a>
            </div>
        </div>
        <c:if test="${sessionScope.user==null}" var="isLogin">
            <a class="text-right" href="Signin" data-target="#signin"
               data-toggle="modal">${signIn}</a>/<a href="Registrate" data-target="#registrate"
            data-toggle="modal">${registration}</a>
        </c:if>
        <c:if test="${isLogin==false}">
            <a class="text-right"  href="SignOut?Command=SIGNOUT&page=${pageContext.request.requestURL}">${signOut}</a>
        </c:if>

        <div class="modal" id="signin">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3>${signIn}</h3>
                        <button type="button" class="close" data-dismiss="modal"> &times;</button>
                    </div>
                    <div class="modal-body">
                        <form class="form-group" action="SignIn" method="post">
                            <input type="hidden" name="Command" value="SignIn"/>
                            <input type="hidden" name="page" value=${pageContext.request.requestURL}/>
                            <i class="fa fa-user fa-2x"style="color:#8d6e63"></i> <label>${login}</label>
                            <input type="text" name="login" class="form-control"/><br/>
                            <i class="fa fa-lock fa-2x"style="color:#8d6e63"></i><label>${password}</label>
                            <input type="password" name="password" class="form-control"/><br/>
                            <input class="btn" type="submit"  value="${signIn}"/><br/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container-fluid bg-light ">
        <a href="index.jsp"><img src=".//pictures//ShopName.png" class="rounded mx-auto d-block" alt="Responsive image"></a>
    </div>

    <div>
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
                        <a class="nav-link" href="catalog.jsp">${catalog}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="adminSales">${sales}</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</body>
</html>
