<%@page import="com.otakuread.dao.UserDao"%>
<%@page import="com.otakuread.dto.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit</title>
<link rel="stylesheet" type="text/css" href="signup.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow&display=swap">
	
	<script src="https://kit.fontawesome.com/fe8b028a80.js" crossorigin="anonymous"></script>
</head>
<body>
	<%String email = (String)request.getParameter("email");
	  UserDao  dao = new UserDao();
	  User user = dao.findbyemail(email);
	%>
	
	   	<div class="background-image">
	   <div class="container">
	   
    <div class="content">
      <form action="uedit" method="post">
        <div class="user-details">
          
          <div class="input-box">
            <span class="details">Name</span>
            <input type="text" placeholder="Enter your name" value=<%=user.getName() %> name="name" required>
          </div>
          
          <div class="input-box">
            <span class="details">Mobile Number</span>
            <input type="text" placeholder="Enter your mobile number" value=<%=user.getContact() %> name="contact" required>
          </div>
          
          <div class="input-box">
            <span class="details">Email</span>
            <input type="text" placeholder="Enter your Email" name="email"value=<%=user.getEmail() %> readonly="readonly" required>
          </div>
          
          <div class="input-box">
            <span class="details">Country</span>
            <input type="text" placeholder="Enter your Country" name="country" value=<%=user.getContry() %> required>
          </div>
          
          <div class="input-box">
            <span class="details">State</span>
            <input type="text" placeholder="Enter your State" name="state" value=<%=user.getState() %> required>
          </div>
          
          <div class="input-box">
            <span class="details">City</span>
            <input type="text" placeholder="Enter your city" name="city" value=<%=user.getCity() %> required>
          </div>
          <div class="input-box">
            <span class="details"> Postal Code</span>
            <input type="text" placeholder="Enter your Pin code" name="pincode" value=<%=user.getPinCode() %> required>
          </div>
          <div class="input-box">
            <span class="details"> Password</span>
            <input type="password" placeholder="Enter your password" name="pass" value=<%=user.getPass() %> required>
          </div>
        

        <div class="button">
          <input type="submit" value="Apply Changes">
       </div>
         </div>
         </form>
         </div>
       </div>
  </div>
</body>
</html>