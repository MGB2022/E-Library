package edu.tridenttech.cpt237.library.model;

import java.util.ArrayList;

public class Reader 
{
	private ArrayList<Book> booksRented = new ArrayList<>();
	private int numBooksRented;
	
	// Singleton ref variable
	private static Reader firstInstance = null;
	
	// Singleton constructor
	private Reader() {};
	
	// Singleton instantiation
	public static Reader getInstance()
	{
		if (firstInstance == null)
		{
			firstInstance = new Reader();
		}
		return firstInstance;
	}
	
	public ArrayList<Book> getRentals()
	{
		return booksRented;
	}
	
	public boolean isRented(Book newBook)
	{
		if (booksRented.contains(newBook))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void rentBook(Book newBook)
	{
		if (newBook.getCount() > 0)
		{
			if (numBooksRented < 5)
			{
				booksRented.add(newBook);
				numBooksRented++;
			}
			else
			{
				AlertBox.display("Maximum Rentals Exceeded", "You cannot rent more than 5 books at a time. \n\nPlease return a book if you want to check out " + newBook.getTitle());
			}
		}
		else
		{
			AlertBox.display("Out of Stock", newBook.getTitle() + "is currently out of stock. Sorry");
		}
	}
	
	public void returnBook(Book newBook)
	{
		if (booksRented.contains(newBook))
		{
			booksRented.remove(newBook);
			newBook.incrementBook();
			numBooksRented--;
		}
		else
		{
			AlertBox.display("Book Not Rented", "You're not currently renting " + newBook.getTitle() + ", so you can't return it!");
		}
	}
	
}
