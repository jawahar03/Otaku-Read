package com.otakuread.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otakuread.dao.UserDao;
import com.otakuread.dto.User;

@WebServlet("/uedit")
public class UserEdit extends HttpServlet
{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		long contact = Long.parseLong(req.getParameter("contact"));
		String email = req.getParameter("email");
		String country = req.getParameter("country");
		String state = req.getParameter("state");
		String city = req.getParameter("city");
		String pass = req.getParameter("pass");
		int pincode = Integer.parseInt(req.getParameter("pincode"));
		
		User user = new User();
		user.setName(name);
		user.setContact(contact);
		user.setEmail(email);
		user.setContry(country);
		user.setState(state);
		user.setCity(city);
		user.setPinCode(pincode);
		user.setPass(pass);
		
		try {
			UserDao dao = new UserDao();
			dao.updateUser(user);
			req.setAttribute("name", name);
			RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
			dispatcher.include(req, resp);
		}
		catch(Exception e)
		{
			
		}
	}
}
