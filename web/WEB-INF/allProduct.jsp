<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<section class="text-center md-4">
    <div class="row wow fadeIn">
    <jsp:useBean id="catalog" scope="session" type="java.util.List"/>
    <c:forEach items="${catalog}" var="product" varStatus="productCount">
            <div class="col-lg-3 col-md-6 mb-4">
                <div class="card">
                    <div class="view overlay">
                        <img class="card-img-top" src="${product.path}">
                        <a href="">
                            <div class="mask rgba-white-slight"></div>
                        </a>
                    </div>
                    <div class="card-body text-center">
                        <h5>
                        <strong>
                            <a href="#" class="dark-grey-text">${product.name}</a>
                        </strong>
                        </h5>
                        <h4 class="font-weight-bold blue-text">
                            <strong>
                                ${product.price}
                            </strong>
                        </h4>
                    </div>
                </div>
            </div>
            <c:if test="${productCount.count % 4 == 0}">
                </div>
    <div class="row wow fadeIn">

            </c:if>
    </c:forEach>
</section>
</body>
</html>
