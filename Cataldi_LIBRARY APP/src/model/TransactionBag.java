package model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class TransactionBag extends Transactions implements Serializable
{
    private Transactions[] transactionArray;
    private int nElms;
    
    public TransactionBag(int maxSize) 
    {
    	transactionArray = new Transactions[maxSize];
        nElms = 0;
    }
    
    public void insertTransaction(Transactions transactions)
    {
    	transactionArray[nElms++] = transactions;
    } 
    
    public Transactions removeById(String id)
    {
        int i;
        for(i = 0; i < nElms; i++)
        {
           if(transactionArray[i].getIsbn().equals(id))
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
        	Transactions t = transactionArray[i];
            for(int j = i; j < nElms - 1; j++)
            {
            	transactionArray[j] = transactionArray[j+1];
            }
            nElms--;
            return t;
        }
    }
    
    public Transactions findById(String id)
    {
        for(int i = 0; i < nElms; i++)
        {
            if(transactionArray[i].getId().equals(id))
            {
                return transactionArray[i];
            }
        }
        return null;
    }   
    
    public String toString()
    {
        StringBuilder  stringBuilder = new StringBuilder();
        
        for(int i = 0; i < nElms; i++)
        {         
        	stringBuilder.append(transactionArray[i].getDate() + " " + transactionArray[i].getTitle() + " " + transactionArray[i].getIsbn() +
        			transactionArray[i].getAuthor() + " " + transactionArray[i].getPrice() + "\n");  
        }       
        return stringBuilder.toString();
    }
    public String getDate()
    {
    	StringBuilder  stringBuilder = new StringBuilder();
    	for(int i = 0; i < nElms; i++)
        { 
    		stringBuilder.append(transactionArray[i].getDate() + "\n");
        }
    	return stringBuilder.toString();
    }
    public String getTitle()
    {
    	StringBuilder  stringBuilder = new StringBuilder();
    	for(int i = 0; i < nElms; i++)
        { 
    		stringBuilder.append(transactionArray[i].getTitle()+ "\n");
        }
    	return stringBuilder.toString();
    }
    public String getIsbn()
    {
    	StringBuilder  stringBuilder = new StringBuilder();
    	for(int i = 0; i < nElms; i++)
        { 
    		stringBuilder.append(transactionArray[i].getIsbn()+ "\n");
        }
    	return stringBuilder.toString();
    }
    public String getAuthor()
    {
    	StringBuilder  stringBuilder = new StringBuilder();
    	for(int i = 0; i < nElms; i++)
        { 
    		stringBuilder.append(transactionArray[i].getAuthor()+ "\n");
        }
    	return stringBuilder.toString();
    }
    public String getPrice()
    {
    	StringBuilder  stringBuilder = new StringBuilder();
    	for(int i = 0; i < nElms; i++)
        { 
    		stringBuilder.append(transactionArray[i].getPrice()+ "\n");
        }
    	return stringBuilder.toString();
    }
}