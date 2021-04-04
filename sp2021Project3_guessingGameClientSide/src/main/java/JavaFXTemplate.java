import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class JavaFXTemplate extends Application {
	HashMap<String, Scene> sceneMap;
	EventHandler<ActionEvent> pressButton;
	private BorderPane borderPane;
	private BorderPane gamePane;
	private Button start;
	private Button b1;
	private Button b2;
	private Button b3;
	private Button b4;
	private Button b5;
	private Button b6;
	private Button Cat1 = new Button("");
	private Button Cat2 = new Button("");;
	private Button Cat3 = new Button("");;
	public Stage dummyStage;
	private VBox root;
	private MenuBar menu;
	private Menu options;
	private MenuItem exit;
	private MenuItem howToPlay;
	private ArrayList<String> categ;
	int countCat;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		countCat = 0;
		categ = new ArrayList<String>();
		HBox hBox = new HBox();
//		HBox hBox2 = new HBox();
//		VBox v1 = new VBox();
		primaryStage.setTitle("Welcome to Hangman");
		dummyStage = primaryStage;
		dummyStage.setTitle("Welcome to Hangman");
		
		sceneMap = new HashMap<String,Scene>();
		Label label = new Label("-------------- !! WELCOME TO HANGMAN !! -------------------");
		label.setAlignment(Pos.CENTER);
		start = new Button("Start");
		hBox.getChildren().add(start);
		hBox.setAlignment(Pos.CENTER);
//		hBox2.setAlignment(Pos.CENTER);
		root = new VBox(300,label,hBox);
		root.setAlignment(Pos.CENTER);
		borderPane = new BorderPane();
		borderPane.setCenter(root);
				
		Scene welcome = new Scene(borderPane, 600,600);
		start.setOnAction(e -> primaryStage.setScene(sceneMap.get("choose")));
		pressButton = new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				Button b = (Button)event.getSource();
				System.out.print(b.getText());
				String s = b.getText();
				if (countCat == 0) {
					Cat1.setText(s);
				} else if (countCat == 1) {
					Cat2.setText(s);
				} else {
					Cat3.setText(s);
				}
				b.setText("PRESSED");
				b.setDisable(true);
				countCat++;
				if (countCat == 3) {
					dummyStage.setScene(sceneMap.get("game"));
				}
			}
		};
		sceneMap.put("welcome", welcome);
		sceneMap.put("choose", getCategories());
		sceneMap.put("game", gameScene());
		sceneMap.put("win", winScene());
		dummyStage.setScene(sceneMap.get("welcome"));
		dummyStage.show();
	}
	
	public Scene getCategories() {
		Label instruction = new Label("Pick 3 Categories");
		b1 = new Button ("Marvel");
		b2 = new Button("Celebrities");
		b3 = new Button("Food");
		b4 = new Button("Brands");
		b5 = new Button("Countries");
		b6 = new Button("Animals");
		b1.setOnAction(pressButton);
		b2.setOnAction(pressButton);
		b3.setOnAction(pressButton);
		b4.setOnAction(pressButton);
		b5.setOnAction(pressButton);
		b6.setOnAction(pressButton);
		
		VBox box = new VBox(20, instruction, b1, b2, b3, b4, b5, b6);
		return new Scene(box, 200, 500);
	}
	
	public Scene gameScene() {
		// Text field
		TextField t1 = new TextField();
		t1.setPrefWidth(50);
		// All labels
		Label label = new Label("Categories");
		label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		Label label2 = new Label("_ _ E _ _ _");
		label2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		Label label3 = new Label("Guess the Letter:");
		label3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		Label label4 = new Label("Number of Misses: ");
		label4.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		Label label5 = new Label("N/A");
		label5.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		Button check = new Button("Check");
		Button clear = new Button("Clear");
		
		HBox box = new HBox(10, Cat1, Cat2, Cat3);
		box.setAlignment(Pos.CENTER);
		HBox box2 = new HBox(10, check, clear);
		box2.setAlignment(Pos.CENTER);
		HBox box3 = new HBox(10, label3, t1);
		box3.setAlignment(Pos.CENTER);
		HBox box4 = new HBox(10, label4, label5);
		box4.setAlignment(Pos.CENTER);
//		HBox box5 = new HBox(5,label11, label7, label10);
//		box5.setAlignment(Pos.CENTER);
//		HBox box6 = new HBox(5,label8, label9);
//		box6.setAlignment(Pos.CENTER);
		// Menu
		menu = new MenuBar();
		options = new Menu("options");
		exit = new MenuItem("exit");
		howToPlay = new MenuItem("Help");
		options.getItems().addAll(howToPlay, exit);
		menu.getMenus().addAll(options);
		// VBox
//		VBox hangMan = new VBox(10, box5, box6);
//		hangMan.setAlignment(Pos.CENTER);
//		VBox gameVBox = new VBox(10, label, box, label2, box3, box2, box4, label6,hangMan);
		VBox gameVBox = new VBox(10, label, box, label2, box3, box2, box4);
		gameVBox.setAlignment(Pos.CENTER);
		// Scene Changer
		Cat1.setOnAction(e -> dummyStage.setScene(sceneMap.get("win")));
		// Border Pane
		gamePane = new BorderPane();
		gamePane.setTop(menu);
		gamePane.setCenter(gameVBox);
		return new Scene(gamePane, 600, 600);
	}
	
	public Scene winScene() {
		Button playAgain = new Button("playAgain");
		Button exit = new Button("Exit");
		exit.setOnAction(e -> System.exit(0));
		Label label = new Label("Game Over");
		label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		HBox hbox = new HBox(10, playAgain, exit);
		hbox.setAlignment(Pos.CENTER);
		VBox vbox = new VBox(100, label, hbox);
		vbox.setAlignment(Pos.CENTER);
		BorderPane pane = new BorderPane();
		pane.setCenter(vbox);
		return new Scene(pane, 400, 400);
		
	}

}
