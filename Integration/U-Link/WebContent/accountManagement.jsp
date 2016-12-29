<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
   
    <title>Account Management</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/accountManagement.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
 
 	<script type="text/javascript">
	$(document).ready(function() {
		
		$("#reset").click(function() {
			var email = $("#email").val();
			var password = $("#password").val();
			console.log(email);
			console.log(password);
			
			$.ajax({
				url : "EditUser",
				type : "post",
				data :'email=' + email + '&password='+ password,
				dataType : "json",
					success : function(data) {
						console.log("test");
					if (data.status == "success") {
						
						$("#inputAlert").html("<div class='alert alert-success' role='alert'><strong>Password has been reset</strong></div>");
					}else{
						console.log("fail");
					}
					}
				});
		});
		
		
		
		$("#delete").click(function() {
			var email = $("#email").val();
			
			$.ajax({
				url : "DeleteUser",
				type : "get",
				data : 'email=' + email,
				dataType : "json",
				success : function(data) {
				console.log("hey");
				console.log(data.status);
				if (data.status == "success") {
					$("#inputAlert").html("<div class='alert alert-success' role='alert'><strong>User has been removed</strong></div>");
				}else{
					console.log("test fail");
				}											
				}
			});
		});
	});
		
	
</script>
 
  </head>
<body>
    
<%@ include file="navigator.jsp"%>
  

	<div class="col-md-6 col-md-offset-3">
	<div class="panel panel-default">
	  <div class="panel-body">
	 	
	   <div align="center"><h3><b>You are currently logged in as Admin </b></h3><br>
	   <form action="createAccount.jsp"><input type="submit" class="btn btn-default" value="Create new account"></form></button>
	   </div>
	   
	  
	  </div>
  	
	</div>
	</div>
	 
	 
	 
		<div class="container" id="tableDiv">
			<table class="table table-bordered">
		  <tr>
		    <th>Username</th> 
		    <th>Reset Password</th>
		    <th>Delete</th>
		  </tr>
		  <%@ page import="ulink.constructor.User" %>
		  <%@ page import="java.util.*" %>
		  <%
		  	ArrayList<User> userList = (ArrayList<User>)request.getAttribute("userList");
    		if(userList !=null){
    			for(User user: userList){
    				out.println("<tr><td>");
    				String email = user.getEmail();
    		  		out.println(email);
    		  	
    		  		out.println("</td>");
    		  		
    	%>
    		  		
    		  		<td>
			<form class="form-inline">
			  <div class="form-group">
			  	<input type="hidden" name="username" value="<%=email%>" id="email">
			    <input type="text" class="form-control" id="password" placeholder="New Password" required>
			  </div>
			  <button type="submit" class="btn btn-default" id="reset">Reset</button>
			</form>
			</td>
			<td>
			  <button type="button" class="btn btn-default" data-toggle="modal" data-target="#deleteUser">Delete</button>
				<div class="modal fade" id="deleteUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" >You are about to delete a user. Proceed?</h4>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			        <button type="submit" class="btn btn-primary" id="delete" data-dismiss="modal">Yes</button>
			      </div>
			    </div>
			  </div>
			</div>
			 </td>
    		  		
    		  		<%
    		  		
    		  		
    		  	}
    		}
    		else{
    			response.sendRedirect("DisplayAllUser");
    		}
		  	
		  
		  %>
		  
			</table>
		</div>
		
		<div class="col-md-8 col-md-offset-2" id="inputAlert">
		
</div>
  </body>
</html>