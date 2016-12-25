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
 <!--
	<script type="text/javascript">
	
 	window.onload = loadUser();
 	
 	function loadUser() {
 		$.get("DisplayAllUser", function(responseJson) {          // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response JSON...
 	        var $table = $("<table>").appendTo($("#tableDiv")); // Create HTML <table> element and append it to HTML DOM element with ID "somediv".
 	       window.alert(responseJson);
 	        $.each(responseJson, function(index, user) {    // Iterate over the JSON array.
 	        	window.alert("loop");
 	            $("<tr>").appendTo($table)                     // Create HTML <tr> element, set its text content with currently iterated item and append it to the <table>.
 	                .append($("<td>").text(user.email))        // Create HTML <td> element, set its text content with id of currently iterated product and append it to the <tr>.
 	                .append($("<td>").text(user.password))      // Create HTML <td> element, set its text content with name of currently iterated product and append it to the <tr>.
 	                .append($("<td>").text(user.roles));    // Create HTML <td> element, set its text content with price of currently iterated product and append it to the <tr>.
 	        });
 	    });
 		
 		
 		
 		
 		
 		
 		
 		
 		window.alert("loadUser");
 	}
 	</script>
  -->
 
 	<script type="text/javascript">
	$(document).ready(function() {
		
		
		$("#reset").click(function() {
			var newPassword = $("#newPassword").val();
			if(newPassword == ""){
				$("#inputAlert").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button><strong>Please enter new password</strong></div>");
			} else{
				$("#inputAlert").html("<div class='alert alert-success alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button><strong>Password has been changed</strong></div>");

			}
			
		});
		
		$.get( "DisplayAllUser", function( data ) {
	        //Do your logic with the response
	        if(data == "myvalue"){
	            $("p").css("display", "none");
	         }

	    });
		
		
		$.getJSON( "DisplayAllUser", function( data ) {
		     
		   // var json = JSON.parse(data);
	       // ArrayList<String> emailList = new ArrayList<String>(); 
	       // for (var email in json){
	       // emailList.add(email);
	       //	console.log(email + "=" + json[email]);
	       //}
	      var items = []; 
	      $.each( data, function( key, val ) {
	    	  items.push("<li id='" + key + "'>" + val + "</li>") ; 
	    	  }); 
		
		$( "<ul/>" , {
			"class" : "my-new-list", 
			html: items.join("")
     		}).appendTo( "body" ); 
		}); 

</script>
 
  </head>
<body>
    
   <nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Ulink Assist</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      <li><a href="index.html">Home</a></li>
        <li><a href="upload.html">Upload Templates</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Screenings <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="addScreening.html">Add Screenings</a></li>
            <li><a href="viewScreenings.html">View Screenings</a></li>
          </ul>
        </li> 
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Analysis <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">KPI</a></li>
              <li><a href="#">Ranking</a></li>
            <li><a href="#">Gender and Age Report</a></li>
          </ul>
        </li>
        
        
      </ul>
      <form class="navbar-form navbar-right">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="login.html">Logout</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      	<li class="active"><a href="accountManagement.html">Account Management <span class="sr-only">(current)</span></a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>


	<div class="col-md-6 col-md-offset-3">
	<div class="panel panel-default">
	  <div class="panel-body">
	 	
	   <div align="center"><h3><b>You are currently logged in as Admin </b></h3><br>
	   <button type="button" class="btn btn-default"><a href="createAccount.html"> Create new account </a></button>
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
    			
    		  		out.println(user.getEmail());
    		  		
    		  		out.println("</td>");
    		  		
    		  		%>
    		  		
    		  		<td>
			<form class="form-inline">
			  <div class="form-group">
			    <input type="text" class="form-control" id="newPassword" placeholder="New Password">
			  </div>
			  <button type="button" class="btn btn-default" id="reset">Reset</button>
			
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
			        <button type="button" class="btn btn-primary">Yes</button>
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
		
		<div class="col-md-8 col-md-offset-2" id="inputAlert"></div>
		

	

  </body>
</html>