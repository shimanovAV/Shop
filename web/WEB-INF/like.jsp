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
</head>
<body>
<section class="text-center md-4">
    <div class="row wow fadeIn">
        <jsp:useBean id="likes" scope="request" type="java.util.List"/>
        <c:forEach items="${likes}" var="product" varStatus="productCount">
        <div class="col-lg-3 col-md-6 mb-4">
            <div class="card">
                <div class="view overlay">
                    <a href="FindProduct?Command=FINDPRODUCTBYID&productID=${product.id}">
                        <img class="card-img-top" src="${product.path}"></a>
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
