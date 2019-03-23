<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/mainpage.css" rel="stylesheet">


    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="resource.text" var="locale"/>

    <fmt:message bundle="${locale}" key="label.menu.new" var="New"/>
    <fmt:message bundle="${locale}" key="label.total.cost" var="cost"/>
    <fmt:message bundle="${locale}" key="button.buy" var="buy"/>
    <fmt:message bundle="${locale}" key="question.make.order" var="makeOrder"/>
    <fmt:message bundle="${locale}" key="hint.delivery.method.courier" var="courier"/>
    <fmt:message bundle="${locale}" key="hint.delivery.method.pickup" var="pickup"/>
    <fmt:message bundle="${locale}" key="question.delete" var="delete"/>
    <fmt:message bundle="${locale}" key="button.no" var="no"/>
    <fmt:message bundle="${locale}" key="button.yes" var="yes"/>
    <fmt:message bundle="${locale}" key="button.save" var="save"/>
</head>
<body>
<section class="text-center md-4">
    <div class="row wow fadeIn">
        <jsp:useBean id="basket" scope="session" type="java.util.List"/>
        <c:forEach items="${basket}" var="product" varStatus="productCount">
        <div class="col-lg-3 col-md-6 mb-4">
            <div class="card">
                <div class="view overlay">
                    <c:if test="${sessionScope.user!=null&&!(sessionScope.user.isAccessLevel())}" var="isLogin">
                        <button type="button" data-target="#DeleteFromBasket"
                                data-toggle="modal" class="dropbtn" data-dismiss="modal" title="${product.id}"
                                name="${product.quantity}">
                            <i class="fa fa-trash-o" style="color:#8d6e63"></i></button>
                    </c:if>

                    <script type="text/javascript">
                        $(document).ready(function () {
                            $('.dropbtn').on('click', (function () {
                                var title = $(this).attr('title')
                                var name = $(this).attr('name')
                                $('.productID').val(title);
                                $('.productQuantity').val(name);
                            }))
                        })
                    </script>
                    <a href="FindProduct?Command=FINDPRODUCTBYID&productID=${product.id}">
                        <img class="photo" src="${product.path}"></a>
                </div>
                <div class="card-body text-center">
                    <c:if test="${product.isNew()}" var="isLogin">
                        <span class="badge badge-pill text-danger">${New}</span>
                    </c:if>
                    <h5>
                        <strong>
                            <a href="FindProduct?Command=FINDPRODUCTBYID&productID=${product.id}"
                               class="dark-grey-text">${product.name}</a>
                        </strong>
                    </h5>
                    <h4 class="font-weight-bold blue-text">
                        <strong>
                                ${product.price}
                        </strong>
                    </h4>
                    <c:if test="${product.isOnSale()}" var="isLogin">
                        <span class="badge badge-pill text-success">${product.stockName}</span>
                    </c:if>
                    <form class="d-flex justify-content" action="ChangeQuantity" method="post">
                        <input type="hidden" name="Command" value="ChangeQuantity"/>
                        <input type="hidden" name="page" value=${pageContext.request.requestURL}/>
                        <input type="hidden" name="productId" value="${product.id}"/>
                        <input class="form-control" type="number" name="quantity"
                               style="width: 100px; margin-right: 7px;" value="${product.quantity}"/>
                        <button class="btn" type="submit" style="height: 40px;">${save}</button>
                        <br/>
                    </form>
                </div>
            </div>
        </div>
        <c:if test="${productCount.count % 4 == 0}">
    </div>
    <div class="row wow fadeIn">

        </c:if>
        </c:forEach>
</section>
<jsp:useBean id="summ" scope="session" type="java.lang.Double"/>
<div id="footer">
    <hr/>
    <h1 style="text-align: center;">
        ${cost} ${summ}
        <button type="button" data-target="#MakeOrder"
                data-toggle="modal" class="dropbtn btn" data-dismiss="modal" style="width: 100px; height: 50px;">
            ${buy} <i class="fas fa-money-check-alt" style="color:#8d6e63"></i>
        </button>
    </h1>
</div>
<div class="modal" id="MakeOrder">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>${makeOrder}</h3>
                <button type="button" class="close" data-dismiss="modal"> &times;</button>
            </div>
            <div class="modal-body">
                <form class="form-group" action="MakeOrder" method="post">
                    <input type="hidden" name="Command" value="MakeOrder"/>
                    <input type="hidden" name="userId" value="${sessionScope.user.login}"/>
                    <input type="hidden" name="summ" value="${summ}"/>
                    <div class="btn-group" data-toggle="buttons">
                        <label class="btn">
                            <input type="radio" name="courier" value="true" checked>${courier}
                        </label>
                        <label class="btn">
                            <input type="radio" name="courier" value="false">${pickup}
                        </label>
                    </div>
                    <br/>
                    <input class="btn" type="submit" value="${yes}"/><br/>
                </form>
                <input class="btn" type="submit" data-dismiss="modal" value="${no}"/><br/>
            </div>
        </div>
    </div>
</div>


<div class="modal" id="DeleteFromBasket">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>${delete}</h3>
                <button type="button" class="close" data-dismiss="modal"> &times;</button>
            </div>
            <div class="modal-body">
                <form class="form-group" action="DeleteFromBasket" method="post">
                    <input type="hidden" name="Command" value="DeleteFromBasket"/>
                    <input type="hidden" name="page" value=${pageContext.request.requestURL}/>
                    <input class="productID" type="hidden" name="productID" value=""/>
                    <input class="productQuantity" type="hidden" name="productQuantity" value=""/>
                    <input class="btn" type="submit" value="${yes}"/><br/>
                </form>
                <input class="btn" type="submit" data-dismiss="modal" value="${no}"/><br/>
            </div>
        </div>
    </div>
</div>
</body>
</html>


