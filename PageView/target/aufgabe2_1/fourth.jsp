<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Cache-Control"
	content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
</head>
<body>
	<h4>
		Welcome to the first jsp file!!!</br> Click on the link to go to the index
		page: <a href="http:index.jsp?filename=index">link</a>
	</h4>
	number of accesses are
	<%=request.getSession().getServletContext()
					.getAttribute("four")%>
</body>
</html>