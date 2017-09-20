<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page session="true" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Insert title here</title>
</head>

<body >
<div class="jumbotron">
Welcome to <c:out value="${sessionScope.org.orgName}" /> 
</div>
<div class="container">
		<c:set var="contextPath" value="${pageContext.request.contextPath}" />
		<a href="${contextPath}/admin/logout">Logout</a>
		<br/>
	
	
	<div class="row">
		<!--  PDF document download of employee list -->
		<div class="col-lg-4" >
		<div class="panel panel-primary hoverable">
			<div class="panel-heading">Download PDF</div>
			<div class="panel-body">
		 Download the list of employees in your organization 
			
		
			<a href="${contextPath}/org/downloadPDF" target="_blank" class="btn btn-primary">Employees in Organization</a>
			</div>
		</div>
		</div>
		<div class="col-lg-4" id="addEmployeeDIV" >
		<div class="panel panel-success hoverable">
			<div class="panel-heading">Register an Employee</div>
			<div class="panel-body">
			
			<form:form action="${contextPath}/admin/registeremployee" commandName="empRole"	method="post">
			
			
				Add a new Employee by selecting a role from the list below<br/>
				Role:
					<form:select  class="form-control" path="roleName" id="roleName" items="${roles}"></form:select>
						<font color="red"><form:errors path="roleName" /></font><input type="submit" class="btn btn-success" value="Register Employee" />
					

			</form:form>
		
			</div>
			</div>
		</div>
		
		<!-- Adding a role into the database using the list of levels from the modeland view object -->
		
		<div  class="col-lg-4" id="addRoleDIV" >

		<div class="panel panel-info">
			<div class="panel-heading">Register a new Role</div>
			<div class="panel-body">
		
	
			<form:form action="${contextPath}/admin/registerOrgRole" method="post" commandName="role">
		
				<table>
					<c:if test="${roleadded eq 'SUCCESS'}"> 
					<tr>
					<td colspan="2">Hurray!!! Role has been added successfully
					</td>
					</tr>
					</c:if>
					<c:if test="${roleadded eq 'FAILURE'}"> 
					<tr>
					<td colspan="2">Role could not be added into Database.Please try again
					</td>
					</tr>
					</c:if>
				Add a New Role to your organization by selecting the level from the list below
					<tr>
						<td>Level:<form:select  class="form-control" path="level" id="level" items="${levels}"></form:select>
							<input type="submit" class="btn btn-info" value="Register a new Role" /></td>
					</tr>
				</table>
		
			</form:form>
			</div>
			</div>
	
		</div>
	
	</div>
	
	
	
	<div class="row">
		<div class="col-lg-4" >
			
			<div class="panel panel-warning" id="addKnowledgeChapterDiv">
			<div class="panel-heading">Add a new Knowledge Chapter</div>
			<div class="panel-body">
			<table>
					<form:form action="${contextPath}/org/addKnowledgeChapter" commandName="knowledgeChapter">
					Add as many files as you want into a chapter...........
					<tr><td>Num of files:
					<form:input class="form-control" type="number" path="numOffiles" required="required" />
					
					<input type="submit" class="btn btn-warning" value="Click here to add knowledge chapters "/>
					</td>
					</tr>
					</form:form>
			</table>
			</div>
			</div>
			
		</div>
		<div class="col-lg-4" >
		<div class="panel panel-default" id="addPhotoDiv">
			<div class="panel-heading">Make an announcement</div>
			<div class="panel-body">
		You can make an announcement in the organization by clicking the link below<br/>
		<a href="${contextPath}/org/makeAnnocuncement" class="btn btn-default" >Make an annoucement</a>
		</div>
		</div>
		</div>
		
		
		<div class="col-lg-4" >
		
			<div class="panel panel-danger" id="addPhotoDiv">
			<div class="panel-heading">Add a new Photo to Album</div>
			<div class="panel-body">
			<table>
			<form:form action="${contextPath}/org/addOrgImage" commandName="orgImage" enctype="multipart/form-data" method="post">
				
				<c:if test="${imageAdded eq 'SUCCESS'}">
				<tr>
				<td> Image has been added successfully.
				</td>
				</tr>
				</c:if>
				<c:if test="${imageAdded eq 'FAILURE'}">
				<tr>
				<td> Image could not be added succesfully.Please try again to upload here!
				</td>
				</tr>
				</c:if>
				Add a new photo to the organization album
				<tr>
					
					<td>Occasion:
					
					<form:input class="form-control" type="text" path="occasion" required="required" />
					</td>
				</tr>
				<tr>
					<td>Select File:
	
					 <form:input type="file" name="file" path="file" required="required"/>
					 </td>
				</tr>
				<tr>
				<td>
					<input type="submit" class="btn btn-danger" value="Click here to add Photo "/>
				</td>
				</tr>
					</form:form>
			</table>
			</div>
			
			</div>
		
		</div>
	</div>
	
</div>	




</body>
</html>