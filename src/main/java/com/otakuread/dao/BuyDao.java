package com.otakuread.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.otakuread.dto.Buy;

public class BuyDao 
{

	public Connection getConnection() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/otakuread?user=root&password=root");
		return con;
	}
	
	public void addBuy(Buy buy) throws SQLException, ClassNotFoundException
	{
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("insert into orders values(?,?,?,?,?,?,?,?,?,?,?)");
		pst.setInt(1, buy.getOrderId());
		pst.setString(2, buy.getEmail());
		pst.setString(3, buy.getBookName());
		pst.setInt(4, buy.getQuantity());
		pst.setInt(5, buy.getTotal());
		pst.setString(6, buy.getState());
		pst.setString(7, buy.getCity());
		pst.setInt(8, buy.getPincode());
		pst.setDate(9, java.sql.Date.valueOf(buy.getOrderDate()));
		pst.setDate(10, java.sql.Date.valueOf(buy.getDeliveryDate()));
		pst.setString(11, buy.getStatus());
		
		pst.executeUpdate();
	}
	public List<Buy> getOrders() throws SQLException, ClassNotFoundException
	{
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("select * from orders");
		ResultSet rs =  pst.executeQuery();
		List<Buy> buy = new ArrayList<Buy>();
		while(rs.next())
		{
			Buy b = new Buy();
			b.setOrderId(rs.getInt(1));
			b.setEmail(rs.getString(2));
			b.setBookName(rs.getString(3));
			b.setQuantity(rs.getInt(4));
			b.setTotal(rs.getInt(5));
			b.setState(rs.getString(6));
			b.setCity(rs.getString(7));
			b.setPincode(rs.getInt(8));
			Date date = rs.getDate(9);
			LocalDate orderDate = date.toLocalDate();
			b.setOrderDate(orderDate);
			Date date1 = rs.getDate(10);
			LocalDate deliveryDate = date1.toLocalDate();
			b.setDeliveryDate(deliveryDate);
			b.setStatus(rs.getString(11));
			
			buy.add(b);
		}
		return buy;
	}
	public List<Buy> getUserOrders(String mail) throws SQLException, ClassNotFoundException
	{
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("select * from orders where email = ?");
		pst.setString(1, mail);
		ResultSet rs =  pst.executeQuery();
		List<Buy> buy = new ArrayList<Buy>();
		while(rs.next())
		{
			Buy b = new Buy();
			b.setOrderId(rs.getInt(1));
			b.setEmail(rs.getString(2));
			b.setBookName(rs.getString(3));
			b.setQuantity(rs.getInt(4));
			b.setTotal(rs.getInt(5));
			b.setState(rs.getString(6));
			b.setCity(rs.getString(7));
			b.setPincode(rs.getInt(8));
			Date date = rs.getDate(9);
			LocalDate orderDate = date.toLocalDate();
			b.setOrderDate(orderDate);
			Date date1 = rs.getDate(10);
			LocalDate deliveryDate = date1.toLocalDate();
			b.setDeliveryDate(deliveryDate);
			b.setStatus(rs.getString(11));
			
			buy.add(b);
		}
		return buy; 
	}
	
	public void updateStatus(String status , String name , String email)throws SQLException, ClassNotFoundException
	{
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("update orders set status=? where email=? and book_name=?");
		pst.setString(1, status);
		pst.setString(2, email);
		pst.setString(3, name);
		pst.executeUpdate();

	}
	
	
	
	
	
	
	
	
	
	
	
}
