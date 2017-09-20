<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ page session="true" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Make an announcement</title>
<link rel="icon" type="image/png" href="resources/images/connection.png"/>
</head>
<body>

Make an announcement here
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a href="${contextPath}/admin/logout">Logout</a>
<form:form action="${contextPath}/org/makeNewAnnouncement" method="post" commandName="announcement" >

<form:input path="title" required="required" /><font color="red"><form:errors path="title" /></font>

<form:input path="message" required="required" />
<input type="submit" value="Make Announcement"/>
</form:form>

</body>
</html>