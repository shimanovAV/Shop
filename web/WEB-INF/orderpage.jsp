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

               <table class="table" style="margin:0 auto 0 0">
            <thread>
                <tr bgcolor="#ffc0cb">
                    <th>Номер заказа</th>
                    <th>Время заказа</th>
                    <th>Товары</th>
                    <th></th>
                </tr>
            </thread>
            <tbody>
            <jsp:useBean id="orders" scope="session" type="java.util.Map"/>
            <c:forEach items="${orders}" var="order" varStatus="orderCount">
                <tr bgcolor="#f5f5f5">
                    <td>${order.getKey().getId()}</td>
                    <td>${order.getKey().getOrderDate()}</td>
                    <c:forEach items="${order.getValue()}" var="product">
                    <td> <div class="view overlay">
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
                                </strong></br>
                                    ${product.quantity}
                            </h4>
                        </div>
                        </div>
                        </div>
                        <div class="row wow fadeIn">
                            </c:forEach>
                        </div>
                    </td>
                    <td><form class="form-group" action="CancelOrder" method="post">
                        <input type="hidden" name="Command" value="CancelOrder"/>
                        <input type="hidden" name="page" value=${pageContext.request.requestURL}/>
                        <input type="hidden" name="orderId" value="${order.getKey().getId()}"/>
                        <input class="btn" type="submit"  value="Cancel"/><br/>
                    </form>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>

</body>
</html>
