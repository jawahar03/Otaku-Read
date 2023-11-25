package com.otakuread.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.otakuread.dao.AdminDao;
import com.otakuread.dto.Book;


@WebServlet("/addproduct")
@MultipartConfig(
		fileSizeThreshold =  1024 * 1024,
		maxFileSize =  1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 50
		)

public class AddProduct extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id =Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String details = req.getParameter("details");
		int price =Integer.parseInt(req.getParameter("price"));
		String author = req.getParameter("author");
		String available = req.getParameter("available");
		
		Part part =req.getPart("img");
		
		Book b = new Book();
		b.setBookId(id);
		b.setBookName(name);
		b.setBookDetails(details);
		b.setBookPrice(price);
		b.setAuthor(author);
		b.setAvailable(available);
		
		AdminDao dao = new AdminDao();
		try {
			dao.addBook(b);
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/otakuread?user=root&password=root");
			PreparedStatement pst = con.prepareStatement("insert into prodimg values(?,?)");
			
			pst.setBlob(2, part.getInputStream());
			pst.setInt(1, id);
			pst.executeUpdate();
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("adminhome.jsp");
	 		dispatcher.include(req, resp);
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
