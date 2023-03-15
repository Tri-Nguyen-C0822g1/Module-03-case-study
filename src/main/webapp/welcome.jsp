<%--
  Created by IntelliJ IDEA.
  User: tring
  Date: 12/5/2022
  Time: 11:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" ; charset="UTF-8">
    <title>Home page</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
            position: absolute;
        }

        .topnav {
            width: 100%;
            overflow: hidden;
            background-color: #333;
            position: fixed;
        }

        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        .topnav a.active {
            background-color: #4CAF50;
            color: white;
        }
        .row{

        }

    </style>
    <div class="topnav" style="padding-right: 10px">
        <a href="/products?action=home" >Trang chủ</a>
        <a href="/products?action=out">Log out</a>
        <a href="/products?action=showCart">Cart</a>

    </div>
</head>
<body>

<font color="red">Welcome, ${sessionScope.USER}</font>

<br/>
<br/>
<br/>
<br/>
<h1 style=" color : red;size: A3; text-align: center">THE GIOI DI DONG - fake</h1>
<form action="/products" method="get">
<input type="text" name="search" placeholder="Search">
<button type="submit" value="search" name="action">Search</button>
</form>
    <div class="row">
        <c:forEach var="products" items="${products}">
        <div class="col-3 mx-auto">
            <img src="${products.getImage()}" width=250; height="200">
            <div class="proName" >${products.getName()}</div>
            <div class="desc">${products.getDescription()}</div>
            <div class="price">$${products.getPrice()}</div>
            <a class="btn btn-primary" href="/products?action=cart&id=${products.getId()}" methods="post">Add to cart</a>
        </div>
        </c:forEach>
    </div>

</body>
</html>
