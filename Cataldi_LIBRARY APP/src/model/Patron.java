package model;

import java.io.Serializable;
import java.util.Random;

@SuppressWarnings("serial")
public class Patron implements Serializable
{
    private String firstName, lastName, username, password, id, phoneNumber, email, reserved, transactionHistory;
    private static int idCounter = 1;
    private int n1 = 100 + new Random().nextInt(900);
    private int n2 = 1000 + new Random().nextInt(9000);

    public Patron(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = "(631)" + n1 + "-" + n2;
        this.email = firstName + "." + lastName + "@mail.library.com";
        this.id = String.valueOf(idCounter++);
        this.username = lastName + String.valueOf(firstName.charAt(0)) + id;
//        this.password = String.valueOf(firstName.charAt(0)) + String.valueOf(firstName.charAt(1)) +
//        				String.valueOf(lastName.charAt(0)) + String.valueOf(lastName.charAt(1)) + id;
    }
    public Patron(String firstName, String lastName, String phoneNumber, String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.id = String.valueOf(idCounter++);
        this.username = lastName + String.valueOf(firstName.charAt(0)) + (id.length());
        this.password = String.valueOf(firstName.charAt(0)) + String.valueOf(firstName.charAt(1)) +
        				String.valueOf(lastName.charAt(0)) + String.valueOf(lastName.charAt(1)) + id;
    }
    public Patron(String username, String password, String id)
    {
    	this.username = username;
    	this.password = password;
    	this.id = id;
    }
    public Patron(){}


    public Patron(String id, String username, String lastName, String firstName, String phoneNumber,String email2)
    {
    	this.firstName = firstName;
        this.lastName = lastName;
    	this.id = String.valueOf(idCounter++);
    	this.username = lastName + String.valueOf(firstName.charAt(0)) + id;
        this.phoneNumber = "(631)" + n1 + "-" + n2;
        this.email = firstName + "." + lastName + "@mail.library.com";
	}
	public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public String getUsername()
    {
    	return username;
    }
    public String getId()
    {
        return id;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public String getEmail()
    {
    	return email;
    }
    public String getReserved()
    {
        return reserved;
    }
    public String getTransactionHistory()
    {
        return transactionHistory;
    }
    public String getPassword()
    {
        return password;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    } 
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setReserved(String reserved)
    {
        this.reserved = reserved;
    }
    public void setTransactionHistory(String transactionHistory)
    {
        this.transactionHistory = transactionHistory;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    
	public String toString() 
	{
		return "name : " + firstName + " " + lastName + "\nusername: " + username + "\n password: "
				+ password + "\nid: " + id + "\nphonenumber: " + phoneNumber + "\nemail: " + email;
	}    
}