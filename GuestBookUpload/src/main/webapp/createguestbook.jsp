<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
h4 {color:red;}
.error {
	color: #ff0000;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>CreateGuestBook</title>
</head>
<body>
<form:form method="POST" action="./createguestbook.app" commandName="guestbookcreateform">
<table class="table table-bordered">
	<tr>
	<td></td>
	<td><b>Save/Update Guest Book Entry</b></td>
	</tr>
	
	<tr>
	<td>Title: </td>	
	<td><form:input path="title" /></td>
	<td><form:errors path="title" cssClass="error" /></td>
	</tr>
	
	<tr>
	<td>Name: </td>	
	<td><form:input path="name" /></td>
	<td><form:errors path="name" cssClass="error" /></td>
	</tr>
	
	<tr>
	<td>Text: </td>	
	<td><form:textarea path="text" /></td>
	<td><form:errors path="text" cssClass="error" /></td>
	</tr>
	
	<tr>
	<td>Email: </td>	
	<td><form:input path="email" /></td>
	<td><form:errors path="email" cssClass="error" /></td>
	</tr>
	
	<tr>
	<td>Keywords<br/>(Separated with comma): </td>	
	<td><form:textarea path="keywords" /></td>
	<td><form:errors path="keywords" cssClass="error" /></td>
	</tr>
	
	<tr>
	<td><form:hidden path="guestBookId" /></td>
	<td>
		<input type="submit" name="createorupdatebookentry" value="Save/Update"/>
		<input  type="submit" name="clearedit" value="Clear Form"/>
		<input type="submit" name="cancelcreate" value="Cancel"/>
	</td>
	</tr>
</table>
</form:form>
</body>
</html>