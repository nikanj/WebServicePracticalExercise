<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="JavaScript" src="js/validation.js"></script>
<title>GuestBook</title>
</head>
<body>
<form action="./createbookentry" name="createbookentryform" method="post">
	<table border="1" >
		<thead align="center">
			<tr>
				<th>Heading 1</th>
				<th>Heading 2</th>
			</tr>
		</thead>
		<tbody align="center">
			<tr>
				<td>row 1,col 1</td>
				<td>row 1,col 2</td>
			</tr>
			<tr>
				<td>row 2,col 1</td>
				<td>row 2,col 2</td>
			</tr>
		</tbody>
	</table>
<table>
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
<td><input type="submit" value="Create Book Entry"><input type="reset"></td>
</tr>
</table>

</form>
</body>
</html>