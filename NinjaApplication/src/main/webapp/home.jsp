<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
  .footer {
    position: absolute;
    bottom: 0;
    width: 100%;
    height: 50px;
    background-color: #f5f5f5;
	}
  </style>
  <title>Application home</title>
  <link rel="icon" type="image/png" href="resources/images/ninjabird.png"/>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
     <nav class="navbar navbar-inverse">
  			<div class="container-fluid">
	    		<div class="navbar-header">
	      			<a class="navbar-brand" href="#">NinjaPuter</a>
	    		</div>
	    		<ul class="nav navbar-nav">
	      			<li class="active"><a href="resources/images/OfficeSpace.png" target="iframea" >Home</a></li>
	      			<li><a href="${contextPath}/aboutUs" target="iframea">What are we?</a></li>
	      			<li><a href="${contextPath}/mapUs" target="iframea">Where are we</a></li>
	     			<li><a href="${contextPath}/orgSelectionPage" target="_blank">Go to Organization Home</a></li>
	   			</ul>
  			</div>
	</nav>
	<div class="container">
	  <iframe name="iframea" frameBorder="0" id="iframea" src="resources/images/OfficeSpace.png" class="responsive" style="width:1210px; height:610px;">
	 
	  </iframe>
	</div>
	
	<footer class="footer">
      <div class="container">
        <p class="text-muted">We are Computer Ninjas!!!!!!!!!</p>
      </div>
    </footer>
</body>
</html>