<%@page import="com.otakuread.dao.BuyDao"%>
<%@page import="com.otakuread.dto.Buy"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Orders</title>
<link rel="stylesheet" type="text/css" href="orders.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow&display=swap">
	<script src="https://kit.fontawesome.com/fe8b028a80.js" crossorigin="anonymous"></script>
</head>
<body>
	<div id="nav_modules">
    <div id="home">
        <a href="adminhome.jsp"><h2>Otaku Read</h2></a>
    </div>

    <div id="add_product">
        <a href="addproduct.jsp"><h2>Add Product</h2></a>
    </div>
    <div id="order">
        <a href="orders.jsp"><h2>Orders</h2></a>
    </div>
    <div id="logout">
        <a href="">
            <h2>Logout</h2>
        </a>
    </div>
</div>

<div id="orders">
    <table>
        <thead>
            <tr>
                <th>Email</th>
                <th>Book Name</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>State</th>
                <th>City</th>
                <th>Pincode</th>
                <th>Ordered Date</th>
                <th>Delivery Date</th>
                <th>Status</th>
                <th>Cancel order</th>
                <th>Delivered</th>
            </tr>
        </thead>

        <tbody>
            <% BuyDao dao = new BuyDao(); List<Buy> buy = dao.getOrders(); %>
            <% for (Buy b : buy) { %>
            <tr>
                <td><%=b.getEmail()%></td>
                <td><%=b.getBookName()%></td>
                <td><%=b.getQuantity()%></td>
                <td><%=b.getTotal()%></td>
                <td><%=b.getState()%></td>
                <td><%=b.getCity()%></td>
                <td><%=b.getPincode()%></td>
                <td><%=b.getOrderDate()%></td>
                <td><%=b.getDeliveryDate()%></td>
                <td><%=b.getStatus() %></td>
          
                <td><a href="cancel?name=<%=b.getBookName()%>&email=<%=b.getEmail()%>">Cancel</a></td>
                <td><a href="delivered?name=<%=b.getBookName()%>&email=<%=b.getEmail()%>">Delivered</a></td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>


</body>
</html>