<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ReverseResult</title>
</head>
<body>
	Reverse of string:
	<b><c:out value="${reverseobj.text}"></c:out></b> ::
	<b><c:out value="${reverseobj.reverseText}"></c:out></b>
	<br />
	<a href="./reverse.app">Go Back</a>
</body>
</html>