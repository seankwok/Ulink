<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
   
    <title>View Screening</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
 	
 	<script type="text/javascript">
	$(document).ready(function() {
		$("#generate").click(
		var demographic =  $("#type").val();
		var age =  $("#age").val();
		)}
	
		$("#editScreening").click(
		var editedAge = $("#editedAge").val();
		var editedCommonIllness = $("#editedCommonIllness").val();
		var editedRecommendedScreening = $("#editedRecommendedScreening").val();
		var editedType = $("#editedType").val();
		var editedFrequency = $("#editedFrequency").val();
		
		// pass values to your backend
		)		
	)
 	</script>
 
 
 
 
 
  </head>
  <body>
    <%@include file="navigator.jsp"%> 
	

<div class="col-md-8 col-md-offset-2">
<div class="panel panel-default">
  <div class="panel-body">
  <h3><b>Filter results</b></h3>
    <form>
    	<div class="form-group">
    	<div class="row">
		<div class="col-md-4">
			<label for="screening">Demographic affected</label> <br>
				<label class="checkbox-inline"><input type="checkbox" name="type" id="type" value="infant">Infant</label>
				<label class="checkbox-inline"><input type="checkbox" name="type" id="type" value="female">Female</label>
				<label class="checkbox-inline"><input type="checkbox" name="type" id="type" value="male">Male</label>
		</div>
				
		<div class="col-md-4">
		<label for="screening">Age Range</label> 
		<select class="form-control" id="age">
			<option value="0-10">0-10</option>
			<option value="11-20">11-20</option>
			<option value="21-30">21-30</option>
			<option value="31-40">31-40</option>
			<option value="41-50">41-50</option>
			<option value="51-60">51-60</option>
			<option value="61-70">61-70</option>
			<option value="71-80">71-80</option>
			<option value=">81">>81</option>
		</select>
		</div>
		
		<div align="center">
		<button type="button" class="btn btn-default btn-lg" id="generate"><a href="filteredResults.html"> Generate </a></button>
		</div>
		
		
		</div>
		</div>
    </form>
  </div>
  </div>
  </div>



<div class="container">
  
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>Age at which patients should start going for screenings</th>
        <th>Common Illness</th>
        <th>Recommended Screening</th>
        <th>Affected Demographic</th>
        <th>Frequency of screening</th>
        <th>View Patients</th>
        <th>Edit</th>
        <th>Delete</th>
      </tr>
     
       <%@ page import="ulink.constructor.Condition" %>
		  <%@ page import="java.util.*" %>
		  <%
		  	ArrayList<Condition> conditionList = (ArrayList<Condition>)request.getAttribute("conditionList");
    		if(conditionList !=null){
    			for(Condition condition: conditionList){
    				out.println("<tr><th>");
    			
    		  		out.println(condition.getAgeRequired());
    		  		
    		  		
    		  		out.println("</th>");
    		  		
    		  		out.println("<td>" + condition.getConditionName() + "</td>");
    		  		
    		  		out.println("<td>" + condition.getScreening() + "</td>");
    		  		
    		  		out.println("<td>" + condition.getType() + "</td>");
    		  		
    		  		out.println("<td>" + condition.getYears() + "</td>");
    		  		
    		  		
    		  		%>
    
        <td><a href ="">Link to client list</a></td>
        <td>
        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#editScreening">Edit</button>
        <div class="modal fade" id="editScreening" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title">Edit screening information</h4>
		      </div>
		      <div class="modal-body">
		        <form>
		          <div class="form-group">
		            <label for="age" class="control-label">Age at which patients should start going for screening:</label>
		            <input type="text" class="form-control" id="editedAge">
		          </div>
		          <div class="form-group">
		            <label for="illness" class="control-label">Common Illness:</label>
		            <input type="text" class="form-control" id="editedCommonIllness">
		          </div>
		          <div class="form-group">
		            <label for="screening" class="control-label">Recommended Screening:</label>
		            <input type="text" class="form-control" id="editedRecommendedScreening">
		          </div>
		          <div class="form-group">
		            <label for="demo" class="control-label">Affected demographic:</label>
					<label class="checkbox-inline"><input type="checkbox" name="type" id="editedType" value="infant">Infant</label>
					<label class="checkbox-inline"><input type="checkbox" name="type" id="editedType" value="female">Female</label>
					<label class="checkbox-inline"><input type="checkbox" name="type" id="editedType" value="male">Male</label>
		          </div>
		          <div class="form-group">
		            <label for="frequency" class="control-label">Frequency of Screening:</label>
		            <input type="text" class="form-control" id="editedFrequency">
		          </div>
		        </form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-success">Update Information</button>
		      </div>
		    </div>
		  </div>
		</div>
		        
        </td>
        <td>
        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#deleteUser">Delete</button>
				<div class="modal fade" id="deleteUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" >You are about to delete a screening. Proceed?</h4>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			        <button type="button" class="btn btn-primary">Yes</button>
			      </div>
			    </div>
			  </div>
			</div>
			</td>
			
		<% 
    			} 
    			
    		}else{
    			response.sendRedirect("DisplayAll");
    		}
		
		%>
      </tr>
      
    </thead>
  </table>
</div>
  </body>
</html>
