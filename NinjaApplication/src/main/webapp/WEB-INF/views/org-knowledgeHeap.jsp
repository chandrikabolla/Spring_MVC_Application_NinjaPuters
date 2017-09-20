<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page session="true" %>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Org knowledgeheap</title>
<link rel="icon" type="image/png" href="resources/images/connection.png"/>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
We have ${chapters} chapters for you.<br/>
<c:forEach items="${kclist}" var="knowledgeChapter">

<div >
	<h4>${knowledgeChapter.title}</h4>
	<p>This chapter contains ${knowledgeChapter.numOffiles} files.</p>
<c:set var="count" value="0" scope="page" />
	<c:forEach var="fileUpload" items="${knowledgeChapter.fileUploads}">
	<c:set var="count" value="${count + 1}" scope="page"/>
		<p> This file is ${fileUpload.filename}</p>
	
		<a href="<c:url value='/download/${fileUpload.fileString}'/>"> Click here to download this file</a> 
	 	
	 </c:forEach>

</div>
</c:forEach>
<script>


</script>
</body>
</html>