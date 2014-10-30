<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c2" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HomePage</title>
</head>
<body>
	<a href="./newguestbook.app">Add an entry</a>
	<form:form method="POST" action="./home.app" commandName="homeform">
		<c:if
			test="${homeform.guestBookList != null && fn:length(homeform.guestBookList) > 0}">
			<hr>
			<h2>Data In The Guest Book</h2>
			<br />
			<input type="submit" name="sort" value="Rearrange according to dates" />
			<table border="1" style="width: 100%;">
				<thead align="center">
					<tr>
						<th>Title</th>
						<th>Name</th>
						<th>Text</th>
						<th>Latest Access Time</th>
						<th>Options</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${homeform.guestBookList}" var="details"
						varStatus="flag">
						<c:if test="${flag.first}"></c:if>
						<tr>
							<td><c:out value="${details.title}"></c:out></td>
							<td><c:out value="${details.name}"></c:out></td>
							<td style="width: 500px;">
								<div style="width: 100%; height: 100px; overflow: auto;" align="center">
									<c:out value="${details.text}"></c:out>
								</div>

							</td>
							<td><c:out value="${details.createDate}"></c:out></td>
							<td><a href="./newguestbook.app?edit=${details.guestBookId}">edit</a></br>

								<a href="./home.app?delete=${details.guestBookId}">delete</a></td>
						</tr>

						<c:if test="${flag.last}"></c:if>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if
			test="${homeform.guestBookList == null || (homeform.guestBookList != null && fn:length(homeform.guestBookList) == 0)}">
			<hr>
			<br />
			<h2>No Data In The Guest Book</h2>
			<br />
		</c:if>
		<hr>
	</form:form>
</body>
</html>