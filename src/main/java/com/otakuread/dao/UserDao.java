package com.otakuread.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.otakuread.dto.Book;
import com.otakuread.dto.Cart;
import com.otakuread.dto.User;

public class UserDao 
{
	
	public Connection getConnection() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/otakuread?user=root&password=root");
		return con;
	}
	public User findbyemail(String email) throws ClassNotFoundException, SQLException
	{

		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("select * from user where email=?");
		pst.setString(1, email);
		ResultSet rs = pst.executeQuery();
		User user = new User();
		while(rs.next())
		{
			user.setName(rs.getString(1));
			user.setContact(rs.getLong(2));
			user.setEmail(rs.getString(3));
			user.setPass(rs.getString(4));
			user.setContry(rs.getString(5));
			user.setState(rs.getString(6));
			user.setCity(rs.getString(7));
			user.setPinCode(rs.getInt(8));
			
		}
		
		return user;
	}
	
	public void addUser(User user) throws SQLException, ClassNotFoundException
	{
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("insert into user values(?,?,?,?,?,?,?,?)");
		pst.setString(1, user.getName());
		pst.setLong(2, user.getContact());
		pst.setString(3, user.getEmail());
		pst.setString(4, user.getPass());
		pst.setString(5, user.getContry());
		pst.setString(6, user.getState());
		pst.setString(7, user.getCity());
		pst.setInt(8, user.getPinCode());

		pst.executeUpdate();
	}
	
	public void updateUser(User user) throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("update user set uname=? , contact=? ,  password=?, country=?, state=?, city=?, postal_code=?  where email=?");
		pst.setString(1, user.getName());
		pst.setLong(2, user.getContact());
		pst.setString(3, user.getPass());
		pst.setString(4, user.getContry());
		pst.setString(5, user.getState());
		pst.setString(6, user.getCity());
		pst.setInt(7, user.getPinCode());

		pst.setString(8, user.getEmail());
		pst.executeUpdate();
		
	}
	
	
	
	public int addToCart(Book book , String email) throws ClassNotFoundException, SQLException
	{
		int bookId = book.getBookId();		
		String userEmail = email;
		String bookName = book.getBookName();
		int price = book.getBookPrice();
		String author  = book.getAuthor();
		int quantity = 1;
		Connection con = getConnection();
		int sno = (int) (1 + Math.random() * (500 - 1 + 1));
		PreparedStatement pst  = con.prepareStatement("insert into cart values(?,?,?,?,?,?,?)");
		pst.setInt(1, sno);
		pst.setInt(2, bookId);
		pst.setString(3, userEmail);
		pst.setString(4, bookName);
		pst.setInt(5, price);
		pst.setString(6, author);
		pst.setInt(7, quantity);
		return pst.executeUpdate();
		
	}
	
	public List<Cart> getCart(String mail) throws SQLException, ClassNotFoundException
	{
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("select * from cart where mail = ?");
		pst.setString(1, mail);
		ResultSet rs =   pst.executeQuery();
		
		List<Cart> cart = new ArrayList<Cart>();
		while(rs.next())
		{
			Cart c = new  Cart();
			c.setSno(rs.getInt(1));
			c.setBookId(rs.getInt(2));
			c.setMail(rs.getString(3));
			c.setBookName(rs.getString(4));
			c.setPrice(rs.getInt(5));
			c.setAuthor(rs.getString(6));
			c.setQuantity(rs.getInt(7));
			cart.add(c);
		}
		return cart;
	}   
	
	public void updateQuantity(int id , int quantity) throws SQLException, ClassNotFoundException
	{
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("update cart set quantity = ? where book_id=?");
		pst.setInt(1, quantity);
		pst.setInt(2, id);
		pst.executeUpdate();
		
	}
	public ResultSet getQuantity(int id) throws SQLException, ClassNotFoundException
	{
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("select quantity from cart where book_id = ?");
		pst.setInt(1, id);
		ResultSet quantity = pst.executeQuery();
		return quantity;
	}
	public ResultSet getTotalPrice(String email)throws SQLException, ClassNotFoundException
	{
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("SELECT SUM(price * quantity) FROM cart WHERE mail = ?");
		
		pst.setString(1, email);
		ResultSet price = pst.executeQuery();
		return price;
	}
	public void deleteBook(int id) throws SQLException, ClassNotFoundException
	{
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("delete from cart where book_id=?");
		pst.setInt(1, id);
		pst.executeUpdate();
	}
	public void deleteCart(int id,String mail) throws SQLException, ClassNotFoundException
	{
		System.out.println("executed");
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("delete from cart where book_id=? and mail=?");
		pst.setInt(1, id);
		pst.setString(2, mail);
		pst.executeUpdate();
		System.out.println("Ended");
	}
	public void updateCart(int id, int quantity)throws SQLException, ClassNotFoundException
	{
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("update cart set quantity = ? where book_id=?");
		pst.setInt(1, quantity);
		pst.setInt(2, id);
		
		pst.executeUpdate();
		
		
	}
}
