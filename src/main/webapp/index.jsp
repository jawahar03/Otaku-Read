<%@page import="java.util.Base64"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.otakuread.dto.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.otakuread.dao.AdminDao"%>
<html>
<head>
<title>Otaku Read</title>
	<link rel="stylesheet" type="text/css" href="ind.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow&display=swap">
	<script src="https://kit.fontawesome.com/fe8b028a80.js" crossorigin="anonymous"></script>
</head>
<body>
 
<div id="nav_modules">
	<div ><a href="index.jsp" > <i class="fa-solid fa-house" style="color: #1a181e;"></i> Home</a></div>
	<div ><a href="account.jsp"> <i class="fa-solid fa-user" style="color: #1a181e;"></i> Account</a></div>
	<div ><a href="Cart.jsp"> <i class="fa-solid fa-cart-shopping"></i> My Cart</a></div>
	<div ><a href="myorders.jsp"> <i class="fa-solid fa-box-open" style="color: #1a181e;"></i> My Orders</a></div>
	<div ><a href="logout"> <i class="fa-regular fa-circle-left" style="color: #1a181e;"></i> Logout</a></div>
</div>	
<div id="img">
	<img alt="" src="zoro.png" style="width: 98.5vw; height: 90vh;">
</div>


<%AdminDao dao = new AdminDao();  
List<Book> book = dao.getAllBook();
String email = (String)session.getAttribute("email");
%>
<div id="body">
	<%String msg =(String) request.getAttribute("added"); %>
	<%if(msg != null  ){ %>
	<h1><%=msg%></h1>
	<%} %>
	<%String placed =(String) request.getAttribute("placed"); %>
	<%if(placed != null  ){ %>
	<h1><%=placed%></h1>
	<%} %>

	<% for(Book b : book){ %>
	<%ResultSet rs = dao.getImages(b.getBookId()); %>
<%rs.next(); %>

<%int id = b.getBookId(); %>
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
            <h1 id="name"><%=b.getBookName() %></h1>
            <h4 id="author"><%=b.getAuthor() %></h4>
            <p id="details"><%=b.getBookDetails() %>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </p>
            <h4 id="price"><i class="fa-solid fa-indian-rupee-sign" style="color: #1a181e;"></i><%=  b.getBookPrice() %></h4>
            <%if(b.isAvailable().equals("no")){ %>
                <h2 id="avail">Out Of Stock</h2>
            <%} %>
            <a href="cart?bookid=<%=b.getBookId() %>" class="action-button">Add To Cart</a>
            <a href="buy?email=<%=email %>" class="action-button">Buy</a>
        </div>
    </div>
</div>
<br>
	
<%}
%>
</div>

</body>
</html>
