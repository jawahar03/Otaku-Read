<%@page import="com.otakuread.dto.User"%>
<%@page import="com.otakuread.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account</title>
<link rel="stylesheet" type="text/css" href="account.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow&display=swap">
	
	<script src="https://kit.fontawesome.com/fe8b028a80.js" crossorigin="anonymous"></script>
</head>
<body>
	<%HttpSession httpSession = request.getSession();
	String email = (String) httpSession.getAttribute("email");
	String upass = (String) httpSession.getAttribute("upass");
	if(upass==null && email==null )
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.include(request, response);
	%>
		
	<%} else{%>
	
		<%UserDao dao = new UserDao();
		 User user =  dao.findbyemail(email);
		%>
		<div class="background-image">
		<div class="user-details">
		
		<h3 class="item"> Name : <%=user.getName() %></h3>
		<h3 class="item">Mobile Number : <%=user.getContact() %></h3>
		<h3 class="item" id="mail">Email :<%=user.getEmail() %></h3>
		<h3 class="item" id="add">Address</h3>
		<h3 class="item">Country : <%=user.getContry() %></h3>
		<h3 class="item">State : <%=user.getState() %></h3>
		<h3 class="item">City : <%=user.getCity() %></h3>
		<h3 class="item">Pincode : <%=user.getPinCode() %></h3>
		
		<h2 class="item" id="but"><a href="useredit.jsp?email=<%=user.getEmail()%>"> <i class="fa-solid fa-pen-to-square" style="color: #f5f5f5;"></i> Edit Details</a></h2>
		</div>
		</div>
	<%} %>
	
</body>
</html>