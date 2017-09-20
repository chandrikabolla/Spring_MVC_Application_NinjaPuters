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
<title>Insert title here</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="employee" value="${employee}"></c:set>
Hello ${employee.firstName} ${employee.lastName} !!!<br/>
Your employee id is ${employee.empid}
<br/>You can view and update your profile here.<br/>
<form:form action="${contextPath}/employee/updateEmpProfile" commandName="employee" method="post">
<table>
<form:input type="hidden" path="empid" value="${employee.empid}"/>
<tr>	
	<td>First Name: </td>
	<td><form:input path="firstName" class="form-control" required="required" disabled="disabled"/><font color="red"><form:errors path="firstName" /></font>
	</td>
</tr>
<tr>	
	<td>Last Name: </td>
	<td><form:input path="lastName" required="required" class="form-control" disabled="disabled"/><font color="red"><form:errors path="lastName" /></font>
	</td>
</tr>


<tr>	
	<td>Personal Email ID: </td>
	<td><form:input type="email" path="personalEmailID.emailAddress" class="form-control" required="required" /><font color="red"><form:errors path="personalEmailID.emailAddress" /></font>
	</td>
</tr>

<tr>	
	<td>Company Email ID: </td>
	<td><form:input type="email" path="companyEmailID.emailAddress" class="form-control" required="required" /><font color="red"><form:errors path="companyEmailID.emailAddress" /></font>
	</td>
</tr>
<tr>
	<td>Permanent Address: </td>
	<td><form:input path="permanentAddress.street" required="required" class="form-control" /><font color="red"><form:errors path="permanentAddress.street" /></font>
	</td>
</tr>
<tr>
	<td>Temporary Address: </td>
	<td><form:input path="temporaryAddress.street" required="required" class="form-control" /><font color="red"><form:errors path="temporaryAddress.street" /></font>
	</td>
</tr>
</table>

<input type="submit" class="btn btn-default" value="update"/>
</form:form>



</body>
</html>