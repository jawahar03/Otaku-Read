<%@page import="com.otakuread.dto.Book"%>
<%@page import="com.otakuread.dao.AdminDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit</title>
<link rel="stylesheet" type="text/css" href="addproduct.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow&display=swap">
	<script src="https://kit.fontawesome.com/fe8b028a80.js" crossorigin="anonymous"></script>
</head>
<body>
	<%Book b = (Book)request.getAttribute("books"); %>
	<div id=container>
	
	
	<form action="addproduct" method="post" enctype="multipart/form-data" id="product-form">
    <label for="id">Manga ID</label>
    <input type="text" id="id" name="id" placeholder="Enter Manga ID" value="<%=b.getBookId() %>" required>

    <label for="name">Manga Name</label>
    <input type="text" id="name" name="name" placeholder="Enter Manga Name" value="<%=b.getBookName() %>" required>

    <label for="details">Manga Details</label>
    <input type="text" id="details" name="details" placeholder="Enter Details About Manga" value="<%=b.getBookDetails() %>" required>

    <label for="price">Manga Price</label>
    <input type="text" id="price" name="price" placeholder="Enter Manga Price" value="<%=b.getBookPrice() %>" required>

    <label for="author">Manga Author Name</label>
    <input type="text" id="author" name="author" placeholder="Enter Manga Author Name"value="<%=b.getAuthor() %>" required>

    <div class="availability">
        <span>Availability:</span>
        <input type="radio" id="available-yes" name="available" value="Yes" required>
        <label for="available-yes">Yes</label>
        <input type="radio" id="available-no" name="available" value="No" required>
        <label for="available-no">No</label>
    </div>

    <label for="img">Manga Cover Image</label>
    <input type="file" id="img" name="img" required>

    <input type="submit" value="Submit">
</form>
</div>
</body>
</html>