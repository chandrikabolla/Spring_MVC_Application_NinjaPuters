<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page session="true" %>
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
<title>Upload here</title>
<link rel="icon" type="image/png" href="resources/images/connection.png"/>
</head>
<body>
<div class="container">
<a href="${contextPath}/admin/logout">Logout</a>
	<div class="row">
	
		<div class="panel panel-success">
			<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
			
			<c:set var="numOffiles" value="${knowledgeChapter.numOffiles}"/>
			<div class="panel-header">
			Add files into the knowledgeChapter
			</div>
			<div class="panel-body">
			<form:form commandName="knowledgeChapter" method="post" action="${contextPath}/org/uploadChapter" enctype="multipart/form-data">
			Title: <form:input path="title"/><br/>
			<c:forEach var="fileUpload" varStatus="status" items="${knowledgeChapter.fileUploads}">
			
			Filename:<input type="text" name="fileUploads[${status.index}].filename" /><br/>
			Select File: <input type="file" name="fileUploads[${status.index}].file"/><br/>
			</c:forEach>
			<form:hidden path="numOffiles" value="${numOffiles}"/>
			<input type="submit" value="Upload "/>
			</form:form>
			</div>
		</div>
	</div>
</div>
</body>
</html>