package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import model.Library;

public class ImportAndExportFiles 
{
	private Library library;
	
	public ImportAndExportFiles(Library library) 
	{
		this.library = library;
	}
	
	public void importPatron()
	{
		String firstName, lastName, phoneNumber, email, reserved, transactionHistory;
		File firstNamesFile, lastNamesFile;
		Scanner firstNameScanner, lastNameScanner;
		
        firstNamesFile = new File("C:\\Users\\jessica\\CSE218\\LIBRARY APP\\src\\textFiles\\firstNames");
        lastNamesFile = new File("C:\\Users\\jessica\\CSE218\\LIBRARY APP\\src\\textFiles\\lastNames");
        try
        {
            firstNameScanner = new Scanner(firstNamesFile);
            lastNameScanner = new Scanner(lastNamesFile);
            
            while (firstNameScanner.hasNextLine() && lastNameScanner.hasNextLine()) 
            {   
            	firstName = firstNameScanner.nextLine();
            	lastName = lastNameScanner.nextLine();
                phoneNumber = "631 ";
                email = firstNameScanner.nextLine() + "." + lastNameScanner.nextLine() + "@email.com";
                reserved = "";
                transactionHistory = "";
                //Patron patron = new Patron(firstName, lastName, phoneNumber, email, reserved, transactionHistory);
            }
            firstNameScanner.close();
            lastNameScanner.close();
          } 
          catch (FileNotFoundException e1)
          {
              e1.printStackTrace();
          } 
        System.out.println("Patron Import Complete.");
	}
	public void importBook()
	{
		File titleFile, isbnFile, firstNameFile, lastNameFile;
		Scanner titleScanner, isbnScanner, firstNameScanner, lastNameScanner;
        String title, isbn, authors;
        
        titleFile = new File("C:\\Users\\jessica\\CSE218\\LIBRARY APP\\src\\textFiles\\bookTitle");
        isbnFile = new File("C:\\Users\\jessica\\CSE218\\LIBRARY APP\\src\\textFiles\\isbn");
        firstNameFile = new File("C:\\Users\\jessica\\CSE218\\LIBRARY APP\\src\\textFiles\\firstNames");
        lastNameFile = new File("C:\\Users\\jessica\\CSE218\\LIBRARY APP\\src\\textFiles\\lastNames");
                
         try
         {
             titleScanner = new Scanner(titleFile);
             isbnScanner = new Scanner(isbnFile); 
             firstNameScanner = new Scanner(firstNameFile);
             lastNameScanner = new Scanner (lastNameFile);
             
             while (titleScanner.hasNextLine() && isbnScanner.hasNextLine() &&
                     firstNameScanner.hasNextLine() && lastNameScanner.hasNextLine()) 
             {   
                 title = titleScanner.nextLine();
                 isbn = isbnScanner.nextLine(); 
                 authors = firstNameScanner.nextLine() + " " + lastNameScanner.nextLine();
          	 
                 library.getBookList().insertBook(title, isbn, authors, false);
             }
             titleScanner.close();
             isbnScanner.close();
             firstNameScanner.close();
           } 
           catch (FileNotFoundException e1)
           {
               e1.printStackTrace();
           }
         System.out.println("Book Import Complete.");
	}
//*******************************************************************************************************************************************
	
	public void exportPatron()
	{
		 try
         {
             ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("patron.txt"));
             oos.writeObject(library.getPatronBag().toString());                 
             oos.close();
         } 
         catch (IOException e1)
         {
             e1.printStackTrace();
         }
         System.out.println("Patron Export Complete.");                
	}
	public void exportBook()
	{
		try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("books.txt"));
            oos.writeObject(library.getBookList().toString());                   
            oos.close();
        } 
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        System.out.println("Book Export Complete.");            
	}
}