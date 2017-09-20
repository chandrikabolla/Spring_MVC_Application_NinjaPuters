<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page session="true" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="icon" type="image/png" href="resources/images/connection.png"/>
<style type="text/css">
#contentDiv{
    width:100%;
    height: 100%;
    padding-bottom: 56%; /* Change this till it fits the dimensions of your video */
    position: relative;
}

#contentDiv iframe {
    width: 100%;
    height: 100%;
    position: absolute;
    display: block;
    top: 0;
    left: 0;
}
</style>
<title>Homepage:Employee
</title>
</head>
<body>
<div class="jumbotron">
  <h1><c:out value="${org.orgName}"></c:out></h1>      
 
</div>
<div class="container">

  <div class="row">
    <div  style="background-color:lavender;">
    <ul class="nav nav-tabs">
  <li class="active"><a href="<c:url value="/announcements?page=1" />" target="contentFrame">Announcements</a></li>
  <li><a href="<c:url value="/myContacts" />" target="contentFrame">My Contacts</a></li>
  <li><a href="<c:url value="/orgPhotos" />" target="contentFrame">My Groups</a></li>
  <li><a href="<c:url value="/myProfile" />" target="contentFrame">My Profile</a></li>
  <li><a href="<c:url value="/knowledgeHeap" />" target="contentFrame">KnowledgeHeap</a>
  <li><a href="<c:url value="/employee/logout"/>" >Log out</a>
</ul>
    
    </div>
    <div  id="contentDiv" style="background-color:lavenderblush;"><iframe name="contentFrame"></iframe></div>
  </div>

</div>
</body>
</html>