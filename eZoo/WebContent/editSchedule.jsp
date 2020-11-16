	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
	<header>
	
	<div class="container">
	
	<h1>eZoo <small>Animal Care</small></h1>
		<hr class="paw-primary">
		<h4>View/Edit Schedule</h4>
		<br />
		<form action="EditFeedingSchedule" method="post" class="form-horizontal">
		<div class="form-group">
		    <label for="id" class="col-sm-4 control-label">Schedule ID</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="scheduleID" name="scheduleID" value="${schedule.scheduleID}" disabled />
		    </div>
		</div>
		<div class="form-group">
		    <label for="id" class="col-sm-4 control-label">Feeding Time</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="feedingTime" name="feedingTime" value="${schedule.feedingTime}" required="required"/>
		    </div>
		</div>
		<div class="form-group">
		    <label for="id" class="col-sm-4 control-label">Recurrence</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="recurrence" name="recurrence" value="${schedule.recurrence}" required="required"/>
		    </div>
		</div>
		<div class="form-group">
		    <label for="id" class="col-sm-4 control-label">Food</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="food" name="food" value="${schedule.food}" required="required"/>
		    </div>
		</div>
		<div class="form-group">
		    <label for="id" class="col-sm-4 control-label">Notes</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="notes" name="notes" value="${schedule.notes}" />
		    </div>
		</div>
		
		<div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		    	<input type="hidden" name="ID" value="${schedule.scheduleID}" />
		     	<button type="submit" class="btn btn-primary">Update</button>
		    </div>
		    <div class="col-sm-2">
		      <a href="animalCare" class="btn btn-default">Home</a>
		    </div>
		  </div> 
		  
		</form>
	
	</div>
	
	</header>
	
	<jsp:include page="footer.jsp" />