package com.otakuread.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otakuread.dao.AdminDao;

@WebServlet("/delete")
public class DeleteBook extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt( req.getParameter("bookid"));
		
		AdminDao dao = new AdminDao();
		try {
			dao.deleteBook(id);
			RequestDispatcher rd = req.getRequestDispatcher("adminhome.jsp");
			rd.include(req, resp);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
