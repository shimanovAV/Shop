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
    <link href="css/style.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="resource.text" var="locale"/>

    <fmt:message bundle="${locale}" key="error.message.not.accepted" var="error"/>
    <fmt:message bundle="${locale}" key="hint.unit" var="pc"/>
</head>
<body>
<table class="table" style="width: 100%">
    <thread style="width: 100%">
        <tr bgcolor="red">
            <th style="color: #f8f9fa">${error}</th>
        </tr>
    </thread>
    <tbody>
    <jsp:useBean id="notAccepted" scope="session" type="java.util.List"/>
        <tr bgcolor="#f5f5f5">
            <td>
                <div class="row wow fadeIn">
                    <c:forEach items="${notAccepted}" var="product" varStatus="productCount">

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
        </tr>
    </tbody>
</table>
</body>
</html>
