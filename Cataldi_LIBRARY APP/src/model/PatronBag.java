package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PatronBag extends Patron implements Serializable
{
    private Patron[] patronArray;
    private int nElms;
    
    public PatronBag(int maxSize)
    {
        patronArray = new Patron[maxSize];
        nElms = 0;
    }
    
    public void insert(Patron p)
    {
        patronArray[nElms++] = p;
    }
        
    public Patron searchById(String id)
    {
        for(int i = 0; i < nElms; i++)
        {
            if(patronArray[i].getId().equals(id))
            {
                return patronArray[i];
            }
        }
        return null;
    }
    
    public Patron searchByUsername(String username)
    {
        for(int i = 0; i < nElms; i++)
        {
            if(patronArray[i].getUsername().equals(username))
            {
                return patronArray[i];
            }
        }
        return null;
    }
        
    public Patron removeById(String id)
    {
        int i;
        for(i = 0; i < nElms; i++)
        {
            if(patronArray[i].getId().contentEquals(id))
            {
                break;
            }
        }
        if(i == nElms)
        {
            return null;
        }
        else
        {
        	Patron temp = patronArray[i];
            for(int j = i; j < nElms - 1; j++)
            {
                patronArray[i] = patronArray[j + 1];
            }
            nElms--;
            return temp;
        }
    }   
    public String toString()
    {
        StringBuilder  stringBuilder = new StringBuilder();
        
        for(int i = 0; i < nElms; i++)
        {         
        	stringBuilder.append(patronArray[i].getId() + " " + patronArray[i].getUsername() + " " + patronArray[i].getLastName() +
        						 patronArray[i].getFirstName() + " " + patronArray[i].getPhoneNumber() + " " + patronArray[i].getEmail() + "\n");  
        }       
        return stringBuilder.toString();
    }
    public String getId()
    {
    	StringBuilder  stringBuilder = new StringBuilder();
    	for(int i = 0; i < nElms; i++)
        { 
    		stringBuilder.append(patronArray[i].getId() + "\n");
        }
    	return stringBuilder.toString();
    }
    public String getUsername()
    {
    	StringBuilder  stringBuilder = new StringBuilder();
    	for(int i = 0; i < nElms; i++)
        { 
    		stringBuilder.append(patronArray[i].getUsername()+ "\n");
        }
    	return stringBuilder.toString();
    }
    public String getLastname()
    {
    	StringBuilder  stringBuilder = new StringBuilder();
    	for(int i = 0; i < nElms; i++)
        { 
    		stringBuilder.append(patronArray[i].getLastName()+ "\n");
        }
    	return stringBuilder.toString();
    }
    public String getFirstname()
    {
    	StringBuilder  stringBuilder = new StringBuilder();
    	for(int i = 0; i < nElms; i++)
        { 
    		stringBuilder.append(patronArray[i].getFirstName()+ "\n");
        }
    	return stringBuilder.toString();
    }
    public String getPhoneNumber()
    {
    	StringBuilder  stringBuilder = new StringBuilder();
    	for(int i = 0; i < nElms; i++)
        { 
    		stringBuilder.append(patronArray[i].getPhoneNumber()+ "\n");
        }
    	return stringBuilder.toString();
    }
    public String getEmail()
    {
    	StringBuilder  stringBuilder = new StringBuilder();
    	for(int i = 0; i < nElms; i++)
        { 
    		stringBuilder.append(patronArray[i].getEmail()+ "\n");
        }
    	return stringBuilder.toString();
    }
    public String getPatronUsername(int username)
    {
    	String str = "";
    	
    	if (username < nElms)
    	{	
    		if (patronArray[username].getUsername() != null)
    		{
    			str = patronArray[username].getUsername();
    		}
    		else
    		{
    			str = null;
    		}
    	}
    	
    	return str;
    }
    public void swapPatrons(int sourceIndex, int destinationIndex)
    {
    	Patron temp = new Patron();
    	
    	temp = patronArray[sourceIndex];
    	
    	patronArray[destinationIndex] = patronArray[sourceIndex];
    	
    	patronArray[sourceIndex] = temp;
    }
    
    public int getNumOfPatrons()
    {
    	return nElms;
    }
}