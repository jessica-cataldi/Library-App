package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Book implements Serializable
{
	private String bookTitle, isbn, author, price;
	private Book next;
    private boolean checkedOut;
    @SuppressWarnings("unused")
	private static double priceCounter = 0.00;

	
	public Book(String bookTitle, String isbn, String author, boolean checkedOut) 
	{
		this.bookTitle = bookTitle;
		this.isbn = isbn;
		this.author = author;
        this.price = String.valueOf(String.format("%.2f", priceCounter = (Math.random() * 99.99)));       
        this.checkedOut = checkedOut;
		next = null;
	}
	public Book() {}

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
	public Book getNext()
	{
		return next;
	}
	 public boolean getCheckedOut()
	    {
	        return checkedOut;
	    }

	public void setBookTitle(String bookTitle) 
	{
		this.bookTitle = bookTitle;
	}
	 public void setCheckedOut(boolean checkedOut)
	    {
	        this.checkedOut = checkedOut;
	    }
	public void setIsbn(String isbn) 
	{
		this.isbn = isbn;
	}
	public void setAuthor(String author) 
	{
		this.author = author;
	}
	public void setNext(Book next)
	{
		this.next = next;
	}

	public void display()
	{
		System.out.println(bookTitle + isbn + author);
	}
	public String toString() 
	{
		return "Book: " + bookTitle + isbn + author;
	}	
}