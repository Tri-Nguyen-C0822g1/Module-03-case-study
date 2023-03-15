<%--
  Created by IntelliJ IDEA.
  User: tring
  Date: 12/6/2022
  Time: 1:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register</title>
</head>
<body>
  <h1>Register Page</h1>
  <form action="LoginServlet" method="post">
    Username <input type="text" name="txtUser" value=""/><br/>
    Password <input type="password" name="txtPass"value=""/><br/>
    Lastname <input type="text" name="txtLast" value=""/><br/>
    Admin <input type="checkbox" name="chkAdmin" value="ADMIN"/><br/>
    <input type="submit" value="Register" name="btAction"/>
  </form>
</body>
</html>
