<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sign Up</title>
<link rel="stylesheet" type="text/css" href="signup.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow&display=swap">
	
	<script src="https://kit.fontawesome.com/fe8b028a80.js" crossorigin="anonymous"></script>
</head>
<body>
	<div class="background-image">
	
	<div class="container">
    <div class="title">Sign-up</div>
   	
   	<%String error = (String)request.getAttribute("error"); %> 
   	<%if(error!=null){ %>
   	<h1>User name "<%=error %>" is already exists.</h1>
   	<%} %>
   	
    <div class="content">
      <form action="signup" method="post">
        <div class="user-details">
          
          <div class="input-box">
            <span class="details">Name</span>
            <input type="text" placeholder="Enter your name" name="name" required>
          </div>
          
          <div class="input-box">
            <span class="details">Mobile Number</span>
            <input type="text" placeholder="Enter your mobile number" name="contact" required>
          </div>
          
          <div class="input-box">
            <span class="details">Email</span>
            <input type="text" placeholder="Enter your Email" name="email" required>
          </div>
          
          <div class="input-box">
            <span class="details">Country</span>
            <input type="text" placeholder="Enter your Country" name="country" required>
          </div>
          
          <div class="input-box">
            <span class="details">State</span>
            <input type="text" placeholder="Enter your State" name="state" required>
          </div>
          
          <div class="input-box">
            <span class="details">City</span>
            <input type="text" placeholder="Enter your city" name="city" required>
          </div>
          <div class="input-box">
            <span class="details"> Postal Code</span>
            <input type="text" placeholder="Enter your Pin code" name="pincode" required>
          </div>
        
          
          <div class="input-box">
            <span class="details"> Password</span>
            <input type="password" placeholder="Enter your password" name="pass" required>
          </div>
         
       
	
        <div class="button">
          <input type="submit" value="Sign-up">
        </div>
         </div>
         </form>
         </div>
       </div>
  </div>
 
  
</body>
</html>