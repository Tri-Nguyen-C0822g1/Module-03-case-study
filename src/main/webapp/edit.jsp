<%--
  Created by IntelliJ IDEA.
  User: tring
  Date: 12/7/2022
  Time: 4:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Product Management Application</title>
</head>
<body>
<center>
  <h1>Products Management</h1>
  <h2>
    <a href="/products?action=init">List All Product</a>
  </h2>
</center>
<div align="center">
  <form method="post">
    <table border="1" cellpadding="5">
      <caption>
        <h2>
          Edit Product
        </h2>
      </caption>
      <c:if test='${product != null}'>
        <input type="hidden" name="id" value="<c:out value='${product.id}' />"/>
      </c:if>
      <tr>
        <th>Product Name:</th>
        <td>
          <input type="text" name="name" size="45"
                 value="<c:out value='${product.name}' />"
          />
        </td>
      </tr>
      <tr>
        <th>Description:</th>
        <td>
          <input type="text" name="desc" size="45"
                 value="<c:out value='${product.description}'/>"
          />
        </td>
      </tr>
      <tr>
        <th>Price:</th>
        <td>
          <input type="text" name="price" size="15"
                 value="<c:out value='${product.price}' />"
          />
        </td>
      </tr>
      <tr>
        <th>Image:</th>
        <td>
          <input type="text" name="image" size="15"
                 value="<c:out value='${product.image}' />"
          />
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
