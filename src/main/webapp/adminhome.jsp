<%@page import="java.util.Base64"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.otakuread.dto.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.otakuread.dao.AdminDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home</title>
<link rel="stylesheet" type="text/css" href="adminhome.css">
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
		<div id="orders">
			<a href="orders.jsp"><h2>Orders</h2></a>
		</div>
		<div id="logout">
			<a href="login.jsp">
				<h2>Logout</h2>
			</a>
		</div>
	</div>

	<div id="display_products">
		<table>
			<thead>
				<tr>
					<th>Manga Image</th>
					<th>Manga ID</th>
					<th>Manga name</th>
					<th>Manga Details</th>
					<th>Manga Price</th>
					<th>Manga Author</th>
					<th>Manga Available</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<%
				AdminDao dao = new AdminDao();
				List<Book> book = dao.getAllBook();
				%>
				<%
				for (Book b : book) {
				%>
				<tr>
					<%
					ResultSet rs = dao.getImages(b.getBookId());
					%>
					<%
					rs.next();
					%>

					<%
					Blob imgBlob = rs.getBlob(1);
					%>
					<%
					byte[] imgBytes = imgBlob.getBytes(1, (int) imgBlob.length());
					%>
					<%
					String img = new String(Base64.getEncoder().encode(imgBytes));
					%>

					<td><img src="data:image/jpeg;base64,<%=img%>" alt="image"
						height="15px" width="15px"></td>

					<td><%=b.getBookId()%></td>
					<td><%=b.getBookName()%></td>
					<td><%=b.getBookDetails()%></td>
					<td><%=b.getBookPrice()%></td>
					<td><%=b.getAuthor()%></td>
					<td><%=b.isAvailable()%></td>
					<td><a href="edit?bookid=<%=b.getBookId()%>">Edit</a></td>
					<td><a href="delete?bookid=<%=b.getBookId()%>">Delete</a></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>