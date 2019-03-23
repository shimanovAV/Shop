<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="customtags" prefix="ctg"%>
<html>
<head>
    <link rel="stylesheet" href="css/bootstrap.min.css" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="resource.text" var="locale"/>

    <fmt:message bundle="${locale}" key="label.sign.in" var="signIn"/>
    <fmt:message bundle="${locale}" key="label.registration" var="registration"/>
    <fmt:message bundle="${locale}" key="label.language" var="language"/>
    <fmt:message bundle="${locale}" key="hint.search" var="search"/>
    <fmt:message bundle="${locale}" key="hint.login" var="login"/>
    <fmt:message bundle="${locale}" key="hint.password" var="password"/>
    <fmt:message bundle="${locale}" key="hint.name" var="name"/>
    <fmt:message bundle="${locale}" key="hint.email" var="email"/>
    <fmt:message bundle="${locale}" key="hint.birthday" var="birthday"/>
    <fmt:message bundle="${locale}" key="label.menu.new" var="New"/>
    <fmt:message bundle="${locale}" key="label.menu.catalog" var="catalog"/>
    <fmt:message bundle="${locale}" key="label.menu.sales" var="sales"/>
    <fmt:message bundle="${locale}" key="error.message.wrong.password" var="wrongPassword"/>
</head>
<body>
<c:if test="${sessionScope.user==null}" var="isLogin">
    <div class="container-fluid bg-light font-italic">
        <div class="top_line">
            <ctg:language value="${language}" pageURL="${pageContext.request.requestURL}"/>
            <c:if test="${sessionScope.error==true}">
                <strong style="color: red">
                    ${wrongPassword}
                </strong>
            </c:if>
            <form action="FindProductByInfo" method="post" >
                <input type="hidden" name="Command" value="FindProduct"/>
                <input type="hidden" name="page" value="${pageContext.request.requestURL}" />
                    <input class="search_input" type="text" name="productInfo" placeholder=${search}>
                    <button class="myButton" type="submit"><i class="fa fa-search"></i></button>
            </form>
        </div>

        <div>
            <a class="text-right" href="Signin" data-target="#signin"
               data-toggle="modal">${signIn}</a>/<a href="Registrate" data-target="#registrate"
                                                    data-toggle="modal">${registration}</a>
        </div>

        <p id="logotip">
            <a href="Start"><img src=".//pictures//ShopName.png" alt="Responsive image"></a>
        </p>

        <div>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="NewProduct?Command=NEWPRODUCTS">${New}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Start">${catalog}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ProductsOnSales?Command=SALEPRODUCTS">${sales}</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
        </c:if>
        <c:if test="${isLogin==false}">
            <c:if test="${(sessionScope.user.isAccessLevel())}" var="isAdmin">
                <jsp:include page = "/WEB-INF/header/adminHeader.jsp" flush="true"/>
            </c:if>
            <c:if test="${isAdmin==false}">
                <jsp:include page = "/WEB-INF/header/userHeader.jsp" flush="true"/>
            </c:if>
        </c:if>
    </div>
<hr/>
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
                        <input type="hidden" name="page" value=${pageContext.request.requestURI}/>
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
    <div class="modal" id="registrate">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3>${registration}</h3>
                    <button type="button" class="close" data-dismiss="modal"> &times;</button>
                </div>
                <div class="modal-body">
                    <form class="form-group" action="Registration" method="post">
                        <input type="hidden" name="Command" value="Registration"/>
                        <input type="hidden" name="page" value=${pageContext.request.requestURL}/>
                        <i class="fa fa-user fa-2x" style="color:#8d6e63"></i>
                        <label>${name}</label>
                        <input type="text" name="name" class="form-control"/><br/>
                        <i class="fas fa-birthday-cake fa-2x"style="color:#8d6e63"></i>
                        <label>${birthday}</label>
                        <input type="date" name="birthday" class="form-control"/><br/>
                        <i class="fas fa-envelope fa-2x" style="color:#8d6e63"></i>
                        <label>${email}</label>
                        <input type="text" name="email" class="form-control"/><br/>
                        <i class="fa fa-user fa-2x"style="color:#8d6e63"></i>
                        <label>${login}</label>
                        <input type="text" name="login" class="form-control"/><br/>
                        <i class="fa fa-lock fa-2x"style="color:#8d6e63"></i>
                        <label>${password}</label>
                        <input type="password" name="password" class="form-control"/><br/>
                        <input class="btn" type="submit" value="${registration}"/><br/>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </div>


</body>
</html>
