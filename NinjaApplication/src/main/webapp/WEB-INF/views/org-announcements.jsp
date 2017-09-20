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
<spring:url value="/resources/core/js/jquery.1.10.2.min.js"
	var="jqueryJs" />
<script src="${jqueryJs}"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Org announcements</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<br/>
You  have ${size} posts on each page.<br/>

<c:if test="${size gt 0}">

<ul class="pagination">
<c:forEach var="count" begin="1" end="${endSize}">
    <li><a href="<c:url value="/announcements?page=${count}" />">${count}</a></li>
</c:forEach>
  </ul>
  </c:if>
<c:forEach items="${annList}" var="announcement">
<div class="alert alert-info">
<c:set var="modalid" value='#${announcement.title}'/>
    <strong>Info!</strong> Title:  ${announcement.title}&nbsp;&nbsp;<a href="#" class="alert-link" data-toggle="modal" data-target="${modalid}">read this post</a>.
  </div>
<!-- Modal -->
  <div class="modal fade" id="${announcement.title}" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">${announcement.title}</h4>
        </div>
        <div class="modal-body">
          <p>${announcement.message}</p>
        </div>
        <div class="modal-footer">
      
          <button type="button" id="#buttonId" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>



</c:forEach>

</table>
<p id="result">
</p>



<script>
jQuery(document).ready(function($) {
//Current request reference; can be used else where
var request;
 
/* attach a submit handler to the form */
$("#buttonId").submit(function(event) {
 
    // abort any pending request
    if (request) {
        request.abort();
    }
     
  /* stop form from submitting normally */
  event.preventDefault();
 
  /*clear result div*/
   $("#result").html('yes entered');
 
  /* get some values from elements on the page: */
   //var values = $(this).serialize();
 
  /* Send the data using post and put the results in a div */
  request =$.ajax({
      url: "${contextPath}/announcementsajax",
      type: "get",
		
      success: function(){
           $("#result").html('submitted successfully');
      },
      error:function(){
          $("#result").html('there is error while submit');
      }   
  }); 
});

});

</script>
</body>
</html>