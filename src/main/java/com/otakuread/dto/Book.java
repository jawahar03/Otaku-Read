package com.otakuread.dto;


public class Book 
{
	private int bookId;
	private String bookName;
	private String bookDetails;
	private int bookPrice;
	private String author;
	private String available;
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookDetails() {
		return bookDetails;
	}
	public void setBookDetails(String bookDetails) {
		this.bookDetails = bookDetails;
	}
	public int getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}
 
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String isAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	
}	
