package com.otakuread.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otakuread.dao.BuyDao;
import com.otakuread.dao.UserDao;
import com.otakuread.dto.Buy;
import com.otakuread.dto.Cart;
import com.otakuread.dto.User;

@WebServlet("/buy")
public class BuyLogic extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDao udao = new UserDao();
		Buy b = new Buy();
		String email = req.getParameter("email");
		int total = Integer.parseInt(req.getParameter("tot"));
		try {
			List<Cart> cart =  udao.getCart(email);
			User u = udao.findbyemail(email);
			BuyDao bdao= new  BuyDao();
			 LocalDate orderDate = LocalDate.now();

		        LocalDate deliveryDate = orderDate.plusDays(7);
	        
	       String status= "processing";
	        
			for(Cart c : cart)
			{
				int id = (int) (1 + Math.random() * (5000 - 1000 + 1));
				b.setOrderId(id);
				b.setEmail(c.getMail());
				b.setBookName(c.getBookName());
				b.setQuantity(c.getQuantity());
				b.setTotal(total);
				b.setState(u.getState());
				b.setCity(u.getCity());
				b.setPincode(u.getPinCode());
				b.setOrderDate(orderDate);
				b.setDeliveryDate(deliveryDate);
				b.setStatus(status);
				System.out.println("before");
				udao.deleteCart(c.getBookId(), email);
				System.out.println("after");
				bdao.addBuy(b);
				
				
			}
			
			
			req.setAttribute("placed", "Order placed successfully!");
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.include(req, resp);
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
