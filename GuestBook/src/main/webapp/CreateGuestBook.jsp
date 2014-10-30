<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>CreateGuestBook</title>
</head>
<body>
<form action="./createbookentry" name="createbookentryform" method="post">
<table>
<tr>
<td></td>
<td><b>Create Guest Book Entry</b></td>
</tr>
<tr>
<td>Item Name: </td>	
<td><input maxlength="25" name="itemname" id="itemnameid" type="text"/></td>
</tr>
<tr>
<td>Item Text: </td>	
<td><textarea cols="30" rows="10" name="itemtext"  id="itemtextid"></textarea></td>
</tr>
<tr>
<td></td>	
<td><input type="submit" name="createbookentry" value="Create Book Entry"><input type="reset"></td>
</tr>
</table>
</form>
</body>
</html>