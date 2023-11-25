package com.otakuread.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.otakuread.dao.UserDao;
import com.otakuread.dto.User;

@WebServlet("/login")
public class Login extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		UserDao userDao = new UserDao();
		try {
			User user = userDao.findbyemail(email);
			if(user.getPass().equals(pass))
			{
				HttpSession session = req.getSession();
				session.setAttribute("email", email);
				session.setAttribute("upass", pass);
				
				RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
				dispatcher.include(req, resp);
			}
			else {
				req.setAttribute("err", "Invalid User Name Or Password");
				RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
				dispatcher.include(req, resp);
			}
		}	
		catch(Exception e)
		{
			
		}
	}
}
