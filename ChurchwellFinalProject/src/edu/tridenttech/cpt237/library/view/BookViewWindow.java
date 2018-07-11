package edu.tridenttech.cpt237.library.view;


import edu.tridenttech.cpt237.library.model.AlertBox;
import edu.tridenttech.cpt237.library.model.Book;
import edu.tridenttech.cpt237.library.model.Library;
import edu.tridenttech.cpt237.library.model.Reader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class BookViewWindow 
{
	private BorderPane pane;
	private Scene scene;
	private Stage stage;
	private Image img;
	private ImageView iv;
	private Label titleLbl;
	private Label authorLbl;
	private Label genreLbl;
	private Label copiesLbl;
	private Button rentBtn;
	private Button goBackBtn;
	private Book book;
	private HBox bodyContainer;
	private VBox detailsContainer;
	private int tempCopies;
	
	public BookViewWindow(Stage stage, Book book, Library library, Reader reader) 
	{
		this.stage = stage;
		this.book = book;
		
		setNodes();
		styleNodes();
		setStage();
		
		rentBtn.setOnAction(e -> {
			if (book.getCount() > 0)
			{
				if (reader.isRented(book))
				{
					AlertBox.display("You already rented that book!", "You already rented this book. \n\nTry renting a different book.");
				}
				else
				{
					reader.rentBook(book);
					book.decrementBook();
					AlertBox.display("Book Rented!", "RENTAL CONFIRMED\n\nThanks for renting " + book.getTitle() + "!\n\nView your rentals in the MyBooks section");
					tempCopies = book.getCount();
					String copiesStr = String.valueOf(tempCopies);
					copiesLbl.setText("Copies Available:		" + copiesStr);
				}
			}
			else
			{
				AlertBox.display("Book Not Available", "Sorry there are no copies available!");
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
		tempCopies = book.getCount();
		String copiesStr = String.valueOf(tempCopies);
		
		titleLbl = new Label(book.getTitle());
		authorLbl = new Label("Author:  				" + book.getAuthor());
		genreLbl = new Label("Genre:  				" + book.getGenre());
		copiesLbl = new Label("Copies Available:		" + copiesStr);
		
		rentBtn = new Button("Rent This Book");
		goBackBtn = new Button("Go Back To Library");
		
		img = new Image("file:resources/book_covers/" + book.getImgSrc());
		iv = new ImageView();
		iv.setImage(img);		
		iv.setPreserveRatio(true);
		iv.setFitHeight(400);
		
		detailsContainer = new VBox(6);
		detailsContainer.getChildren().addAll(titleLbl,authorLbl,genreLbl,copiesLbl, rentBtn, goBackBtn);
		
		bodyContainer = new HBox(2);
		bodyContainer.getChildren().addAll(iv, detailsContainer);
	}
	
	private void styleNodes()
	{
		titleLbl.setTextFill(Color.web("white"));
		titleLbl.setFont(Font.font(16));
		titleLbl.setAlignment(Pos.CENTER);
		authorLbl.setTextFill(Color.web("white"));
		genreLbl.setTextFill(Color.web("white"));
		copiesLbl.setTextFill(Color.web("white"));
		bodyContainer.setAlignment(Pos.CENTER);
		detailsContainer.setPadding(new Insets(100,0,0,0)); 
		detailsContainer.setSpacing(30);
		bodyContainer.setSpacing(40);
	}
	
	private void setStage()
	{
		stage.setTitle(book.getTitle());
		pane = new BorderPane();
		scene = new Scene(pane);
		stage.setScene(scene);
		stage.setHeight(600);
		stage.setWidth(710);
		stage.centerOnScreen();
		
		pane.setCenter(bodyContainer);
		pane.setStyle("-fx-background-color: #191927;");
	}
}
