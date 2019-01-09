<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 04.12.2018
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="Registration" method="post">
    <input type="hidden" name="Command" value="Registration"/>
    Enter Name:<br/>
    <input type="text" name="name" value=""/><br/>
    Enter Description:<br/>
    <input type="text" name="email" value=""/><br/>
    Enter Category:<br/>
    <div class="btn-group" data-toggle="buttons">
        <label class="btn btn-primary active">
            <input type="radio" name="category" value="Book" autocomplete="off" checked>Girls
        </label>
        <label class="btn btn-primary">
            <input type="radio" name="category" value="Magazine" autocomplete="off" >Boys
        </label>
    </div>
    Enter price:<br/>
    <input class="tbox" type="number" placeholder="Price" name="price" step="0.01"/><br/>
    <input class="tbox" type="number" placeholder="Quantity" name="quantity"/><br/>
    <input type="file" name="getFile" accept="image/*">
    <input type="password" name="password" value=""/><br/>
    <input type="submit"  value="Registration"/><br/>
</form>
</body>
</html>
