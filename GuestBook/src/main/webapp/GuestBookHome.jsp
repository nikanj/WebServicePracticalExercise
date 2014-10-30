<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c2" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
h4 {color:red;}
</style>
<title>GuestBookHome</title>
</head>
<body>
<c:if test="${userloggedin}">
<jsp:include page="/Welcome.jsp"></jsp:include>
<hr>
</c:if>

<jsp:include page="/CreateGuestBook.jsp"></jsp:include>
<h4><c:out value="${errorMessage1}"></c:out></h4>
<h4><c:out value="${errorMessage2}"></c:out></h4>

<form action="./guestBookhome" method="post">

<c:if test="${guestBookEntries.guestBookEntryList != null && fn:length(guestBookEntries.guestBookEntryList) > 0}">
<hr>
<h2>Guest Book Entry List</h2><br/>
<c:choose>
	<c:when test="${guestBookEntries.currentStartIndex+1 != 1}">
			<input type="submit" name="backward" value="<<">
	</c:when> 
	<c:otherwise>
			<input type="submit" disabled="disabled" name="backward" value="<<">
	</c:otherwise>
</c:choose>
Displaying: <c:out value="${currentStartIndex}"></c:out> - <c:out value="${currentEndIndex}"></c:out> items
<input type="submit" name="sortByDate" value="Sort By Date"/>
<c:if test="${userloggedin}">
<input type="submit" name="delete" value="Delete">
</c:if>
<table border="1" >
		<thead align="center">
			<tr>
				<c:if test="${userloggedin}">
					<th>Select</th>
				</c:if>
				<th>Book Name</th>
				<th>Create Date</th>
			</tr>
		</thead>
		<tbody align="center">
		<c:forEach items="${guestBookEntries.currentDisplayList}" var="entry" varStatus="status">
			<c:if test="${status.first}"></c:if>
			<tr>
				<c:if test="${userloggedin}">
				
					<td><input type="checkbox" name="bookid_${entry.itemName}" value="${entry.itemName}"></td>
				</c:if>
				<td><c:out value="${entry.itemName}"></c:out></td>
				<td><c:out value="${entry.createDate}"></c:out></td>
			</tr>
			
			<c:if test="${status.last}"></c:if>
		</c:forEach>
		</tbody>
</table>
<c:choose>
	<c:when test="${guestBookEntries.currentEndIndex < fn:length(guestBookEntries.guestBookEntryList)}">
		<input type="submit" name="forward" value=">>">
	</c:when> 
	<c:otherwise>
		<input type="submit" disabled="disabled" name="forward" value=">>">
	</c:otherwise>
</c:choose>
</c:if>
<c:if test="${userloggedin}">
click here to logout: <a href="./logout">link</a>
</c:if>

</form>
</body>
</html>