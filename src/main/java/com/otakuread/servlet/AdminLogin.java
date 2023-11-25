package com.otakuread.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminlogin")
public class AdminLogin extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String aname = req.getParameter("aname");
		String pass = req.getParameter("apass");
		
		String validName = "admin";
		String validPass = "root";
		if(validName.equals(aname) && validPass.equals(pass))
		{
			RequestDispatcher dispatcher = req.getRequestDispatcher("adminhome.jsp");
			dispatcher.include(req, resp);
		}
		else {
			req.setAttribute("err", "Invalid User Name Or Password");
			RequestDispatcher dispatcher = req.getRequestDispatcher("adminlogin.jsp");
			dispatcher.include(req, resp);
		}
	}
}
