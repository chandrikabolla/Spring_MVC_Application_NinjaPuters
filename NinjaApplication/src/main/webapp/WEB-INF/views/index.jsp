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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title><c:out value="${org.orgName}"/></title>
 <link rel="icon" type="image/png" href="resources/images/connection.png"/>
</head>
<body>


<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:if test="${reqAdminExists eq 'EXISTS' }">
	<div class="container">
		<h4 style="text-align:center;">You have chosen ${org} as your Organization</h4>
		<div class="row">
		<div class="col-lg-6" style="background-color:lavender;">	
		<h4><a href="${contextPath}/org/adminLogin">Go to admin login</a></h4>
		</div>
		<div class="col-lg-6" style="background-color:lavenderblush;">
		<h4><a href="${contextPath}/org/employeeLogin">Go to Employeelogin</a></h4>
		</div>
		
		</div>
		<br/>
		<div class="container">
		<div class="row">
		<c:if test="${NumOrgImages gt 0}">		
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
		    <!-- Indicators -->
		    <ol class="carousel-indicators">
	
		    <c:set var="endCount" value="${Num}"/>
		    <c:forEach var="count" begin="0" end="${endCount}" step="1">
		    <li data-target="#myCarousel" data-slide-to="${count}"></li>
		    </c:forEach>
		      
		    </ol>

    			<!-- Wrapper for slides -->
  		 	<div class="carousel-inner" role="listbox">
  		 	<c:set var="count" value="0" scope="page" />
			<c:forEach var="image" items="${ImageList}">
				<c:if test="${count == 0}">
				<div class="item active">
		        <img src="${contextPath}/getImage/${image.occasion}/${image.fileString}" alt="${image.filename}" width="1460" height="1045">
		        <div class="carousel-caption">
		          <h3 style="color:black;"><c:out value="${image.occasion}" /></h3>
		          <p>The atmosphere in Chania has a touch of Florence and Venice.</p>
		        </div>
		         </div>
		         </c:if>
		         <c:if test="${count gt 0}">
		         
		        	<div class="item">
		        <img src="${contextPath}/getImage/${image.occasion}/${image.fileString}" alt="/image/${image.occasion}/${image.fileString}.jpg" width="1460" height="1045">
		        <div class="carousel-caption">
		          <h3 style="color:black;"><c:out value="${image.occasion}" /></h3>
		          <p>The atmosphere in Chania has a touch of Florence and Venice.</p>
		        </div>
		         </div>
		         </c:if>
		        <c:set var="count" value="${count + 1}" scope="page"/>
		     
			
			</c:forEach>
		      
		  
		    </div>

		    <!-- Left and right controls -->
		    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
		      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
		      <span class="sr-only">Previous</span>
		    </a>
		    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
		      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		      <span class="sr-only">Next</span>
		    </a>
		  </div>
		  </c:if>
		  </div>
	</div>
	</div>

</c:if>
<c:if test="${reqAdminExists eq 'NoAdmin' }">
	
	<div class="container">
		<div class="row">
		<div class="col-lg-6" style="background-color:lavender;">	
		<form:form commandName="newAdmin" action="${contextPath}/org/adminRegister" method="POST">
		<form:input path="name" required="required" />
	
		<form:input type="email" path="adminEmail" required="required" />
		
		<input type="submit" />
		
		</form:form>
		</div>
	</div>
</c:if>

</body>
</html>