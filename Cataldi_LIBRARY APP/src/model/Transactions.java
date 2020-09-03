package model;

import java.sql.Date;

public class Transactions 
{
	private String bookTitle, isbn, author, price, id;
	private String date;
	
	public Transactions(String date, String bookTitle, String isbn, String author, String price, String id) 
	{
		this.date = date;
		this.bookTitle = bookTitle;
		this.isbn = isbn;
		this.author = author;
        this.price = price;
        this.id = id;
	}
	public Transactions(){}

	public String getTitle() 
	{
		return bookTitle;
	}
	public String getIsbn() 
	{
		return isbn;
	}
	public String getAuthor() 
	{
		return author;
	}
	public String getPrice()
    {
        return price;
    }
	public String getId()
    {
        return id;
    }
	public String getDate()
    {
        return date;
    }

	public void setBookTitle(String bookTitle) 
	{
		this.bookTitle = bookTitle;
	}
	public void setIsbn(String isbn) 
	{
		this.isbn = isbn;
	}
	public void setAuthor(String author) 
	{
		this.author = author;
	}
	public void setPrice(String price) 
	{
		this.price = price;
	}
	public void setId(String id) 
	{
		this.id = id;
	}
	public void setDate(String date) 
	{
		this.date = date;
	}

	public String toString() 
	{
		return date + bookTitle + isbn + author + price;
	}	
}