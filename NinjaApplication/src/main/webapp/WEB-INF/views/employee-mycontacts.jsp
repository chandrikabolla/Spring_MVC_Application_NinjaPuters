<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page session="true" %>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="icon" type="image/png" href="resources/images/connection.png"/>
<spring:url value="/resources/core/js/jquery.1.10.2.min.js"
	var="jqueryJs" />
<script src="${jqueryJs}"></script>
</head>
<body>
<c:set var="employee" value="${requestScope.employee}" scope="session"/>
You have ${contactListSize} contacts in your addressBook

<div class="container" style="min-height: 500px">

	<div class="starter-template">
		<h1>Search Form</h1>
		<br>

		<div id="feedback"></div>

		<form class="form-horizontal" id="search-form">
			<div class="form-group form-group-lg">
				<label class="col-sm-2 control-label">Username</label>
				<div class="col-sm-10">
					<input type=text class="form-control" id="username">
				</div>
			</div>
			

			
		</form>

	</div>

</div>
<c:if test="${contactListSize gt 0}">
<div class="container">
	<c:forEach var="contact" items="${contacts}"> 
	<div class="alert alert-info">
	<p> Contact name : ${contact.contactName} ,Contact through: ${contact.contactEmail} </p>
	
	</div>
	</c:forEach>

</div>
</c:if>
<br/>
<script>
	jQuery(document).ready(function($) {

		$("#username").keyup(function(event) {

			// Disble the search button
			
			// Prevent the form from submitting via the browser.
			event.preventDefault();

			searchViaAjax();

		});

	});

	function searchViaAjax() {

		var search =  $("#username").val();

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}search/api/getSearchResult",
			data : search,
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				display(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				//display(e);
			},
			done : function(e) {
				console.log("DONE");
				
			}
		});

	}

	
	function display(data) {
		var contextPath="myapp/";
		var json = "<h4>Ajax Response</h4>";
		for(i in data)
			{
		json+= "<a href=addContact/"+data[i].contactname+">Add this contact: "+data[i].contactname+"</a>";
				
			}
		$('#feedback').html(json);
	}
</script>
</body>
</html>