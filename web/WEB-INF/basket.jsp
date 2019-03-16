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
<button type="button" data-target="#MakeOrder"
        data-toggle="modal" class="dropbtn" data-dismiss="modal">
    <i class="fa fa-trash-o" style="color:#8d6e63"></i></button>
<section class="text-center md-4">
    <div class="row wow fadeIn">
        <jsp:useBean id="basket" scope="session" type="java.util.List"/>
        <c:forEach items="${basket}" var="product" varStatus="productCount">
        <div class="col-lg-3 col-md-6 mb-4">
            <div class="card">
                <div class="view overlay">
                        <button type="button" data-target="#DeleteFromBasket"
                                data-toggle="modal" class="dropbtn" data-dismiss="modal" title="${product.id}" name="${product.quantity}">
                            <i class="fa fa-trash-o" style="color:#8d6e63"></i></button>

                    <script type="text/javascript">
                        $(document).ready(function(){
                            $('.dropbtn').on('click', (function(){
                                var title  = $(this).attr('title')
                                var name = $(this).attr('name')
                                $('.productID').val(title);
                                $('.productQuantity').val(name);

                            }))
                        })
                    </script>
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
                    <form class="form-group" action="ChangeQuantity" method="post">
                        <input type="hidden" name="Command" value="ChangeQuantity"/>
                        <input type="hidden" name="page" value=${pageContext.request.requestURL}/>
                        <input type="hidden" name="productId" value="${product.id}"/>
                        <h4 class="font-weight-bold blue-text">
                            <strong>
                                <input class="form-control" type="number" name="quantity" value="${product.quantity}"/><br/>
                            </strong>
                        </h4>
                        <input class="btn" type="submit"  value="Save"/><br/>
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

<div class="modal" id="DeleteFromBasket">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>Do you want to delete the product?</h3>
                <button type="button" class="close" data-dismiss="modal"> &times;</button>
            </div>
            <div class="modal-body">
                <form class="form-group" action="DeleteFromBasket" method="post">
                    <input type="hidden" name="Command" value="DeleteFromBasket"/>
                    <input type="hidden" name="page" value=${pageContext.request.requestURL}/>
                    <input class="productID" type="hidden" name="productID" value=""/>
                    <input class="productQuantity" type="hidden" name="productQuantity" value=""/>
                    <input class="btn" type="submit"  value="Yes"/><br/>
                </form>
                <input class="btn"  type="submit" data-dismiss="modal" value="No"/><br/>
            </div>
        </div>
    </div>
</div>

<div class="modal" id="MakeOrder">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>Do you want to make an order?</h3>
                <button type="button" class="close" data-dismiss="modal"> &times;</button>
            </div>
            <div class="modal-body">
                <form class="form-group" action="MakeOrder" method="post">
                    <input type="hidden" name="Command" value="MakeOrder"/>
                    <input type="hidden" name="userId" value="${sessionScope.user.login}"/>
                    <input type="hidden" name="summ" value="${requestScope.summ}"/>
                    <div class="btn-group" data-toggle="buttons">
                        <label class="btn">
                            <input type="radio" name="courier" value="true" autocomplete="off" checked>Courier
                        </label>
                        <label class="btn">
                            <input type="radio" name="courier" value="false" autocomplete="off" >Pickup
                        </label>
                    </div>
                    <input class="btn" type="submit"  value="Yes"/><br/>
                </form>
                <input class="btn"  type="submit" data-dismiss="modal" value="No"/><br/>
            </div>
        </div>
    </div>
</div>

<nav class="navbar fixed-bottom navbar-light bg-light">
    <form class="form-inline">
        <button class="btn btn-outline-success" type="button">Save changes</button>
        <button class="btn btn-sm btn-outline-secondary" type="button">Smaller button</button>
    </form>
</nav>
</body>
</html>
