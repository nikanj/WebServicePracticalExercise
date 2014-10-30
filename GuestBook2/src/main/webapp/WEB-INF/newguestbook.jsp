<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NewGuestBook</title>
</head>
<body>
	<form:form method="POST" action="./newguestbook.app"
		commandName="newguestbookform">
		<h3>Enter Guest Book Details</h3>
		<table>
		<tr>
		<td>Title:</td>
		<td> <form:input path="data.title" /></td>
		<td><form:errors path="data.title" cssClass="error" /></td>
		</tr>
		<tr>
		<td>Name:</td>
		<td> <form:input path="data.name" /></td>
		<td><form:errors path="data.name" cssClass="error" /></td>
		<tr>
		<td>Text:</td>
		<td> <form:textarea path="data.text" /></td>
		<td><form:errors path="data.text" cssClass="error" /></td>
		</tr>
		<tr><form:hidden path="data.guestBookId" /></tr>
		<tr>
		<td><input type="submit" name="submit" value="Save/Update" /></td>
		<td><input type="reset" value="Clear Form" /></td>
		</tr>
		</table>
	</form:form>
</body>
</html>