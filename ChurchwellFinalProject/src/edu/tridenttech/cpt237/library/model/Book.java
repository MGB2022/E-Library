package edu.tridenttech.cpt237.library.model;

import java.util.Comparator;

public class Book implements Comparable<Book>
{
	private String title;
	private String author;
	private String genre;
	private int count;
	private String imgSrc;
	private int recordIndex;
	
	// Default constructor, used as safeguard when adding new books
	public Book()
	{
		this.title = "";
		this.author = "";
		this.genre = "";
		this.count = 0;
		this.imgSrc = "";
		this.recordIndex = 0;
	}
	
	public Book(String title, String author, String genre, int count, String imgSrc, int recordIndex)
	{
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.count = count;
		this.imgSrc = imgSrc;
		this.recordIndex = recordIndex;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public String getGenre()
	{
		return genre;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public String getImgSrc()
	{
		return imgSrc;
	}
	
	public void setRecordIndex(int newRecordIndex)
	{
		this.recordIndex = newRecordIndex;
	}
	
	public void setTitle(String newTitle)
	{
		this.title = newTitle;
	}
	
	public void setAuthor(String newAuthor)
	{
		this.author = newAuthor;
	}
	
	public void setGenre(String newGenre)
	{
		this.genre = newGenre;
	}
	
	public void setCount(int newCount)
	{
		this.count = newCount;
	}
	
	public void setImgSrc(String newImgSrc)
	{
		this.imgSrc = newImgSrc;
	}

	
	public int getRecordIndex()
	{
		return recordIndex;
	}
	
	public void incrementBook()
	{
		this.count++;
	}
	
	public void decrementBook()
	{
		this.count--;
	}
	
	// Alphabetical Sort
	public int compareTo(Book other) 
	{
		int compareInt = this.getTitle().compareTo(other.getTitle());
		if (compareInt > 0)
		{
			return 1;
		}
		else if (compareInt < 0)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
	
	// Reverse Alphabetical Sort
	static class ReverseAlphaSort implements Comparator<Book>
	{
		@Override
		public int compare(Book b1, Book b2) 
		{
			
			int compareInt = b1.getTitle().compareTo(b2.getTitle());
			if (compareInt < 0)
			{
				return 1;
			}
			else if (compareInt > 0)
			{
				return -1;
			}
			else
			{
				return 0;
			}
		}
	}
}
