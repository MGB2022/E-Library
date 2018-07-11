package edu.tridenttech.cpt237.library.view;

//AUTHOR:	ADAM CHURCHWELL
//PROJECT:	FINAL PROJECT
//DATE:		APRIL 27, 2018
//CLASS:		LIBRARY WINDOW

import java.util.ArrayList;

import edu.tridenttech.cpt237.library.model.AlertBox;
import edu.tridenttech.cpt237.library.model.Book;
import edu.tridenttech.cpt237.library.model.Library;
import edu.tridenttech.cpt237.library.model.Reader;

import javafx.geometry.Orientation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LibraryWindow 
{
	private Stage stage;
	private BorderPane pane;
	private Scene scene;
	private Label logoLabel;
	private Button sortAzBtn;
	private Button sortZaBtn;
	private Button viewBtn;
	private Button closeBtn;
	private Button myBooksBtn;
	private Button backButton;
	private ListView<Book> listView = new ListView<>();
	private ArrayList<Book> books = new ArrayList<>();
	private HBox headerContainer;
	private HBox myBooksContainer;
	private HBox bodyTopContainer;
	private HBox sortContainer;
	private VBox bodyContainer;
	
	private MyBooksWindow myBooksWindow;
	private BookViewWindow bookViewWindow;
	private Reader reader = Reader.getInstance();
	
	public LibraryWindow(Stage stage, Library library)
	{
		this.stage = stage;
		

		books = library.getBooks();
		listView.getItems().setAll(books);
		listView.setCellFactory(param -> new ListCell<Book>() 
		{
			private ImageView imageView = new ImageView();

			@Override
			public void updateItem(Book book, boolean empty)
			{
				super.updateItem(book, empty);
				if (empty) 
				{
					setGraphic(null);
				} 
				else 
				{
					imageView.setImage(new Image("file:resources/book_covers/" + book.getImgSrc()));
					imageView.setPreserveRatio(true);
					imageView.setFitHeight(200);
					setGraphic(imageView);
				}
			}
		});

		// Set and Style Nodes and Stage
		
		setNodes();
		styleNodes();
		setStage();
		
		// Button Actions

		viewBtn.setOnAction(e -> {
			Book selectedBook = listView.getSelectionModel().getSelectedItem();
			if (selectedBook == null)
			{
				AlertBox.display("Please select a book to view", "Please select a book below, then click 'View Book'");
			}
			else
			{
				bookViewWindow = new BookViewWindow(stage, selectedBook, library, reader);
				bookViewWindow.show();
			}
		});
		
		myBooksBtn.setOnAction(e -> {
			if (reader.getRentals().size() < 1)
			{
				AlertBox.display("No Rentals in MyBooks", "You haven't rented any books!\n\nRent your first book before viewing your rentals.");
			}
			else
			{
				myBooksWindow = new MyBooksWindow(stage, library, reader);
				myBooksWindow.show();
			}
		});
		
		sortAzBtn.setOnAction(e -> {
			listView.getItems().setAll(library.azSort(books));
		});
		
		sortZaBtn.setOnAction(e -> {
			listView.getItems().setAll(library.zaSort(books));
		});
		
		closeBtn.setOnAction(e -> {
			stage.close();
		});
		
		backButton.setOnAction(e -> {
			HomeWindow h = new HomeWindow(stage, library);
			h.show();
		});
	}
	
	public void show()
	{
		stage.show();
	}
	
	public void setNodes()
	{
		logoLabel = new Label("ELIB\n");
		viewBtn = new Button("View Book");
		closeBtn = new Button("x");
		myBooksBtn = new Button("My Books");
		sortAzBtn = new Button("Sort A-Z");
		sortZaBtn = new Button("Sort Z-A");
		backButton = new Button("j");
		
		backButton.setFont(Font.loadFont("file:resources/fonts/Arrows.ttf", 15));
		backButton.setMinHeight(29);
		backButton.setRotate(180);

				
		headerContainer = new HBox(1);
		headerContainer.getChildren().addAll(logoLabel);
		
		sortContainer = new HBox(3);
		sortContainer.getChildren().addAll(backButton, sortAzBtn, sortZaBtn);
		
		myBooksContainer = new HBox(2);
		myBooksContainer.getChildren().addAll(viewBtn, myBooksBtn);
		
		bodyTopContainer = new HBox(2);
		bodyTopContainer.getChildren().addAll(sortContainer, myBooksContainer);
		
		bodyContainer = new VBox(2);
		bodyContainer.getChildren().addAll(bodyTopContainer, listView);
	}
	
	public void styleNodes()
	{
		logoLabel.setTextFill(Color.web("#fcf9ed"));
		logoLabel.setFont(Font.loadFont("file:resources/fonts/Minecrafter.Reg.ttf", 40));
		viewBtn.setPrefWidth(90);
		closeBtn.setPrefWidth(10);
		myBooksBtn.setPrefWidth(90);
		sortAzBtn.setPrefWidth(75);
		sortZaBtn.setPrefWidth(75);
		
		headerContainer.setAlignment(Pos.TOP_RIGHT);
		headerContainer.setPadding(new Insets(35,40,0,0));
		
		bodyTopContainer.setAlignment(Pos.CENTER);
		bodyTopContainer.setSpacing(440);
		bodyTopContainer.setPadding(new Insets(0,0,30,0));
		
		myBooksContainer.setAlignment(Pos.TOP_RIGHT);
		myBooksContainer.setSpacing(15);
		
		sortContainer.setAlignment(Pos.TOP_LEFT);
		sortContainer.setSpacing(15);
		
		listView.setOrientation(Orientation.HORIZONTAL);
		listView.setPadding(new Insets(0,0,0,0));;

		listView.setMaxHeight(250);
		listView.setMinHeight(250);
		listView.setOnMouseEntered(e -> {
			listView.setStyle("-fx-selection-bar: #191927");
		});;
		
		bodyContainer.setPadding(new Insets(0, 40, 0, 40));
		bodyContainer.setAlignment(Pos.CENTER);
	}
	
	public void setStage()
	{
		stage.setTitle("Library");
		pane = new BorderPane();
		scene = new Scene(pane);
		stage.setScene(scene);
		stage.setHeight(500);
		stage.setWidth(930);
		stage.centerOnScreen();
		
		pane.setTop(headerContainer);
		pane.setCenter(bodyContainer);
		
		pane.setStyle("-fx-background-color: #191927;");
	}
}
