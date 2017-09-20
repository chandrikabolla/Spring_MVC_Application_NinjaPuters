<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page session="true" %>
    <%@taglib  uri="http://www.springframework.org/tags" prefix="spring"%>
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
<title>Register your organization</title>
<link rel="icon" type="image/png" href="resources/images/ninjabird.png"/>
<style>
body, html {
    height: 100%;
    margin: 0;
}

.bg {
    /* The image used */
    background-image: url("resources/images/OfficeSpace.png");

    /* Full height */
    height: 100%; 

    /* Center and scale the image nicely */
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
}
</style>
</head>
<body style="background-color:#FFFFF0">
<div class="bg">
<div class="container">
	<div class="panel panel-warning">
		<c:set var="contextPath" value="${pageContext.request.contextPath}" />
		<div class="panel-heading">
		Fill the below form to register you organization into this application:
		</div>
		<div class="panel-body text-center">
		<form action="${contextPath}/org/Register" method="post">
			
			<br/>
			<table class="text-center">
				
				<tr>
					<td><label for="orgName">	Organization name:</label>
					</td>
					<td>
						<spring:bind path="newOrg.orgName">		
						<input class="form-control"  value="${status.value}" id="orgName" type="text" name="${status.expression}" required="required"/>
						
		        <c:if test="${status.error}">
		            Error codes:
		            <c:forEach items="${status.errorMessages}" var="error">
		                <c:out value="${error}"/>
		            </c:forEach>
		        </c:if>
						</spring:bind>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name" >Admin name:</label>
					</td>
					<td>
						<spring:bind path="admin.name">
						<input  class="form-control" id="name" type="text" name="${status.expression}" required="required"/>
						    <c:if test="${status.error}">Error codes:
		            <c:forEach items="${status.errorMessages}" var="error">
		                <c:out value="${error}"/>
		            </c:forEach>
		        </c:if>
						</spring:bind>
					</td>
				</tr>
				<tr>
					<td>
						<label for="emailid">Admin Email ID:</label>
					</td>
					<td>
						<spring:bind path="admin.adminEmail">
						<input  class="form-control" id="emailid" type="email" name="${status.expression}" required="required"/>
						<c:if test="${status.error}">Error codes:
		            <c:forEach items="${status.errorMessages}" var="error">
		                <c:out value="${error}"/>
		            </c:forEach>
		        </c:if>
						</spring:bind>
					</td>
				</tr>
			
				<tr>
					<td colspan="2">
						<input type="submit" class="btn btn-warning" value="Submit Org"/>
					</td>
				</tr>
			</table>
		</form>
		</div>
	</div>
</div>
</div>
</body>
</html>