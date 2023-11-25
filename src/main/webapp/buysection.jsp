<%@page import="java.sql.ResultSet"%>
<%@page import="com.otakuread.dto.User"%>
<%@page import="com.otakuread.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Place Order</title>
<link rel="stylesheet" type="text/css" href="buysection.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow&display=swap">
	<script src="https://kit.fontawesome.com/fe8b028a80.js" crossorigin="anonymous"></script>
</head>
<body>
<%UserDao dao = new UserDao();
	String email =(String) session.getAttribute("email");
	String upass = (String) session.getAttribute("pass");
	 if(email==null && upass==null)
		{
			request.setAttribute("invalid","It seems you don't have an account" );
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	ResultSet rs = dao.getTotalPrice(email);
	User user = dao.findbyemail(email);
%>
	
	<div id="order">
		<h2>Order Now</h2>
	</div>
	<br><br>
	<section id="ship-section" >
			<h3 id="ship">Shipping to, <%=user.getName() %>,, <%=user.getState() %>,<%=user.getCity()%>,<%=user.getPinCode() %> </h3>
		</section>
	<div id="details">
		
		<div class="column">
		<section>
			<%rs.next(); 
			  int total = rs.getInt(1);
			%>
			<h3 id="items">Items :</h3>
			<h3 id="delivery">Delivery :</h3>
			<h3 id="total">Total :</h3>
			<h3 id="discount">Discount : </h3>
			<h1 id="order-total">Order Total :</h1>
		</section>
		</div>
		<div class="column">
		<section>
			<h3 id="total"> <i class="fa-solid fa-indian-rupee-sign" style="color: #1a181e;"></i> <%=total %></h3>
			<%int del= 60; %>
			<h3 id="delivery-charge"> <i class="fa-solid fa-indian-rupee-sign" style="color: #1a181e;"></i> <%=del %></h3>
			<%int tot = total+del; %>
			<h3 id="total-del"> <i class="fa-solid fa-indian-rupee-sign" style="color: #1a181e;"></i> <%=tot %></h3>
			<h3 id="dis-amount"> <i class="fa-solid fa-indian-rupee-sign" style="color: #1a181e;"></i> -30</h3>
			<%int finalTotal = tot-30; %>
			<h1 id="final-total"> <i class="fa-solid fa-indian-rupee-sign" style="color: #1a181e;"></i> <%=finalTotal %></h1>
		</section>
		</div>
	</div>
		<div id="place-order">
			<a href="buy?email=<%=email %>&tot=<%=finalTotal%>"> <i class="fa-solid fa-truck-fast" style="color: #ffffff;"></i> Place your order</a>
		</div>
</body>
</html>