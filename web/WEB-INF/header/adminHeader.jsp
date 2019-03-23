<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="customtags" prefix="ctg"%>
<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.min.css" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">


    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="resource.text" var="locale"/>

    <fmt:message bundle="${locale}" key="label.sign.out" var="signOut"/>
    <fmt:message bundle="${locale}" key="label.language" var="language"/>
    <fmt:message bundle="${locale}" key="hint.search" var="search"/>
    <fmt:message bundle="${locale}" key="label.menu.orders" var="orders"/>
    <fmt:message bundle="${locale}" key="label.menu.catalog" var="catalog"/>
    <fmt:message bundle="${locale}" key="label.menu.sales" var="sales"/>
</head>
<body>
    <div class="container-fluid bg-light font-italic">
        <div>
            <div class="top_line">
                <ctg:language value="${language}" pageURL="${pageContext.request.requestURL}"/>
                <form action="FindProductByInfo" method="post" >
                    <input type="hidden" name="Command" value="FindProduct"/>
                    <input type="hidden" name="page" value="${pageContext.request.requestURL}" />
                    <input class="search_input" type="text" name="productInfo" placeholder=${search}>
                    <button class="myButton" type="submit"><i class="fa fa-search"></i></button>
                </form>
            </div>

            <div>
                <a class="text-right"  href="SignOut?Command=SIGNOUT&page=${pageContext.request.requestURL}">${signOut}</a>
            </div>

            <p id="logotip">
                <a href="Start"><img src=".//pictures//ShopName.png" alt="Responsive image"></a>
            </p>
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
                        <a class="nav-link" href="OrderProduct?Command=ORDERPRODUCT">${orders}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Start">${catalog}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="adminSale">${sales}</a>
                    </li>
                </ul>
            </div>
        </nav>
        </div>
    </div>
<hr/>
</body>
</html>
