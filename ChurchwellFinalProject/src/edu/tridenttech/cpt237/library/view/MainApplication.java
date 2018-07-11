package edu.tridenttech.cpt237.library.view;

import edu.tridenttech.cpt237.library.model.Library;

// AUTHOR:	ADAM CHURCHWELL
// PROJECT:	FINAL PROJECT
// DATE:		APRIL 22, 2018
// CLASS:	MAIN

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception
	{
		
		Library library = Library.getInstance();
		library.loadBooks("resources/data/books.csv");
		library.loadCovers("file:resources/book_covers/");
		
		HomeWindow home = new HomeWindow(primaryStage, library);
		home.show();
	}
}
