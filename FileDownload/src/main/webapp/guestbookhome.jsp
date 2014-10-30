<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="c2" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql_rt" %>
<%@ taglib prefix="sql2" uri="http://java.sun.com/jstl/sql" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<br><br>
<fmt:bundle basename="jdbc">
 <fmt:message key="jdbc.url" var="url" />
 <fmt:message key="jdbc.driverClassName" var="driver" />
 <fmt:message key="jdbc.username" var="user" />
 <fmt:message key="jdbc.password" var="password" />
</fmt:bundle>
<sql:setDataSource var="guestBookDS" url="${url}" driver="${driver}" user="${user}" password="${password}"/>
<sql:query var="booklist" dataSource="${guestBookDS}" sql="select * from GUEST_BOOK" />

<table border="1" align="center">
<tr style="font-weight:bold;">
<td align="center" align="center" colspan=9>Guest Book Entries</td>
</tr>
<tr style="font-weight:bold;">
<td align="center">Book_Id</td>
<td align="center">Author_Title</td>
<td align="center">Author_Name</td>
<td align="center">Book_Data</td>
<td align="center">Author_Email</td>
<td align="center">Date_Of_Creation</td>
<td align="center">Last_Modification_Date</td>
<td align="center">Keywords</td>
<td align="center">Download</td>
</tr>

<c:forEach var="book" items="${booklist.rows}">
<sql:query var="keywordlist" dataSource="${guestBookDS}">
select * from KEYWORD where KEYWORD_ID IN (select KEYWORD_ID from GUEST_BOOK_KEYWORD_MAPPING where BOOK_ID = ? )
<sql:param value="${book.book_id}"/>
</sql:query>
<tr>
<td align="center"><c:out value="${book.book_id}"/></td>
<td align="center"><c:out value="${book.author_title}"/></td>
<td align="center"><c:out value="${book.author_name}"/></td>
<td align="center"><c:out value="${book.book_data}"/></td>
<td align="center"><c:out value="${book.guest_email_id}"/></td>
<td align="center"><c:out value="${book.date_of_creation}"/></td>
<td align="center"><c:out value="${book.last_modification_date}"/></td>
<td>
	<ul>
      <c:forEach var="keyword" items="${keywordlist.rows}" varStatus="status">
      	<li><c:out value="${keyword.keyword_value}"/></li>
      </c:forEach>
   </ul>
</td>
<td align="center">
<a href="./filedownload.app?id=<c:out value="${book.book_id}"/>"><c:out value="${book.author_name}"/></a>
</td>
</tr>
</c:forEach>

<tr style="font-weight:bold;">
<td align="center" align="center" colspan=9><a href="./filedownload.app">Download Csv File</a></td>
</tr>
</table>
</body>
</html>