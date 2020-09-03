package model;

public class BookList
{
	private Book bookCover;

	public BookList()
	{
		bookCover = null;
	}
	public void insertBook(String title, String isbn, String author, boolean checkedOut) 
	{
		Book newBook = new Book(title, isbn, author, false);
		newBook.setNext(bookCover);
		bookCover = newBook;
	}	
	public Book findByIsbn(String isbn) 
	{
		Book current = bookCover; 
		while(current != null)
		{
			if (current.getIsbn().compareTo(isbn) == 0)
			{
				break;
			}
			current = current.getNext();
		}
		return current;
	}
	public Book findByAuthor(String author) 
	{
		Book current = bookCover; 
		while(current != null)
		{
			if (current.getAuthor().compareTo(author) == 0)
			{
				break;
			}
			current = current.getNext();
		}
		return current;
	}
	public Book findByPriceRange(String priceRange) 
	{
		Book current = bookCover; 
		while(current != null)
		{
			if (current.getPrice().compareTo(priceRange) == 0)
			{
				break;
			}
			current = current.getNext();
		}
		return current;
	}
	public Book findByTitle(String title) 
	{
		Book current = bookCover; 
		while(current != null)
		{
			if (current.getTitle().compareTo(title) == 0)
			{
				break;
			}
			current = current.getNext();
		}
		return current;
	}
	public Book removeByIsbn(String isbn)  
	{
		Book current = bookCover;
		Book previous = bookCover;
		
		while(current.getIsbn().compareTo(isbn) != 0)
		{
			if(current.getNext() == null)
			{
				return null;
			}
			else
			{
				previous = current;
				current = current.getNext();
			}
		}
		if(current == bookCover) 
		{
			bookCover = bookCover.getNext();
		}
		else
		{
			previous.setNext(current.getNext()); 
		}
		return current;
	}
	public void display() 
	{
		Book current = bookCover;
		while(current != null)
		{
			current.display();
			current = current.getNext();
		}
		System.out.println();
	}
}