<%@page import="java.util.Base64"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.otakuread.dao.UserDao"%>
<%@page import="com.otakuread.dto.Cart"%>
<%@page import="java.util.List"%>
<%@page import="com.otakuread.dao.AdminDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
<link rel="stylesheet" type="text/css" href="cart.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow&display=swap">
<script src="https://kit.fontawesome.com/fe8b028a80.js" crossorigin="anonymous"></script>
</head>
<body>
	<%AdminDao dao = new AdminDao();
	  HttpSession s = request.getSession();
	  String email =(String) s.getAttribute("email");
	  String upass =(String) s.getAttribute("pass");
	  if(email==null && upass==null)
		{
			request.setAttribute("invalid","It seems you don't have an account" );
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	   UserDao udao = new UserDao();
	   List<Cart> cart = udao.getCart(email);
   %>
   <div id="nav_modules">
   	<div ><a href="index.jsp" > <i class="fa-solid fa-house" style="color: #1a181e;"></i> Home</a></div>
	<div ><a href="account.jsp"> <i class="fa-solid fa-user" style="color: #1a181e;"></i> Account</a></div>
	<div ><a href="Cart.jsp"> <i class="fa-solid fa-cart-shopping"></i> My Cart</a></div>
	<div ><a href="myorders.jsp"> <i class="fa-solid fa-box-open" style="color: #1a181e;"></i> My Orders</a></div>
	<div ><a href="logout"> <i class="fa-regular fa-circle-left" style="color: #1a181e;"></i> Logout</a></div>
</div>	
<br>

	<div id="body">
   <div id="total">
		
		<%ResultSet price = udao.getTotalPrice(email);
			if(price.next()){%>
				<h1>Total Amount : <i class="fa-solid fa-indian-rupee-sign" style="color: #f5f5f5;"></i> <%=price.getInt(1) %></h1>
			<%} %>
		</div>
		<br>
		
	<% for(Cart c : cart){ %>
	
	<%ResultSet rs = dao.getImages(c.getBookId()); %>
	<%int id = c.getBookId();
	  
	%>
	<%rs.next(); %>
	
	
<%Blob imgBlob = rs.getBlob(1); %>	
<%byte[] imgBytes = imgBlob.getBytes(1, (int) imgBlob.length()); %>
<%String img = new String(Base64.getEncoder().encode(imgBytes)); %>


	<div class="content-wrapper">
    <div class="left-column">
        <div id="Cover_img">
            <img src="data:image/jpeg;base64,<%=img %>" alt="image" >
        </div>
    </div>
		    
	<div class="right-column">
		<div id="manga_details">
			<h1 id="name"><%=c.getBookName() %></h1>
			<h2 id="author"><%=c.getAuthor() %></h2>
			<h3 id="pirce"><%=c.getPrice() %></h3>
			<h4 id="quantity">Quantity <%=c.getQuantity() %> </h4>
			<h4 id="remove"><a id="a" href="remove?id=<%=c.getBookId() %>"> <i class="fa-solid fa-trash-can" style="color: #1a181e;"></i> Remove from cart</a></h4>
		</div>
		</div>
		</div>
	<br>
	<%}  %>
	
		<br><br>
		<div id="buy">
			<a  href="buysection.jsp" style="color: #f5f5f5"> <i class="fa-solid fa-box" style="color: #f5f5f5;"></i> Proceed to buy</a>
		</div>
		</div>
		
</body>
</html>