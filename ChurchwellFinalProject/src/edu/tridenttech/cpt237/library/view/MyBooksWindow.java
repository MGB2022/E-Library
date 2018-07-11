package edu.tridenttech.cpt237.library.view;

//AUTHOR:	ADAM CHURCHWELL
//PROJECT:	FINAL PROJECT
//DATE:		APRIL 27, 2018
//CLASS:		MY BOOKS WINDOW

import java.util.ArrayList;

import edu.tridenttech.cpt237.library.model.AlertBox;
import edu.tridenttech.cpt237.library.model.Book;
import edu.tridenttech.cpt237.library.model.Library;
import edu.tridenttech.cpt237.library.model.Reader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class MyBooksWindow 
{
	private int MAX_RENTALS = 5;
	
	private BorderPane pane;
	private Scene scene;
	private Stage stage;
	private Label logoLabel;
	private Label maxLbl;
	private Button returnBtn;
	private Button goBackBtn;
	private ComboBox<String> booksRented;
	private ArrayList<Book> myBooks = new ArrayList<>();
	private HBox headerContainer;
	private VBox buttonContainer;
	private HBox bodyContainer;
	private HBox footerContainer;
	
	public MyBooksWindow(Stage stage,  Library library, Reader reader)
	{
		this.stage = stage;
		myBooks = reader.getRentals();

		
		setNodes();
		styleNodes();
		setStage();
		
		returnBtn.setOnAction(e -> {
			
			for (int i = 0; i < myBooks.size(); i++)
			{
				if (myBooks.get(i).getTitle().equals(booksRented.getValue()))
				{
					AlertBox.display("Return Successful","You successfully returned " + myBooks.get(i).getTitle());
					reader.returnBook(myBooks.get(i));
					booksRented.getItems().remove(i);
				}
			}
		});
		
		goBackBtn.setOnAction(e -> {
			LibraryWindow l = new LibraryWindow(stage, library);
			l.show();
		});
	}
	
	public void show()
	{
		stage.show();
	}
	
	private void setNodes()
	{
		logoLabel = new Label("ELIB\n");
		maxLbl = new Label("Maximum Rentals: " + MAX_RENTALS);
		returnBtn = new Button("Return This Book");
		goBackBtn = new Button("Go Back to Library");
		
		booksRented = new ComboBox<String>();
		booksRented.setPromptText("Your Rentals");
		for(int i = 0; i < myBooks.size(); i++)
		{
			booksRented.getItems().add(myBooks.get(i).getTitle());
		}
		
		headerContainer = new HBox(1);
		headerContainer.getChildren().addAll(logoLabel);
		
		buttonContainer = new VBox(2);
		buttonContainer.getChildren().addAll(returnBtn, goBackBtn);
		
		bodyContainer = new HBox(2);
		bodyContainer.getChildren().addAll(booksRented,buttonContainer);
		
		footerContainer = new HBox(1);
		footerContainer.getChildren().addAll(maxLbl);
	}
	
	private void styleNodes()
	{
		logoLabel.setTextFill(Color.web("white"));
		logoLabel.setFont(Font.loadFont("file:resources/fonts/Minecrafter.Reg.ttf", 40));
		maxLbl.setTextFill(Color.web("white"));
		returnBtn.setPrefWidth(160);
		goBackBtn.setPrefWidth(160);
		
		booksRented.setPrefWidth(200);
		
		headerContainer.setAlignment(Pos.TOP_RIGHT);
		headerContainer.setPadding(new Insets(35,40,40,0));
		
		buttonContainer.setSpacing(30);
		
		bodyContainer.setSpacing(30);
		bodyContainer.setPadding(new Insets(30,30,30,30));
		
		footerContainer.setAlignment(Pos.CENTER);
		footerContainer.setPadding(new Insets(20,20,20,20));
	}
	
	private void setStage()
	{
		stage.setTitle("My Books");
		pane = new BorderPane();
		scene = new Scene(pane);
		
		stage.setScene(scene);
		stage.setHeight(380);
		stage.setWidth(460);
		stage.centerOnScreen();
		
		pane.setTop(headerContainer);
		pane.setCenter(bodyContainer);
		pane.setBottom(footerContainer);
		
		pane.setStyle("-fx-background-color: #191927;");
	}
}
