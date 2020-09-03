package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import model.Library;
import model.Patron;

@SuppressWarnings("serial")
public class InsertionSort extends Patron implements Serializable
{
	private Patron[] patronInsertionSort;
	private int nElms;
	
	
	public InsertionSort(int maxSize)
	{
		patronInsertionSort = new Patron[maxSize];
		nElms = 0;
	}
	
	public void insert(Patron value)
	{
		patronInsertionSort[nElms++] = value;
	}
	
	public void insertionSort(Library library)
	{		
		int i, j;

		long startTime = System.currentTimeMillis();
		for (i = 1; i < library.getPatronBag().getNumOfPatrons(); i++)
		{
			j = i;
			String temp = library.getPatronBag().getPatronUsername(i);
			@SuppressWarnings("unused")
			String temp2 = library.getPatronBag().getPatronUsername(j);
			while (j > 0 && library.getPatronBag().getPatronUsername(j - 1).compareTo(library.getPatronBag().getPatronUsername(i)) > 0) 
			{ 
				temp = library.getPatronBag().getPatronUsername(i - 1);
				j--;
				library.getPatronBag().swapPatrons(i, j);
			}
			temp2 = temp;
		}
		long endTime = System.currentTimeMillis();		
		long duration = (endTime - startTime);
		try
        {
            FileWriter f = new FileWriter("timeTookToSort.txt",true);
      	  	BufferedWriter o = new BufferedWriter(f);
      	  	o.write("\nInsertion Sorting: " + duration + " milliseconds\n");
      	  	o.close();
        } 
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
	}
}