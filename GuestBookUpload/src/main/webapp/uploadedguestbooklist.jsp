<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
h4 {color:red;}
.error {
	color: #ff0000;
}
</style>
<title>Uploaded Guest Book Display List</title>
</head>
<body>
<a href="./guestbookhome.app">Guest Book Home</a>
<hr>
<form:form id="displayuploadedForm" method="POST" action="./guestbookupload.app" commandName="guestbookuploadform">
<c:if test="${guestbookuploadform.uploadedGuestBookEntryList != null && fn:length(guestbookuploadform.uploadedGuestBookEntryList) > 0}">
<h2>Uploaded Guest Book List</h2><br/>
<div align="center"><h4><form:errors path="selectedGuestBookIds" cssClass="error" /></h4></div>

<input type="submit" name="merge" value="Merge"/>

<table border="1" style="width: 100%;">
		<thead align="center">
			<tr>
				
				<th>Select</th>
				<th>Title</th>
				<th>Name</th>
				<th>Text</th>
				<th>Email</th>
				<th>Keywords</th>
				<th>Creation Time</th>
				<th>Last Modified Time</th>
				
			</tr>
		</thead>
		<tbody align="center">
		
			<c:forEach items="${guestbookuploadform.uploadedGuestBookEntryList}" var="entry" varStatus="status">
				<c:if test="${status.first}"></c:if>
				<tr>
					<td><form:checkbox value="${entry.guestBookId}" path="selectedGuestBookIds" /></td>
					<td><c:out value="${entry.title}"></c:out></td>
					<td><c:out value="${entry.name}"></c:out></td>
					<td style="width:300px;">
					<div style="width:100%; height: 100px; overflow: auto;" align="center">
						<c:out value="${entry.text}"></c:out></div>
					
           			 </td>
           			<c:if test="${entry.email != null}">
           				<td><c:out value="${entry.email}"></c:out></td>
           			</c:if>
           			<c:if test="${entry.email == null}">
           				<td><c:out value="-"></c:out></td>
           			</c:if>
           			<td><ul>
           			<c:forEach items="${entry.keywordList}" var="keyword" varStatus="status">
           				<li><c:out value="${keyword}"></c:out></li>
           			</c:forEach>
           			</ul></td>
					<td><c:out value="${entry.createDate}"></c:out></td>
					<td><c:out value="${entry.lastModifiedDate}"></c:out></td>
				</tr>
				
				<c:if test="${status.last}"></c:if>
			</c:forEach>

		</tbody>
</table>
</c:if>
</form:form>
<c:if test="${guestbookuploadform.uploadedGuestBookEntryList == null || (guestbookuploadform.uploadedGuestBookEntryList != null && fn:length(guestbookuploadform.uploadedGuestBookEntryList) == 0)}">
<br/>
<h2>Guest Book Entries not uploaded yet</h2><br/>
</c:if>
</body>
</html>