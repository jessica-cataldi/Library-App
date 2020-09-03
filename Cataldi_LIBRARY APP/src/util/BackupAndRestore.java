package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.Library;
import model.Patron;
import model.PatronBag;

public class BackupAndRestore 
{
	Library library;
	
	public BackupAndRestore (Library library) 
	{
		this.library = library;
	}
	
	public void backup()
	{
        try
        {
            FileOutputStream fos1 = new FileOutputStream("patron.dat", true);
            ObjectOutputStream oos1 = new ObjectOutputStream(fos1);                   
            oos1.writeObject(library.getPatronBag());
            oos1.close();                    
            
            FileOutputStream fos2 = new FileOutputStream("librarian.dat", true);
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);                   
            //oos2.writeObject(library.getLibrarianBag());
            oos2.close();                                      

            FileOutputStream fos4 = new FileOutputStream("books.dat", true);
            ObjectOutputStream oos4 = new ObjectOutputStream(fos4);                   
            //oos4.writeObject(library.getBookList());
            oos4.close();                   
            
            System.out.println("Backup Complete.");             
        } 
        catch (FileNotFoundException e1)
        {
            e1.printStackTrace();
        } 
        catch (IOException e1)
        {
            e1.printStackTrace();
        } 
	}
	public void restore()
	{
		 try
         {
             FileInputStream fis1 = new FileInputStream("patron.dat");
             ObjectInputStream ois1 = new ObjectInputStream(fis1);
             Patron p = new PatronBag(10000);
             p = (Patron) ois1.readObject();
             library.getPatronBag().insert(p);
             ois1.close();
             
             FileInputStream fis4 = new FileInputStream("books.dat");
             ObjectInputStream ois4 = new ObjectInputStream(fis4);
             //Book b = new BookBag(10000);
            // b = (Book) ois4.readObject();
             //bookList.insertBook(b);
             ois4.close();
             
             System.out.println("Restore Complete.");
         } 
         catch (FileNotFoundException e1)
         {
             e1.printStackTrace();
         } 
         catch (ClassNotFoundException e1)
         {
             e1.printStackTrace();
         } 
         catch (IOException e1)
         {
             e1.printStackTrace();
         }
	}
}
