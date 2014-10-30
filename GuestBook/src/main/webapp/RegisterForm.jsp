<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<h1>Enter credentials to register as a new user</h1>
<form action="./register" method="POST">
FirstName: <input type="text" name="Firstname"/><br/>
LastName: <input type="text" name="Lastname"/><br/>
UserName: <input type="text" name="username"/><br/>
Password: <input type="password" name="password"/>
<input type="submit" value="submit"/>
</form>
</body>
</html>