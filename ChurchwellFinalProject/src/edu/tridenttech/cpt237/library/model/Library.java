package edu.tridenttech.cpt237.library.model;

import edu.tridenttech.cpt237.library.model.Book;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Library 
{
	private ArrayList<Book> Books = new ArrayList<>();
	private ArrayList<String> imgSrcs = new ArrayList<>();
	private ArrayList<Image> bookCovers = new ArrayList<>();
	private int numBooks;
	
	// Singleton ref variable
	private static Library firstInstance = null;
	
	// Singleton constructor
	private Library() {};
	
	// Singleton instantiation
	public static Library getInstance()
	{
		if (firstInstance == null)
		{
			firstInstance = new Library();
		}
		return firstInstance;
	}
	
	public int getNumBooks()
	{
		return numBooks;
	}
	
	public ArrayList<Book> getBooks()
	{
		return Books;
	}
	
	public ArrayList<Image> getCovers()
	{
		return bookCovers;
	}
	
	// Load book data from books.csv (title, author, genre, count, imgSrc)
	public void loadBooks(String filePath) throws FileNotFoundException
	{
		Scanner input;
		input = new Scanner(new File(filePath));

		while (input.hasNext())
		{
			String line = input.nextLine();
			String[] fields = line.split(",");
			
			try 
			{	
				String countsStr = fields[3];
				int newCount = Integer.parseInt(countsStr);
				imgSrcs.add(fields[4]);
				
				Book newBook = new Book(fields[0], fields[1], fields[2], newCount, fields[4], numBooks);
				
				Books.add(newBook);
			}
			catch(NumberFormatException n)
			{
				AlertBox.display("Library File Error", "Number Format Exception on line " + (numBooks + 1) + "!");
			}
			
			numBooks++;
		}
		input.close();
		
	}
	
	public void loadCovers(String coversPath)
	{
		
		for (int i = 0; i < this.numBooks; i++) 
		{
			bookCovers.add(new Image(coversPath + imgSrcs.get(i)));
		}
	}
	
	public ArrayList<Book> azSort(ArrayList<Book> booksToSort)
	{

		Collections.sort(booksToSort);

		return booksToSort;
	}
	
	public ArrayList<Book> zaSort(ArrayList<Book> booksToSort)
	{

		Collections.sort(booksToSort);
		Collections.sort(booksToSort, new Book.ReverseAlphaSort());
		return booksToSort;
	}
	
	public void addBook(Book bookToAdd)
	{
		Books.add(bookToAdd);
	}
	
	public void addCover(Image coverToAdd, String imgSrc)
	{
		bookCovers.add(coverToAdd);
		imgSrcs.add(imgSrc);
	}
}
