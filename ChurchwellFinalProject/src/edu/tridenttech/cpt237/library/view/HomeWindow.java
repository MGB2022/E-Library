package edu.tridenttech.cpt237.library.view;

// AUTHOR:	ADAM CHURCHWELL
// PROJECT:	FINAL PROJECT
// DATE:		APRIL 22, 2018
// CLASS:	HOME WINDOW

import edu.tridenttech.cpt237.library.model.Library;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class HomeWindow 
{
	private Stage stage;
	private BorderPane pane;
	private Scene scene;
	private Label logoLabel;
	private Label appDescription;
	private Label instructions;
	private Button readerBtn;
	private Button librarianBtn;
	private VBox labelContainer;
	private VBox footerContainer;
	private HBox buttonContainer;
	private Library library;
	private LibraryWindow libraryWindow;
	private DatabaseWindow databaseWindow;
	
	public HomeWindow(Stage stage, Library library)
	{
		this.stage = stage;
		this.library = library;
		
		setNodes();
		styleNodes();
		setStage();
		
		readerBtn.setOnAction(new enterListener());

		librarianBtn.setOnAction(e -> {
			databaseWindow = new DatabaseWindow(stage, library);
			databaseWindow.show();
		});
	}
	
	public void show()
	{
		stage.show();
	}
	
	public void setNodes()
	{
		logoLabel = new Label("ELIB\n");
		appDescription = new Label("Electronic Library");
		instructions = new Label("Enter, Browse, and Rent A Book");
		
		readerBtn = new Button("Reader");
		librarianBtn = new Button("Librarian");
		
		labelContainer = new VBox(2);
		labelContainer.getChildren().addAll(logoLabel, appDescription);
		
		buttonContainer = new HBox(2);
		buttonContainer.getChildren().addAll(readerBtn, librarianBtn);
		
		footerContainer = new VBox(2);
		footerContainer.getChildren().addAll(instructions, buttonContainer);
	}
	
	public void styleNodes()
	{
		logoLabel.setTextFill(Color.web("white"));
		logoLabel.setFont(Font.loadFont("file:resources/fonts/Minecrafter.Reg.ttf", 70));
		logoLabel.setPadding(new Insets(0, 0, 20, 0));
		
		appDescription.setTextFill(Color.web("#fcf9ed"));
		appDescription.setPadding(new Insets(0, 0, 20, 0));
		appDescription.setFont(Font.font(null, FontWeight.NORMAL, 20));
		
		instructions.setTextFill(Color.web("#fcf9ed"));
		instructions.setPadding(new Insets(0, 0, 40, 0));
		instructions.setFont(Font.font(null, FontWeight.NORMAL, 16));
		
		readerBtn.setPrefWidth(120.0);
		librarianBtn.setPrefWidth(120.0);
		
		labelContainer.setAlignment(Pos.CENTER);
		labelContainer.setPadding(new Insets(0, 20, 0, 20));
		
		buttonContainer.setAlignment(Pos.CENTER);
		buttonContainer.setPadding(new Insets(0, 20, 60, 20));
		buttonContainer.setSpacing(40);
		
		footerContainer.setAlignment(Pos.CENTER);
	}
	
	public void setStage()
	{
		stage.setTitle("Home");
		pane = new BorderPane();
		scene = new Scene(pane);
		stage.setScene(scene);
		stage.setHeight(430);
		stage.setWidth(410);
		stage.centerOnScreen();
		
		pane.setCenter(labelContainer);
		pane.setBottom(footerContainer);
		
		pane.setStyle("-fx-background-color: #191927;");
	}
	
	private class enterListener implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() == readerBtn)
			{
				libraryWindow = new LibraryWindow(stage, library);
				libraryWindow.show();
			}
		}
	}
}
