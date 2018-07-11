package edu.tridenttech.cpt237.library.view;

import edu.tridenttech.cpt237.library.model.AlertBox;
import edu.tridenttech.cpt237.library.model.Book;
import edu.tridenttech.cpt237.library.model.Library;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

public class DatabaseWindow 
{
	private Stage stage;
	private Scene scene;
	private BorderPane pane;
	private Button addBtn, uploadBtn, saveToFileBtn, exitBtn;
	private TableView<Book> bookTable;
	private VBox tableContainer;
	private VBox bodyContainer;
	private HBox newEntryContainer;
	private HBox fileUploadContainer;
	private HBox saveQuitContainer;
	private HBox bodyBottomContainer;
	private HBox footerContainer;
	private TextField title, author, genre, count;
	private Label instructionLabel, imgSrc, logo;
	private Library library;
	
	@SuppressWarnings("unchecked")
	public DatabaseWindow(Stage stage, Library library)
	{
		this.stage = stage;
		this.library = library;
		ObservableList<Book> books = FXCollections.observableArrayList(library.getBooks());

		TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
		titleColumn.setMinWidth(300);
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
		authorColumn.setMinWidth(160);
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		
		TableColumn<Book, String> genreColumn = new TableColumn<>("Genre");
		genreColumn.setMinWidth(120);
		genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
		
		TableColumn<Book, Integer> countColumn = new TableColumn<>("Num In Stock");
		countColumn.setMinWidth(110);
		countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
		
		TableColumn<Book, String> imgSrcColumn = new TableColumn<>("Img Src");
		imgSrcColumn.setMinWidth(270);
		imgSrcColumn.setCellValueFactory(new PropertyValueFactory<>("imgSrc"));
		
		bookTable = new TableView<>();
		bookTable.setItems(books);
		bookTable.getColumns().addAll(titleColumn, authorColumn, genreColumn, countColumn, imgSrcColumn);
		
		setNodes();
		styleNodes();
		setStage();
		
		addBtn.setOnAction(e -> {
			Boolean test = isInteger(count);
			if (!test)
			{
				AlertBox.display("Quantity Error", "This text field must be an integer");
				count.clear();
			}
			else
			{
				addButtonClicked();
			}
		});
		
		uploadBtn.setOnAction(e -> {

		});
		
		saveToFileBtn.setOnAction(e -> {
			saveFile();
		});
		
		title.setOnMouseClicked(e -> {
			title.clear();
		});
		
		author.setOnMouseClicked(e -> {
			author.clear();
		});
		
		genre.setOnMouseClicked(e -> {
			genre.clear();
		});
		
		count.setOnMouseClicked(e -> {
			count.clear();
		});
		
		uploadBtn.setOnAction(e -> {
			
			File initialDirectory = new File("resources/covers_to_add");
			File saveToDirectory = new File("resources/book_covers");
			
			FileChooser fC = new FileChooser();
			fC.setInitialDirectory(initialDirectory);
			
			File fileToUpload = fC.showOpenDialog(stage);
			imgSrc.setText(fileToUpload.getName());
			
			Image img = new Image(fileToUpload.toURI().toString());
			
			fC.setInitialDirectory(saveToDirectory);
			fC.setInitialFileName(fileToUpload.getName());
			File whereToSave = fC.showSaveDialog(stage);
			
			library.addCover(img, fileToUpload.getName());
			library.loadCovers("file:resources/book_covers/");
			
			
		    try {
		        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(img, null);
		        ImageIO.write(bufferedImage, "jpg", whereToSave);
		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }
		});
		
		exitBtn.setOnAction(e -> {
			stage.close();
			HomeWindow h = new HomeWindow(stage, library);
			h.show();
		});
		
	}
	
	
	public void show()
	{
		this.show();
	}
	
