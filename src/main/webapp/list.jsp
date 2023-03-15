<%--
  Created by IntelliJ IDEA.
  User: tring
  Date: 12/7/2022
  Time: 12:43 AM
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
    </style>
    <div class="topnav" style="padding-right: 10px">
        <a href="/products?action=init" >Update</a>
        <a href="/products?action=out">Log out</a>
        <a href="/products?action=create">Add new product</a>
    </div>
</head>
<body>
<br/><br/><br/><br/>
<h1 style="text-align: center" >Product Management</h1>

<form style="padding-top: 20px" action="" method="get">
    <table >
        <tr>
            <td>
                <input type="text" name="search" placeholder="Search..">
            </td>
            <td>
                <button type="submit" value="search">Search</button>
            </td>
        </tr>
    </table>
</form>

<div align="center">
    <caption><h2>List of Products</h2></caption>
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
                <td>${product.getPrice()}</td>
                <td><img src="${product.getImage()}" width=100; height="80"></td>
                <td>
                    <a class="btn btn-lg btn-danger" href="/products?action=edit&id=${product.getId()}">Edit</a>
                    <a class="btn btn-lg btn-outline-danger" href="/products?action=delete&id=${product.getId()}">Delete</a>
                </td>
            </tr>
        </c:forEach>

    </table>
    </div>
</body>
</html>
