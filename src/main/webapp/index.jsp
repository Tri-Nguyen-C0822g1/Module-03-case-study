<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
<body>
<h1>Login Page</h1>

<form action="/login" method="post">
    Username <input type="text" name="txtUsername" value=""/><br/>
    Password <input type="password" name="txtPass" value=""/><br/>
    <input type="submit" value="Login" name="btAction" />
    <input type="reset" value="Reset"/>
</form>
</body>
</html>