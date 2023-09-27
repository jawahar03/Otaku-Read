package com.otakuread.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.otakuread.dao.AdminDao;
import com.otakuread.dao.UserDao;
import com.otakuread.dto.Book;
import com.otakuread.dto.Cart;

@WebServlet("/cart")
public class AddToCart extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");
		String upass = (String) session.getAttribute("upass");
		if(email==null && upass==null)
		{
			req.setAttribute("invalid","It seems you don't have an account" );
			RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
			dispatcher.forward(req, resp);
		}
		
		try {
			AdminDao dao = new AdminDao();
			UserDao udao = new UserDao();
			List<Cart> cart = udao.getCart(email);
			
			int bookid = Integer.parseInt(req.getParameter("bookid"));
			boolean bookFound = false;
			for(Cart c : cart)
			{
				if(bookid == c.getBookId())
				{
					bookFound = true;
				}
			}
			
			if(bookFound)
			{
				ResultSet q = udao.getQuantity(bookid);
				if(q.next()) {
				int quantity = q.getInt(1);
				quantity++;
				udao.updateQuantity(bookid, quantity);
				req.setAttribute("added", "Book already in cart.. Quantity increased.");
				RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
				dispatcher.include(req, resp);
				}
			}
			 else {
				 Book book =  dao.findById(bookid);
					int res = udao.addToCart(book, email);
					if(res==1)
					{
						req.setAttribute("added", "Book added to cart");
						RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
						dispatcher.include(req, resp);
						
					}
			 }
		}
		catch(Exception e)
		{
			
		}
		
	}
}
