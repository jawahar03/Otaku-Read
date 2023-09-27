package com.otakuread.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otakuread.dao.UserDao;

@WebServlet("/remove")
public class Remove extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id =Integer.parseInt( req.getParameter("id"));
		UserDao udao = new UserDao();
		try {
			ResultSet qua = udao.getQuantity(id);
			qua.next();
			int quantity = qua.getInt(1);
			if(quantity>1)
			{
				quantity-=1;
				udao.updateQuantity(id, quantity);
				RequestDispatcher rd = req.getRequestDispatcher("Cart.jsp");
				rd.forward(req, resp);
			}
			else {
				udao.deleteBook(id);
				RequestDispatcher rd = req.getRequestDispatcher("Cart.jsp");
				rd.forward(req, resp);
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
