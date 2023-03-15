<%--
  Created by IntelliJ IDEA.
  User: tring
  Date: 12/7/2022
  Time: 11:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>List Product</title>

  <!-- CSS only -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  <style>
    body {
      margin: 0;
      font-family: Arial, Helvetica, sans-serif;
      /*position: absolute;*/
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
  </div>
</head>
<body>

<br/>
<br/>
<br/>
<br/>
<h1 style=" color : red;size: A3; text-align: center">THE GIOI DI DONG - fake</h1>


<div align="center">
  <caption><h2>Cart</h2></caption>
  <table class="table table-striped">

    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Description</th>
      <th>Price</th>
      <th>Image</th>
      <td>Action</td>
    </tr>
    <c:forEach items='${products}' var="product">
      <tr>
        <td>${product.getId()}</td>
        <td>${product.getName()}</td>
        <td>${product.getDescription()}</td>
        <td>$${product.getPrice()}</td>
        <td><img src="${product.getImage()}" width=100; height="80">}</td>
        <td>
          <a href="/products?action=delete&id=${product.getId()}">Delete</a>
        </td>
      </tr>
    </c:forEach>

  </table>
</div>
</body>
</html>