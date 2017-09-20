<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Here you go Admin!!!!!!!</title>
	<link rel="icon" type="image/png" href="resources/images/connection.png"/>
</head>
<body>
	<h3>This is admin login page</h3>
	<br/>
	<hr size="5"/>
	<c:if test="${requestScope.loginfail eq 'Empty' }">
	
		<div >
	
			<p><font color="red">UserName/Password you entered is empty</font>
			</p>
		</div>
	</c:if>
	
	<c:if test="${requestScope.loginfail eq 'Wrong' }">
	
		<div >
	
			<p><font color="red">UserName/Password you entered is wrong</font>
			</p>
		</div>
	</c:if>
	
	<c:if test="${requestScope.loginfail eq 'LoginFirst' }">
	
		<div >
	
			<p><font color="red">Admin!!! You have to first log in.</font>
			</p>
		</div>
	</c:if>
	
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />


	<form:form commandName="adminUserAccount" action="${contextPath}/admin/homepage" method="post">
		<form:label path="userName">UserName:</form:label>
		<form:input type="text" path="userName" /><font color="red"><form:errors path="userName" /></font>
		
		<form:label path="password">Password:</form:label>
		<form:input type="password" path="password" /><font color="red"><form:errors path="password" /></font>
		
		<input type="submit"/>

	</form:form>

</body>
</html>