	private void setNodes() 
	{
		logo = new Label("ELIB");
		String instructions = "INSTRUCTIONS: To enter a new book, enter book details in the text boxes. Next upload the book\n"
							+ "cover and save to book_covers folder. To finish, click 'Add Book'. To test the new book, click\n"
							+ "'Exit Database and login as Reader. To save a new book to the database, click 'Save File'";
							
		addBtn = new Button("Add Book");
		uploadBtn = new Button("Upload");
		saveToFileBtn = new Button("Save File");
		exitBtn = new Button("Exit Database");
		title = new TextField("Title");
		author = new TextField("Author");
		genre = new TextField("Genre");
		count = new TextField("Quantity");
		imgSrc = new Label("Click Upload Button!");
		instructionLabel = new Label(instructions);
		
		tableContainer = new VBox(1);
		tableContainer.getChildren().addAll(bookTable);
		
		newEntryContainer = new HBox(6);
		newEntryContainer.getChildren().addAll(title, author, genre, count, imgSrc, addBtn);
		
		fileUploadContainer = new HBox(1);
		fileUploadContainer.getChildren().addAll(uploadBtn);
		
		saveQuitContainer = new HBox(2);
		saveQuitContainer.getChildren().addAll(saveToFileBtn, exitBtn);
		
		footerContainer = new HBox(2);
		footerContainer.getChildren().addAll(logo, saveQuitContainer);
		
		bodyBottomContainer = new HBox(2);
		bodyBottomContainer.getChildren().addAll(instructionLabel, fileUploadContainer);
		
		bodyContainer = new VBox(3);
		bodyContainer.getChildren().addAll(bookTable, newEntryContainer, bodyBottomContainer);
		
	};
	
	private void styleNodes()
	{
		logo.setTextFill(Color.web("#191927"));
		logo.setFont(Font.loadFont("file:resources/fonts/Minecrafter.Reg.ttf", 30));
		addBtn.setPrefWidth(90);
		uploadBtn.setPrefWidth(90);
		saveToFileBtn.setPrefWidth(130);
		exitBtn.setPrefWidth(130);
		title.setMinWidth(200);
		author.setMinWidth(120);
		count.setMinWidth(120);
		imgSrc.setMinWidth(160);
		imgSrc.setAlignment(Pos.CENTER);
		
		bookTable.setMaxHeight(350);
		
		bodyContainer.setPadding(new Insets(30,30,30,30));
		bodyBottomContainer.setPadding(new Insets(20,0,20,0));
		bodyBottomContainer.setSpacing(340);
		bodyBottomContainer.setAlignment(Pos.CENTER);
		instructionLabel.setPadding(new Insets(15,0,0,0));

		newEntryContainer.setPadding(new Insets(30,0,0,0));
		newEntryContainer.setSpacing(15);
		newEntryContainer.setAlignment(Pos.CENTER);
		
		fileUploadContainer.setPadding(new Insets(30,0,0,0));
		fileUploadContainer.setSpacing(20);
		fileUploadContainer.setAlignment(Pos.CENTER);
		
		saveQuitContainer.setPadding(new Insets(0,0,30,30));
		saveQuitContainer.setSpacing(30);
		saveQuitContainer.setAlignment(Pos.CENTER);
		
		footerContainer.setPadding(new Insets(0,30,10,30));
		footerContainer.setAlignment(Pos.CENTER);
		footerContainer.setSpacing(630);
		logo.setPadding(new Insets(-40,0,0,0));
	}
	
	private void setStage()
	{
		stage.setTitle("Library Database");
		pane = new BorderPane();
		scene = new Scene(pane);
		stage.setScene(scene);
		stage.setHeight(740);
		stage.setWidth(1090);
		stage.centerOnScreen();
		
		pane.setCenter(bodyContainer);
		pane.setBottom(footerContainer);
		pane.setStyle("-fx-background-color: white;");
	}
	
	private void addButtonClicked()
	{
		int newRecordIndex = library.getNumBooks() + 1;
		Book book = new Book();
		book.setTitle(title.getText());
		book.setAuthor(author.getText());
		book.setGenre(genre.getText());
		book.setCount(Integer.parseInt(count.getText()));
		book.setImgSrc(imgSrc.getText());
		book.setRecordIndex(newRecordIndex);
		bookTable.getItems().add(book);
		title.clear();
		author.clear();
		genre.clear();
		count.clear();
		imgSrc.setText("Click Upload Button!");
		library.addBook(book);
	}
	
	private void saveFile()
	{
		String fileName = "resources/data/books.csv";
		ArrayList<Book> arrayToSave = new ArrayList<>();
		arrayToSave = library.getBooks();
		
		PrintWriter writer = null;
			
		try 
		{
			writer = new PrintWriter(fileName);
			AlertBox.display("Save Successful", "SAVE CONFIRMATION\n\nYou successfully saved your library's data file!");
		}
		catch (FileNotFoundException e)
		{
			AlertBox.display("File Not Found", "The file you're trying to save to doesn't exist");
		}
		
		for(Book b : arrayToSave)
		{
			writer.println(b.getTitle() + "," + b.getAuthor() + "," + b.getGenre() + "," + b.getCount() + "," + b.getImgSrc());
		}
		
		writer.close();
	}
	
	private boolean isInteger(TextField test)
	{	
		try {
			@SuppressWarnings("unused")
			int count = Integer.parseInt(test.getText());
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}
}
