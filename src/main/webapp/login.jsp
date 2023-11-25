<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
		<link rel="stylesheet" type="text/css" href="login.css">

		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow&display=swap">
</head>
<body>
<div class="background-image">
	<div class="container">
        <div class="title">Login</div>

 
        <% String name = (String)request.getAttribute("name"); %>
        <% if(name!=null){ %>
            <h1>Hello <%= name %> </h1>
        <% } %>
        
        <% String err = (String)request.getAttribute("err"); %>
        <% if(err!=null){ %>
            <h1><%= err %> </h1>
        <% } %> 
        <% String invalid = (String)request.getAttribute("invalid"); %>
        <% if(invalid!=null){ %>
            <h1><%= invalid %> </h1>
        <% } %> 

 				
        <form action="login" method="post">
            <div class="user-details">
            <div class="input-box">
                <span class="details">Email</span>
                <input type="text" placeholder="Enter your Email" name="email" required>
              </div>
              <div class="input-box">
                <span class="details"> Password</span>
                <input type="password" placeholder="Enter your password" name="pass" required>
              </div>
            </div>
              <div class="button">
                <input type="submit" value="Login">
              </div>
              <span> <a href="signup.jsp">Dont have a account, Click here</a> </span>
              <span> <a href="adminlogin.jsp">Click here to login as a admin</a></span>
        </form>
    </div>
    </div>
	
	<script src="https://kit.fontawesome.com/fe8b028a80.js" crossorigin="anonymous"></script>
</body>
</html>