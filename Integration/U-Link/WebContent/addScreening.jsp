<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
   
    <title>Add Screening</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/accountManagement.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
    
    $(document).ready(function() {
		$("#add").click(function() {
				var startingAge =  $('#age').val();
				var illness =  $('#illness').val();
				var screening =  $('#screening').val();
				var demographic =  $('#type').val();
				if(demographic ==""){
					console.log("please fill in");
				}
				var regularity =  $('#years').val();

				$.ajax({
					url : "Add",
					type : "get",
					data : 'age=' + startingAge
					+ '&illness=' + illness 
					+ '&screening='+ screening
					+ '&type='+ demographic 
					+ '&years='+ regularity,
					dataType : "json",
					success : function(data) {
					console.log(data.status);
						if (data.status == "success") {
							$("#addedSuccessfully").html("<div class='alert alert-success' role='alert'><strong><center>New screening added</center></strong></div>");
						}	else{
							console.log("fail");
						}											
					}
					});

				});
		
});
    
    </script>
    
</head>
<body>
<%@ include file="navigator.jsp"%>

<div class="page-header" align="center">
  <h1>Add new screening</h1>
</div>

	<div class="col-md-8 col-md-offset-2">

		<form>
				<div class="form-group">
					<label for="age">Age</label> <input type="text"
						class="form-control" placeholder="Starting age in which patient might be diagnosed with illness" id="age" required>
				</div>
				<div class="form-group">
					<label>Common Illness </label> 
					<input type="text" class="form-control" id="illness" required>
				</div>
				<div class="form-group">
					<label for="screening">Recommended screening</label> 
					<input type="text" class="form-control" id="screening" required>
				</div>
				
				<div >
				<label for="screening">Demographic affected</label> <br>
				<label class="checkbox-inline"><input type="checkbox" name="type" value="infant" id="type" >Infant</label>
				<label class="checkbox-inline"><input type="checkbox" name="type" value="female"id="type" >Female</label>
				<label class="checkbox-inline"><input type="checkbox" name="type" value="male" id="type" >Male</label>
				</div>
				<br>
				
				<div class="form-group">
					<label for="screening">Regularity of Screening (In years)</label> 
					<input type="text" class="form-control" placeholder="How often does one have to go for this screening" id="years" required>
				</div>
				
				
			<div align="center">
				<button type="submit" value="Submit" class="btn btn-default"
					id="add">Add</button>
			</div>
		</form>
</div>

<div class="col-md-8 col-md-offset-2" id="addedSuccessfully"></div>




</body>
</html>