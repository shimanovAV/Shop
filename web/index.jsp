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
  <jsp:include page = "header.jsp" flush="true"/>
</div>
<div class="container">
  <jsp:include page="/allProduct.jsp"/>
</div>


<a href="#" class="btn btn-danger" data-toggle="button" aria-pressed="false" role="button">
  Кнопка-переключатель
</a>
</div>



<script src="js/bootstrap.min.js"/>
</body>
</html>