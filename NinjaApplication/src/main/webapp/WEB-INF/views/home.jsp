<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false" %>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<title>Home</title>
	
	<style>
body, html {
    height: 100%;
    margin: 0;
}

.bg {
    /* The image used */
    background-image: url("resources/images/greenoffice.jpg");

    /* Full height */
    height: 100%; 

    /* Center and scale the image nicely */
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
}
</style>
</head>
<body>
<div class="bg">
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <div class="container">
	<h1>Hello User!</h1>
	
	<div class="row">
		
		<div class="col-lg-6">
			<div class="panel panel-success">
				<div class="panel-heading">Choose your Organization to go further..........
				</div>
				<div class="panel-body text-center">
					<form:form commandName="org" action="${contextPath}/org" method="post">
						<c:if test="${orgsSize gt 0}">
						<table class="table-responsive text-center">
							<tr>
								<td>
								Organization:
								</td>
								<td> <form:select path="orgName"  class="form-control" items="${orgs}"></form:select>
							
								</td>
							</tr>
							<tr>
								<td colspan="2">
								<input type="submit" class="btn btn-success" value="Enter your organization" />
								</td>
							</tr>
						
						</table>
						
						</c:if>
						
					</form:form>
				</div>
			</div>
		</div>
		
		<div class="col-lg-6">		
			<div class="panel panel-success">
				<div class="panel-heading">
				Register Today for free.............
				</div>
				<div class="panel-body">
				It is free to register your organization.You only have to give the name of organization and name of admin and his/her email address.
					<a href="${contextPath}/org/Register" class="btn btn-success">Register your organization </a>
				</div>
			</div>		
		</div>
	
	</div>
</div>
</div>
</body>
</html>
