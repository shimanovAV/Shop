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



</head>
<body>
<div>
    <c:if test="${sessionScope.user==null||!(sessionScope.user.isAccessLevel())}">
    <jsp:include page = "header.jsp" flush="true"/>
    </c:if>
    <c:if test="${sessionScope.user!=null&&(sessionScope.user.isAccessLevel())}">
        <jsp:include page = "/AdminHeader" flush="true"/>
    </c:if>
</div>
<div class="container">


    <section class="text-center md-4">
        <div class="row wow fadeIn">
            <jsp:useBean id="findCatalog" scope="session" type="java.util.List"/>
            <c:forEach items="${findCatalog}" var="product" varStatus="productCount">
            <div class="col-lg-3 col-md-6 mb-4">
                <div class="card">
                    <div class="view overlay">
                        <c:if test="${sessionScope.user!=null$$sessionScope.user.isAccessLevel()}" var="isLogin">
                            <button type="button" data-target="#deleteProduct"
                                    data-toggle="modal" class="dropbtn" data-dismiss="modal" title="${product.id}" >
                                <i class="fa fa-trash-o" style="color:#8d6e63"></i></button>
                        </c:if>

                        <script type="text/javascript">
                            $(document).ready(function(){
                                $('.dropbtn').on('click', (function(){
                                    var title  = $(this).attr('title')
                                    $('.productID').val(title);

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
                    </div>
                </div>
            </div>
            <c:if test="${productCount.count % 4 == 0}">
        </div>
        <div class="row wow fadeIn">

            </c:if>
            </c:forEach>
    </section>
    <div class="modal" id="deleteProduct">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3>Do you want to delete the product?</h3>
                    <button type="button" class="close" data-dismiss="modal"> &times;</button>
                </div>
                <div class="modal-body">
                    <form class="form-group" action="DeleteProduct" method="post">
                        <input type="hidden" name="Command" value="DeleteProduct"/>
                        <input type="hidden" name="page" value=${pageContext.request.requestURL}/>
                        <input class="productID" type="hidden" name="productID" value=""/>
                        <input class="btn" type="submit"  value="Yes"/><br/>
                    </form>
                    <input class="btn"  type="submit" data-dismiss="modal" value="No"/><br/>
                </div>
            </div>
        </div>
    </div>
</div>


<a href="#" class="btn btn-danger" data-toggle="button" aria-pressed="false" role="button">
    Кнопка-переключатель
</a>
</div>



<script src="js/bootstrap.min.js"/>
</body>
</html>