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
    <fmt:setBundle basename="resources.text" var="locale"/>


    <fmt:message bundle="${locale}" key="hint.add.product" var="addProduct"/>
    <fmt:message bundle="${locale}" key="hint.name" var="name"/>
    <fmt:message bundle="${locale}" key="hint.description" var="description"/>
    <fmt:message bundle="${locale}" key="hint.category.boys" var="boys"/>
    <fmt:message bundle="${locale}" key="hint.category.girls" var="girls"/>
    <fmt:message bundle="${locale}" key="hint.price" var="price"/>
    <fmt:message bundle="${locale}" key="hint.quantity" var="quantity"/>
    <fmt:message bundle="${locale}" key="hint.set.stock" var="setStock"/>
    <fmt:message bundle="${locale}" key="hint.image" var="image"/>
</head>
<body>

<div>
    <jsp:include page = "/WEB-INF/adminHeader.jsp" flush="true"/>
</div>

<a class="text-right" data-target="#addproduct"
   data-toggle="modal">${addProduct}</a>


<div class="modal" id="addproduct">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>${addProduct}</h3>
                <button type="button" class="close" data-dismiss="modal"> &times;</button>
            </div>
            <div class="modal-body">
                <form class="form-group" action="AddProduct" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="Command" value="AddProduct"/>
                    <input type="hidden" name="page" value=${pageContext.request.requestURL}/>
                    <label>${name}</label>
                    <input type="text" name="name" class="form-control"/><br/>
                    <label>${description}</label>
                    <input type="text" name="description" class="form-control"/><br/>
                    <div class="btn-group" data-toggle="buttons">
                        <label class="btn">
                            <input type="radio" name="category" value="Boy" autocomplete="off" checked>${boys}
                        </label>
                        <label class="btn">
                            <input type="radio" name="category" value="Girl" autocomplete="off" >${girls}
                        </label>
                    </div>
                    <label>${price}</label>
                    <input type="number" name="price" step="0.01" class="form-control"/><br/>
                    <label>${quantity}</label>
                    <input class="form-control" type="number" name="quantity"/><br/>
                    <label>${image}</label>
                    <input class="form-control" type="file" name="getFile" accept="image/*">
                    <input class="btn" type="submit"  value="${addProduct}"/><br/>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <jsp:include page="/allProduct.jsp"/>
</div>

<script src="js/bootstrap.min.js"/>
</body>
</html>
