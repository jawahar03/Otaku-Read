package com.otakuread.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.otakuread.dto.Book;

public class AdminDao 
{
	public Connection getConnection() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/otakuread?user=root&password=root");
		return con;
	}
	
	
	public void addBook(Book book) throws SQLException, ClassNotFoundException {
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("insert into products values(?,?,?,?,?,?)");
		pst.setInt(1, book.getBookId());
		pst.setString(2, book.getBookName());
		pst.setString(3, book.getBookDetails());
		pst.setInt(4, book.getBookPrice());
		pst.setString(5, book.getAuthor());
		pst.setString(6, book.isAvailable());
		
		 pst.executeUpdate();
	}
	
	public List<Book> getAllBook() throws SQLException, ClassNotFoundException
	{
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("select * from products");
		ResultSet rs = pst.executeQuery();
		
		List<Book> book = new ArrayList<Book>();
		while(rs.next())
		{
			Book b = new Book();
			b.setBookId(rs.getInt(1));
			b.setBookName(rs.getString(2));
			b.setBookDetails(rs.getString(3));
			b.setBookPrice(rs.getInt(4));
			b.setAuthor(rs.getString(5));
			b.setAvailable(rs.getString(6));
			
			book.add(b);
		}
		return book;
		
	}
	
	public List<Book> findBook(String name) throws SQLException,ClassNotFoundException
	{
		Connection  con = getConnection();
		PreparedStatement pst= con.prepareStatement("select * from products where prod_name like %?% or author like %?%");
		pst.setString(1, name);
		pst.setString(2, name);
		ResultSet rs = pst.executeQuery();
		
		List<Book> book = new ArrayList<Book>();
		while(rs.next())
		{
			Book b = new Book();
			b.setBookId(rs.getInt(1));
			b.setBookName(rs.getString(2));
			b.setBookDetails(rs.getString(3));
			b.setBookPrice(rs.getInt(4));
			b.setAuthor(rs.getString(5));
			b.setAvailable(rs.getString(6));
			
			book.add(b);
		}
		return book;
	}
	public ResultSet getImages(int id)throws SQLException,ClassNotFoundException
	{
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("select image from prodimg where img_id=?");
		pst.setInt(1, id);
		 return  pst.executeQuery();
	}
	
	public Book findById(int id) throws SQLException,ClassNotFoundException
	{
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("select * from products where prod_id = ?");
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		Book b= new Book();
		while(rs.next())
		{
			b.setBookId(rs.getInt(1));
			b.setBookName(rs.getString(2));
			b.setBookDetails(rs.getString(3));
			b.setBookPrice(rs.getInt(4));
			b.setAuthor(rs.getString(5));
			b.setAvailable(rs.getString(6));
		}
		return b;
	}
	
	
	public int updateBook(Book book) throws SQLException, ClassNotFoundException {
		
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("update products set prod_name=? , prod_detail=?, prod_price=?, author=?,available=? where prod_id=?");
		pst.setString(1, book.getBookName());
		pst.setString(2, book.getBookDetails());
		pst.setInt(3, book.getBookPrice());
		pst.setString(4, book.getAuthor());
		pst.setString(5, book.isAvailable());
		
		pst.setInt(6, book.getBookId());
		return pst.executeUpdate();
	}
	
	public void deleteBook(int id) throws SQLException, ClassNotFoundException
	{
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("delete from products where prod_id=?");
		pst.setInt(1, id);
		pst.executeUpdate();
		pst=con.prepareStatement("delete from prodimg where img_id=?");
		pst.setInt(1, id);
		pst.executeUpdate();
	}
	
	
	
	
	
	
	

}
