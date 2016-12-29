<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
   
    <title>Create Account</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
 	
 	<script type="text/javascript">
	$(document).ready(function() {
		$("#create").click(function() {
			var username =  $('#inputEmail').val();
			var accountType = $("#accountType").val();
			var password =  $('#inputPassword').val();
			console.log(username);
			console.log(accountType);
			console.log(password);
				$.ajax({
					url : "CreateUser",
					type : "post",
					data : 'username=' + username
							+ '&roles=' + accountType
							+'&password='+ password,
					dataType : "json",
					success : function(data) {
					console.log("hey");
					if (data.status == "success") {
						$("#createSuccess").html("<div class='col-sm-offset-2 col-sm-4'><div class='alert alert-success' role='alert'>Account successfully created</div></div>");
					}else{
						console.log("fail");
					}
					}
					});
					
					
			});
			
	});	
 	</script>
 
  </head>
  <body>
    <%@include file="navigator.jsp"%> 
	
	<div class="panel panel-default">
	  <div class="panel-body">
	   <b>New Account</b>
	  </div>
	</div>
	
	<form class="form-horizontal" >
  <div class="form-group">
    <label class="col-sm-2 control-label">Email</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="inputEmail" placeholder="Email" required> <b>@Ulinkassist.com</b>
    </div>
  </div>
 
 <div class="form-group">
    <label class="col-sm-2 control-label">Account Type</label>
    <div class="col-sm-4" id="accountType"> 
      <select class="form-control">
	  <option value="Admin"> Admin</option>
	  <option value="Non-Admin">Non-Admin</option>
	</select>
    </div>
  </div>
  
  
  <div class="form-group">
    <label class="col-sm-2 control-label">Password</label>
    <div class="col-sm-4">
      <input type="password" class="form-control" id="inputPassword" placeholder="Password" required>
    </div>
  </div>
  
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10" id="create">
      <button type="submit" class="btn btn-default">Create New Account</button>
    </div>
  </div>
</form>
	<div id="createSuccess"></div>
  </body>
</html>
