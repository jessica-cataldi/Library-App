package model;

public class Library
{
	private BookList bookList;
	private PatronBag patronBag;
	private TransactionBag transactionBag;
    
    public Library()
    {
    	bookList = new BookList();
    	patronBag = new PatronBag(LibraryInterface.PATRONBAG_MAXSIZE);
    	transactionBag = new TransactionBag(LibraryInterface.TRANSACTIONBAG_MAXSIZE);
    }

    public BookList getBookList()
    {
    	return bookList;
    }
    public void setBookList(BookList bookList)
    {
    	this.bookList = bookList;
    }

	public PatronBag getPatronBag() 
	{
		return patronBag;
	}  
	public void setPatronBag(PatronBag patronBag)
	{
		this.patronBag = patronBag;
	} 
	
	public TransactionBag getTransactionBag()
    {
    	return transactionBag;
    }
    public void seTransactionBag(TransactionBag transactionBag)
    {
    	this.transactionBag = transactionBag;
    }

}