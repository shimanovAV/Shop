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

    <fmt:message bundle="${locale}" key="hint.change.product" var="changeProduct"/>
    <fmt:message bundle="${locale}" key="hint.name" var="name"/>
    <fmt:message bundle="${locale}" key="hint.description" var="description"/>
    <fmt:message bundle="${locale}" key="hint.category.boys" var="boys"/>
    <fmt:message bundle="${locale}" key="hint.category.girls" var="girls"/>
    <fmt:message bundle="${locale}" key="hint.price" var="price"/>
    <fmt:message bundle="${locale}" key="hint.quantity" var="quantity"/>
    <fmt:message bundle="${locale}" key="hint.image" var="image"/>
    <fmt:message bundle="${locale}" key="button.add.to.basket" var="addToBasket"/>
    <fmt:message bundle="${locale}" key="label.menu.new" var="New"/>
</head>
<body>
<jsp:useBean id="product" scope="request" type="by.etc.shop.entity.Product"/>
<div>
    <jsp:include page = "header.jsp" flush="true"/>
</div>
<c:if test="${sessionScope.user!=null$$sessionScope.user.isAccessLevel()}" var="isLogin">
    <button type="button" data-target="#changeProduct"
            data-toggle="modal" class="close" >
        <i class="fa fa-wrench" style="color:#8d6e63"></i></button>
</c:if>
<div class="container" >
    <div class="row">
        <div class="col-md-5">
            <img class="d-block w-100" src="${product.path}">
        </div>
        <div class="col-md-7">
            <c:if test="${product.isNew()}">
                <strong>
                    <span class="badge badge-pill danger-color">${New}</span>
                </strong>
            </c:if>
            <h2>${product.name}</h2>
        </div>

        <c:if test="${sessionScope.user!=null&&!(sessionScope.user.isAccessLevel())}">
        <form class="form-group" action="ChangeLike" method="post">
            <input type="hidden" name="Command" value="ChangeLike"/>
            <input type="hidden" name="page" value="${pageContext.request.requestURL}" />
            <input type="hidden" name="productId" value="${product.id}" />
            <input type="hidden" name="userId" value="${sessionScope.user.login}" />
        <button type="submit" class="close" >
            <c:if test="${product.hasLike(sessionScope.likes)}" var="isLogin">
            <i onclick="changeColor()" class="fa fa-heart" id="changeColor"  style="color:#8d6e63"></i></button>
            </c:if>
            <c:if test="${!(product.hasLike(sessionScope.likes))}" var="isLogin">
                <i onclick="changeColor()" class="fa fa-heart" id="changeColor"  style="color:#e0b8a6"></i></button>
            </c:if>
        </form>
        </c:if>
        <c:if test="${sessionScope.user==null}" var="isLogin">
            <form class="form-group" action="GoToSignIn" method="post">
                <button type="submit" class="close" >
                    <i onclick="changeColor()" class="fa fa-heart"   style="color:#e0b8a6"></i></button>
            </form>
        </c:if>
        <script type="text/javascript">
            var flag = true;
            function changeColor(){
                if (flag == true) {
                    $('#changeColor').css('color', '#8d6e63');
                    flag = false;
                }
                else {
                    $('#changeColor').css('color', '#e0b8a6');
                    flag = true;
                }
            }
        </script>

            <c:if test="${sessionScope.user!=null&&!(sessionScope.user.isAccessLevel())}">
                <form class="form-group" action="AddToBasket" method="post">
                    <input type="hidden" name="Command" value="AddToBasket"/>
                    <input type="hidden" name="productId" value="${product.id}" />
                    <input type="hidden" name="page" value="${pageContext.request.requestURL}" />
                    <input type="hidden" name="userId" value="${sessionScope.user.login}"/>
                    <label>${quantity}</label>
                    <input class="form-control" type="number" name="quantity" value="1"/><br/>
                    <input class="btn" type="submit" value="${addToBasket}"/><br/>
                </form>
            </c:if>
        <c:if test="${sessionScope.user==null}" var="isLogin">
            <form class="form-group" action="GoToSignIn" method="post">
                <input class="btn" type="submit" value="Buy"/><br/>
            </form>
        </c:if>
    </div>
</div>
<div class="modal" id="changeProduct">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>${changeProduct}</h3>
                <button type="button" class="close" data-dismiss="modal"> &times;</button>
            </div>
            <div class="modal-body">
                <form class="form-group" action="ChangeProduct" method="post">
                    <input type="hidden" name="Command" value="ChangeProduct"/>
                    <input type="hidden" name="productID" value="${product.id}"/>
                    <input type="hidden" name="productPathToPicture" value="${product.path}"/>
                    <input type="hidden" name="page" value=${pageContext.request.requestURL}/>
                    <label>${name}</label>
                    <input type="text" name="name" class="form-control" value="${product.name}"/><br/>
                    <label>${description}</label>
                    <input type="text" name="description" class="form-control" value="${product.description}"/><br/>
                    <div class="btn-group" data-toggle="buttons">
                            <c:if test="${product.forBoy()}">
                                <label class="btn">
                                    <input type="radio" name="category" value="Boy" autocomplete="off" checked>${boys}
                                </label>
                                <label class="btn">
                                    <input type="radio" name="category" value="Girl" autocomplete="off">${girls}
                                </label>
                            </c:if>

                        <c:if test="${!product.forBoy()}">
                            <label class="btn">
                                <input type="radio" name="category" value="Boy" autocomplete="off" >${boys}
                            </label>
                            <label class="btn">
                                <input type="radio" name="category" value="Girl" autocomplete="off" checked>${girls}
                            </label>
                        </c:if>
                    </div>
                    <label>${price}</label>
                    <input type="number" name="price" step="0.01" class="form-control" value="${product.price}"/><br/>
                    <label>${quantity}</label>
                    <input class="form-control" type="number" name="quantity" value="${product.quantity}"/><br/>
                    <select name="stock">
                        <jsp:useBean id="stocks" scope="session" type="java.util.List"/>
                        <c:forEach items="${stocks}" var="stock" varStatus="stockCount">
                            <option value="${stock.name}">${stock.name}</option>
                        </c:forEach>
                        <option value="no">No</option>
                    </select>
                    <input class="btn" type="submit"  value="${changeProduct}"/><br/>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
