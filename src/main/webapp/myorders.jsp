<%@page import="com.otakuread.dto.User"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.otakuread.dao.UserDao"%>
<%@page import="com.otakuread.dto.Buy"%>
<%@page import="java.util.List"%>
<%@page import="com.otakuread.dao.BuyDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Orders</title>
<link rel="stylesheet" type="text/css" href="myorders.css">
<script src="https://kit.fontawesome.com/fe8b028a80.js" crossorigin="anonymous"></script>
</head>
<body>
<%
	String email =(String) session.getAttribute("email");
	String upass = (String) session.getAttribute("pass");
	 if(email==null && upass==null)
		{
			request.setAttribute("invalid","It seems you don't have an account" );
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	
%>
	<div id="orders">
		<table>
			<thead>
				<tr>
					<th>Book Name</th>
					<th>Quantity</th>
					<th>Total</th>
					<th>State</th>
					<th>City</th>
					<th>Pincode</th>
					<th>Ordered Date</th>
					<th>Delivery Date</th>
					<th>Status</th>
				</tr>
			</thead>

			<tbody>
				<%
				BuyDao dao = new BuyDao();
				String mail =(String) session.getAttribute("email");
				List<Buy> buy = dao.getUserOrders(mail);
				%>
				<%
				for (Buy b : buy) {
				%>
				<tr>
					<td><%=b.getBookName()%></td>
					<td><%=b.getQuantity()%></td>
					<td><%=b.getTotal()%></td>
					<td><%=b.getState()%></td>
					<td><%=b.getCity()%></td>
					<td><%=b.getPincode()%></td>
					<td><%=b.getOrderDate()%></td>
					<td><%=b.getDeliveryDate()%></td>
					<td><%=b.getStatus() %></td>
				</tr>
				<%
				}
				%>

			</tbody>
		</table>
	</div>
	
</body>
</html>