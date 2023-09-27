package com.otakuread.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otakuread.dao.UserDao;
import com.otakuread.dto.User;

@WebServlet("/useredit")
public class UserDetailsEdit extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email =(String) req.getAttribute("email");
		UserDao dao = new UserDao();
		try {
			User user = dao.findbyemail(email);
			req.setAttribute("user", user);
			RequestDispatcher dispatcher = req.getRequestDispatcher("useredit.jsp");
			dispatcher.include(req, resp);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
