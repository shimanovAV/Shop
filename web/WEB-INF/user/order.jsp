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

    <fmt:message bundle="${locale}" key="hint.number.order" var="numberOrder"/>
    <fmt:message bundle="${locale}" key="hint.time.order" var="time"/>
    <fmt:message bundle="${locale}" key="hint.products" var="prodStaf"/>
    <fmt:message bundle="${locale}" key="hint.unit" var="pc"/>
    <fmt:message bundle="${locale}" key="button.cancel" var="cancel"/>
</head>
<body>
<table class="table" style="width: 100%">
    <thread style="width: 100%;">
        <tr bgcolor="#ffc0cb" style="text-align: center;">
            <th>${numberOrder}</th>
            <th>${time}</th>
            <th>${prodStaf}</th>
            <th></th>
        </tr>
    </thread>
    <tbody>
    <jsp:useBean id="orders" scope="session" type="java.util.Map"/>
    <c:forEach items="${orders}" var="order" varStatus="orderCount">
        <tr bgcolor="#f5f5f5">
            <td>${order.getKey().getId()}</td>
            <td>${order.getKey().getOrderDate()}</td>
            <td>
                <div class="row wow fadeIn">
                    <c:forEach items="${order.getValue()}" var="product" varStatus="productCount">

                    <div class="small-card">
                        <a href="FindProduct?Command=FINDPRODUCTBYID&productID=${product.id}">
                            <img class="icon-photo" src="${product.path}"></a>

                        <div class="card-body text-center" style="margin-right: 0%;">
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
                            <h4 class="font-weight-bold blue-text">
                                <strong>
                                        ${product.quantity} ${pc}
                                </strong>
                            </h4>
                        </div>
                    </div>
                    <c:if test="${productCount.count % 4 == 0}">
                </div>
                <div class="row wow fadeIn">
                    </c:if>
                    </c:forEach>
            </td>
            <td>
                <form class="form-group" action="CancelOrder" method="post">
                    <input type="hidden" name="Command" value="CancelOrder"/>
                    <input type="hidden" name="page" value=${pageContext.request.requestURL}/>
                    <input type="hidden" name="orderId" value="${order.getKey().getId()}"/>
                    <input class="btn" type="submit" value="${cancel}"/><br/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
