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
<title>Employee login for <c:out value="${org.orgName}"/></title>
<link rel="icon" type="image/png" href="resources/images/connection.png"/>
</head>
<body>
Employee login page

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:if test="${requestScope.loginfail eq 'Empty' }">
	
		<div >
	
			<p><font color="red">ID/UserName/Password you entered is empty</font>
			</p>
		</div>
	</c:if>
	
	<c:if test="${requestScope.loginfail eq 'Wrong' }">
	
		<div >
	
			<p><font color="red">ID/UserName/Password you entered is wrong</font>
			</p>
		</div>
	</c:if>
	
	<c:if test="${requestScope.loginfail eq 'LoginFirst' }">
	
		<div >
	
			<p><font color="red">Employee!!! You have to first log in.</font>
			</p>
		</div>
	</c:if>
<div id="employeeIDForm">
<form:form action="${contextPath}/employee/homepage" commandName="employee"
		method="post">

		<table>
			<tr>
				<td>UserName:</td>
				<td><form:input path="employeeUserAccount.userName" size="30" required="required" />
					<font color="red"><form:errors path="employeeUserAccount.userName" /></font></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><form:password path="employeeUserAccount.password" size="30" required="required" />
					<font color="red"><form:errors path="employeeUserAccount.password" /></font></td>
			</tr>


			<tr>
				<td>Employee id:</td>
				<td><form:input  path="empid" size="30" required="required" />
					<font color="red"><form:errors path="empid" /></font></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Register Employee" /></td>
			</tr>
		</table>

	</form:form>

</div>
</body>
</html>