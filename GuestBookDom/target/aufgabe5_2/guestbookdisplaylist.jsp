<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display List</title>
</head>
<body>
<c:if test="${guestbookhomeform.guestBookEntryList != null && fn:length(guestbookhomeform.guestBookEntryList) > 0}">
<h2>Guest Book Entry List</h2><br/>

<input type="submit" name="sortByDate" value="Sort By Date"/>
<input type="submit" name="viewall" value="View All"/>
<table border="1" style="width: 100%;">
		<thead align="center">
			<tr>
				
				<!--<th>Select</th>-->
				<th>Title</th>
				<th>Name</th>
				<th>Text</th>
				<th>Email</th>
				<th>Keywords</th>
				<th>Creation Time</th>
				<th>Last Modified Time</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody align="center">
		
			<c:forEach items="${guestbookhomeform.guestBookEntryList}" var="entry" varStatus="status">
				<c:if test="${status.first}"></c:if>
				<tr>
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
					<td>
						<div id="span1"><a href="./createguestbook.app?edit=${entry.guestBookId}">edit</a></div>
						<div id="span2"><a href="./guestbookhome.app?delete=${entry.guestBookId}">delete</a></div>
					</td>
				</tr>
				
				<c:if test="${status.last}"></c:if>
			</c:forEach>

		</tbody>
</table>
</c:if>
<c:if test="${guestbookhomeform.guestBookEntryList == null || (guestbookhomeform.guestBookEntryList != null && fn:length(guestbookhomeform.guestBookEntryList) == 0)}">
<br/>
<h2>Guest Book Entries not found</h2><br/>
</c:if>
</body>
</html>