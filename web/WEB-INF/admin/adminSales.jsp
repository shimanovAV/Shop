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


    <fmt:message bundle="${locale}" key="hint.add.sale" var="addSale"/>
    <fmt:message bundle="${locale}" key="hint.sale.name" var="name"/>
    <fmt:message bundle="${locale}" key="hint.percent.size" var="percentSize"/>
    <fmt:message bundle="${locale}" key="hint.sale.term" var="term"/>
    <fmt:message bundle="${locale}" key="question.delete.sale" var="deleteSale"/>
    <fmt:message bundle="${locale}" key="button.yes" var="yes"/>
    <fmt:message bundle="${locale}" key="button.no" var="no"/>
</head>
<body style="background: #f8f9fa;">
<div>
    <jsp:include page="/Header" flush="true"/>
</div>
<button type="button" id="addButton" data-target="#addsale"
        data-toggle="modal">
    <i class="fa fa-plus fa-2x"></i>
</button>

<div class="modal" id="addsale">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>${addSale}</h3>
                <button type="button" class="close" data-dismiss="modal"> &times;</button>
            </div>
            <div class="modal-body">
                <form class="form-group" action="AddStock" method="post">
                    <input type="hidden" name="Command" value="AddStock"/>
                    <input type="hidden" name="page" value=${pageContext.request.requestURL}/>
                    <label>${name}</label>
                    <input type="text" name="name" class="form-control"/><br/>
                    <label>${percentSize}</label>
                    <input class="form-control" type="number" max="100" name="percentSize"/><br/>
                    <label>${term}</label>
                    <input type="date" name="expireDate" class="form-control"/><br/>
                    <i class="fa fa-birthday-cake fa-2x"style="color:#8d6e63"></i>
                    <input class="btn" type="submit"  value="${addSale}"/><br/>
                </form>
            </div>
        </div>
    </div>
</div>
<table class="table" style="margin:0 auto 0 0; background:#f8f9fa;">
    <thread>
        <tr bgcolor="#ffc0cb">
            <th>${name}</th>
            <th>${percentSize}</th>
            <th>${term}</th>
            <th></th>
        </tr>
    </thread>
    <tbody>
    <jsp:useBean id="stocks" scope="session" type="java.util.List"/>
        <c:forEach items="${stocks}" var="stock" varStatus="stockCount">
    <tr bgcolor="#f5f5f5">
            <td>${stock.name}</td>
            <td>${stock.percentSize}</td>
            <td>${stock.expireDate}</td>
            <td><button type="button" data-target="#deleteStock"
                        data-toggle="modal" class="dropbtn" data-dismiss="modal" title="${stock.id}" >
                <i class="fa fa-trash" style="color:#8d6e63"></i></button>
            </td>
    </tr>
        </c:forEach>
    </tbody>
</table>
<script type="text/javascript">
    $(document).ready(function(){
        $('.dropbtn').on('click', (function(){
            var title  = $(this).attr('title')
            $('.stockID').val(title);

        }))
    })
</script>
<div class="modal" id="deleteStock">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>${deleteSale}</h3>
                <button type="button" class="close" data-dismiss="modal"> &times;</button>
            </div>
            <div class="modal-body">
                <form class="form-group" action="DeleteStock" method="post">
                    <input type="hidden" name="Command" value="DeleteStock"/>
                    <input type="hidden" name="page" value=${pageContext.request.requestURL}/>
                    <input class="stockID" type="hidden" name="stockID" value=""/>
                    <input class="btn" type="submit"  value="${yes}"/><br/>
                </form>
                <input class="btn"  type="submit" data-dismiss="modal" value="${no}"/><br/>
            </div>
        </div>
    </div>
</div>

<script src="js/bootstrap.min.js"/>
</body>
</html>
