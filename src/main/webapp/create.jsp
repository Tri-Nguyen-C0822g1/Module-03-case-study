<%--
  Created by IntelliJ IDEA.
  User: tring
  Date: 12/6/2022
  Time: 8:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create New Product</title>
</head>
<body>
<h2>
  <a href="/products?action=home">Back to home</a>
</h2>

<form action="/products?action=add" method="post">
  <table border="1" cellpadding="5">
    <caption>
      <h2>Create New Product</h2>
    </caption>
<%--    <tr>--%>
<%--      <th>Product ID:</th>--%>
<%--      <td>--%>
<%--        <input type="number" name="id" id="id" size="30"/>--%>
<%--      </td>--%>
<%--    </tr>--%>
    <tr>
      <th>Product Name:</th>
      <td>
        <input type="text" name="name" id="name" size="30"/>
      </td>
    </tr>
    <tr>
      <th>Product Description:</th>
      <td>
        <input type="text" name="desc" id="desc" size="30"/>
      </td>
    </tr>
    <tr>
      <th>Price</th>
      <td>
        <input type="number" name="price" id="price" size="30"/>
      </td>
    </tr>
    <tr>
      <th>Image</th>
      <td>
        <input type="text" name="image" id="image" size="30"/>
      </td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <input type="submit" value="Save"/>
      </td>
    </tr>
  </table>
</form>
</div>
</body>
</html>
