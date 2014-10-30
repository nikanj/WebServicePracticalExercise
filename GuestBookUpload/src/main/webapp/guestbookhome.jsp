<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c2" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script src="js/searchbox.js"></script>

<style type="text/css">
h4 {color:red;}
.error {
	color: #ff0000;
}
.t { border-top: 1px solid black; }
.r { border-right: 1px solid black; height: 100%; }
</style>
<title>GuestBookHome</title>
</head>
<body>
<div>
<div align="left">
<a href="./createguestbook.app">Create entry</a>
</div>
<div align="right">
<c:if test="${guestbookhomeform.uploadedGuestBookEntryList != null && fn:length(guestbookhomeform.uploadedGuestBookEntryList) > 0}">
<a href="./guestbookupload.app">View Uploaded Guest Book Entries</a>
</c:if>
<form:form id="uploadform" method="POST" action="./guestbookupload.app" enctype="multipart/form-data" commandName="guestbookuploadform">
            <input type="file" name="file" />
            <input type="submit" value="upload"/>
</form:form>
</div>
</div>
<form:form id="displayForm" method="POST" action="./guestbookhome.app" commandName="guestbookhomeform">
<hr>
<div align="right">

	    <label for="searchTextId">Search: </label>
	    <input name="searchtext" id="searchTextId"/>
		<input id="filterid" name="filtersubmit" type="submit" value="GO" />
</div>

<div id="displayDiv">
	<jsp:include page="guestbookdisplaylist.jsp"></jsp:include>
</div>
<hr>
</form:form>
</body>
</html>