package com.otakuread.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.otakuread.dao.BuyDao;

@WebServlet("/delivered")
public class Delivered extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		BuyDao dao = new BuyDao();
		String status = "delivered";
		try {
//			HttpSession ses = req.getSession();
//			String email = (String) ses.getAttribute("email");
//			String name =(String) req.getParameter("name");
			String email = req.getParameter("email");
			String name = req.getParameter("name");
			dao.updateStatus(status, name, email);
			RequestDispatcher dispatcher = req.getRequestDispatcher("orders.jsp");
			dispatcher.include(req, resp);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
