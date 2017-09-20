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
<title>Add new Role</title>
<link rel="icon" type="image/png" href="resources/images/connection.png"/>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="level" value="${role.level}"/>
<form:form action="${contextPath}/admin/addnewRole" commandName="role"
		method="post">

		<table>
			<tr>
				<td>Role:</td>
				<td><form:input path="roleName" required="required"/>
					<font color="red"><form:errors path="roleName" /></font></td>
			</tr>
<form:hidden path="level" value="${level}"/>
			

			<tr>
				<td colspan="2"><input type="submit" value="create Role" /></td>
			</tr>
		</table>

	</form:form>



</body>
</html>