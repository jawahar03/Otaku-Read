package com.otakuread.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

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
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
			User user = udao.findbyemail(email);
			BuyDao buy = new BuyDao();
			List<Buy> b1 = buy.getUserOrders(email);
			
			String emailContent = 
			        "Dear " + user.getName() + ",\n\n" +
			        "Thank you for your recent purchase from Otaku Read. " +
			        "We are delighted to have you as a valued customer. Your order details are as follows:\n\n";

			for (Buy purchase : b1) {
			
			    emailContent += "Book Title: " + purchase.getBookName() + "\n";
			    emailContent += "Total: ₹" + purchase.getTotal() + "\n";
			    emailContent += "Delivery Date: ₹" + purchase.getDeliveryDate() + "\n";
			    emailContent += "\n"; 
			    
			  }

			emailContent +=
			        "Payment Method: Cash On Delivery\n\n" +
			        "Shipping Address:\n" +
			        user.getState()+user.getCity()+user.getPinCode() +"\n\n" +
			        "If you have any questions or need assistance, please do not hesitate to contact our customer support team at readwithotaku@gmail.com.\n\n" +
			        "We hope you enjoy your new book. Happy reading!\n\n" +
			        "Best regards,\n" +
			        "Otaku Read\n" +
			        "Otakuread.com";
			
			Properties properties = new Properties();
			 properties.put("mail.smtp.auth", "true");
		     properties.put("mail.smtp.starttls.enable", "true");
		     properties.put("mail.smtp.host", "smtp.gmail.com");
		     properties.put("mail.smtp.port", "587");
		     
		     Session session = Session.getInstance(properties, new Authenticator() {
		    	 @Override
		            protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication("readwithotaku@gmail.com", "ztdrwbltrsjfvxxk");
		            }	
			});
		     
		     try {
		            Message message = new MimeMessage(session);
		            message.setFrom(new InternetAddress("readwithotaku@gmail.com"));
		            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		            message.setSubject("Thank You for Your Purchase");
		            message.setText(emailContent);

		            Transport.send(message);

		        } catch (MessagingException e) {
		            e.printStackTrace();
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
