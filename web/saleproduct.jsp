<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
</head>
<body>
<div>
    <jsp:include page = "/Header" flush="true"/>
</div>
<div class="container">
    <jsp:useBean id="saleProducts" scope="session" type="java.util.List"/>
    <c:set var="all" value="${saleProducts}" scope="session"/>
    <jsp:include page = "allProduct.jsp" flush="true"/>
</div>
</body>
</html>
