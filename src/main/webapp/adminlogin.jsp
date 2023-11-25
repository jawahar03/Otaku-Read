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
	<h1>Welcome Admin</h1>

	<%String err = (String) request.getAttribute("err"); %>
	<%if(err!=null){ %>
		<h1><%=err %></h1>
	<%} %>

	<form action="adminlogin" method="post">
            <div class="user-details">
            <div class="input-box">
                <span class="details">Name</span>
                <input type="text" placeholder="Enter admin name" name="aname" required>
              </div>
              <div class="input-box">
                <span class="details"> Password</span>
                <input type="password" placeholder="Enter admin password" name="apass" required>
              </div>
            </div>
              <div class="button">
                <input type="submit" value="Login">
              </div>
        </form>
        </div>
    </div>
</body>
</html>