	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
	<style>
		/* disables dataTable interaction for table header located over the 'submit' button */
		.dataTable > thead > tr > .sorting-disabled 
		{ 
			background:none; 
			pointer-events:none;
		}
	</style>
	
	<header>
		<div class="container">
		
		<c:choose>
		<c:when test="${not empty message }">
	  	<p class="alert ${messageClass}">${message }</p>
		<%
	  	session.setAttribute("message", null);
	  	session.setAttribute("messageClass", null);
		%>
		</c:when>
		</c:choose>
		
		<h1>eZoo <small>Animal Care</small></h1>
		<hr class="paw-primary">
		<h4>Assign a feeding schedule for <c:out value="${animalName }" /> (ID: 
		<c:out value="${animalID }" />)</h4>
		
		<table class="table table-striped table-hover table-responsive ezoo-datatable">
			<thead>
				<tr>
					<th class="text-center">Schedule ID</th>
					<th class="text-center">Feeding Time</th>
					<th class="text-center">Recurrence</th>
					<th class="text-center">Food</th>
					<th class="text-center">Notes</th>
					<th class="sorting-disabled"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="schedule" items="${schedules }">
					<tr>
						<td><c:out value="${schedule.scheduleID}" /></td>
						
						<td><c:out value="${schedule.feedingTime}" /></td>
						<td><c:out value="${schedule.recurrence}" /></td>
						<td><c:out value="${schedule.food}" /></td>
						<td><c:out value="${schedule.notes}" /></td>
						
						<td style="background-color: none">
							<form action="AssignFeedingSchedule" method="post">
							<input type="hidden" name="scheduleID" value="${schedule.scheduleID }" />
								<input type="hidden" name="animalID" value="${animalID }" />
								<button type="submit" class="btn btn-default">Select</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	
		</div>
	</header>
	
	<jsp:include page="footer.jsp" />