<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<html>
<body>
	<H1>Enter credentials for login</H1>
	<form action="login" method="POST">
		<input type="text" name="username" />
		<input type="password" name="password" /><br />
		 <input type="submit" value="submit" />
	</form>
	Click on the link to register: <a href="http:register.jsp">link</a>
</body>
</html>