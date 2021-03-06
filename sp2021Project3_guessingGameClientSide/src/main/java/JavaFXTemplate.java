/*
 * Spring 2021 - CS 342 - Software Design - Professor Mark Hallenbeck
 * Project 3 - Word Guessing Game
 * Pranav Rathod - 669687574
 * Parth Tawde   - 660276790
 * 
 * This file consists the code and logic behind Client Graphic User Interface
 * 
 */

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class JavaFXTemplate extends Application {
	private TextField t1;
	HashMap<String, Scene> sceneMap;
	EventHandler<ActionEvent> pressButton;
	EventHandler<ActionEvent> categPress;
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
	private Button Cat2 = new Button("");
	private Button Cat3 = new Button("");
	private Button currentCat;
	public Stage dummyStage;
	private VBox root;
	private MenuBar menu;
	private Menu options;
	private MenuItem exit;
	private MenuItem howToPlay;
	private ArrayList<String> categ;
	private GameStatus object;
	private String GuessWord;
	private Label label;
	private Label label2;
	private Label label3;
	private Label label4;
	private Label label5;
	private String catStrings[] = new String[3];
	private Button bArray[] = new Button[3];
	private Label label6 = new Label();
	Label endMessage = new Label("Game Over");
	private Image img;
	private ImageView view;
	int countCat;
	private String image = "Hangman0.jpeg";
	Client clientConnection;
	private boolean checkIfTrue = true;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	EventHandler<ActionEvent> again;

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Label port = new Label("Enter the port number: ");
		port.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		port.setStyle("-fx-background-color: white");
		t1 = new TextField();
		t1.setPrefWidth(100);
		countCat = 0;
		categ = new ArrayList<String>();
		
		primaryStage.setTitle("Welcome to Hangman");
		dummyStage = primaryStage;  // set a dummy stage
		dummyStage.setTitle("Welcome to Hangman");
		
		sceneMap = new HashMap<String,Scene>();  // create a hashmap
		start = new Button("Login");
		Button help = new Button("Help?");
		start.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		help.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		HBox hBox = new HBox(10, port, t1);
		
		// adding elements to HBox
		hBox.setAlignment(Pos.CENTER);
		HBox hBox2 = new HBox(10, start, help);
		hBox2.setAlignment(Pos.CENTER);
		root = new VBox(100,hBox, hBox2);
		root.setAlignment(Pos.CENTER);
		borderPane = new BorderPane();
		borderPane.setCenter(root);
		
		// Creating the welcome scene
		Image newImage = new Image("Hangman.png");
		BackgroundSize Size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		borderPane.setBackground(new Background(new BackgroundImage(newImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, Size)));
		Scene welcome = new Scene(borderPane, 300,300);
		
		// If help is clicked
		help.setOnAction(e -> {
			 Dialog<String> dialog = new Dialog<String>();
		     dialog.setTitle("Help?");
		     ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
		     dialog.setContentText("A train has been highjacked by a group of intruders and the crew has captured by them.\n"
		    		 + "You need atleast 3 crew members to get you out of the train and get you to safety\n" 
		    		 + "The Crew has been locked in a room which can be only opened by if you play a game of hangman, except," 
		    		 + " you have to guess words 3 separate categories which makes it challenging.\n"
		     		 + " If you lose, the latch door beneath the crew opens and they all fall the same\n"
		    		 + "1. Pick 3 categories that you think you are familar with.\n"
		     		 + "2. Each category that you pick will have 3 attempt so you can answer 3 times\n"
		    		 + "3. If you run out of the attempts from any category, you lose.\n"
		     		 + "4. To win you must get words from all 3 categories right.\n"
		    		 + "5. You must only enter one character at a time and click check to see if it exists in the word\n"
		     		 + "6. To clear Text Field press Clear\n");
		     dialog.getDialogPane().getButtonTypes().add(type);
		     dialog.showAndWait();
		});
		
		// when login is clicked
		this.start.setOnAction(e-> {primaryStage.setScene(sceneMap.get("choose"));
		try {
		primaryStage.setTitle("This is a client");
		
		// Whenever the contents are to be resetted
		clientConnection = new Client(data->{
			Platform.runLater(()->{
			clientConnection.data = (GameStatus) data;
			label2.setText(clientConnection.data.GuessingString);
			
			// changing the graphics for the hangman.
			Button butArray[] = {Cat1, Cat2, Cat3};
			if (clientConnection.data.countWrong != 0) {
				image = "Hangman" + (clientConnection.data.countWrong) + ".jpeg";
				System.out.println(image);
				img = new Image(image);
				view = new ImageView(img);
				view.setFitHeight(300);
				view.setPreserveRatio(true);
				label6.setGraphic(view);
			}
			
			// setting the number of fails
			label4.setText("Number of Misses: " + clientConnection.data.countWrong);
			
			// checks for attempts 
			if (clientConnection.data.winFlag) {
				String temp = clientConnection.data.clientMessage;
				if (temp.equals("Category Won")) {
					label2.setText("CATEGORY WON!! PICK ANOTHER ONE");
					PauseTransition halt = new PauseTransition(Duration.seconds(3));
					halt.setOnFinished(p -> {
						boolean array[] = clientConnection.data.winArray;
						for(int i = 0; i < 3; i++) {
							//System.out.println(array[i]);
							if(!array[i]) {
								butArray[i].setDisable(false);
							}
						}
						clientConnection.data.winFlag = false;
						clientConnection.data.GuessingString = "";
						clientConnection.data.countWrong = 0;
						clientConnection.data.clientMessage = "";
					});
					halt.play();
				} else if (temp.equals("Client Wins Game")) {  // Win condition
					// Change scene if won.
					endMessage.setText("CONGRATULATIONS YOU WON THE GAME");
					endMessage.setStyle("-fx-background-color: white");
					PauseTransition halt = new PauseTransition(Duration.seconds(3));
					halt.setOnFinished(p -> {
						primaryStage.setScene(sceneMap.get("end"));
					});
					halt.play();
				}
			}
			// when all the attempts are over
			if (clientConnection.data.countWrong == 6) {
				String temp = clientConnection.data.clientMessage;
				if (temp.equals("Category Lost")) {
					PauseTransition halt = new PauseTransition(Duration.seconds(3));
					halt.setOnFinished(p -> {
						label2.setText("Category Lost " + clientConnection.data.attemptsLeft[clientConnection.data.attemptIndex] + "/3 attempts from this catgory remain");
						boolean array[] = clientConnection.data.winArray;
						// enables the button until all attempts are used.
						for(int i = 0; i < 3; i++) {
							if(!array[i]) {
								butArray[i].setDisable(false);
							}
						}
						currentCat.setStyle("-fx-background-color: HotPink");
						clientConnection.data.GuessingString = "";
						clientConnection.data.countWrong = 0;
						clientConnection.data.clientMessage = "";
					});
					halt.play();
				} else if (temp.equals("Client Looses Game")) {
					// Lose condition
					endMessage.setText("YOU COULD NOT SAVE, THE TEAM, GAME OVER");
					endMessage.setStyle("-fx-background-color: white");
					PauseTransition halt = new PauseTransition(Duration.seconds(3));
					halt.setOnFinished(p -> {
						dummyStage.setScene(sceneMap.get("end"));
					});
					halt.play();
				}
			}
		});
		}, Integer.parseInt(t1.getText()));
		} catch (NumberFormatException f) {
			BorderPane startPane = new BorderPane();
			label.setText("CLIENT FAILURE PLEASE RESTART");
			startPane.setCenter(label);
			primaryStage.setScene(new Scene(startPane, 300, 300));
			PauseTransition halt = new PauseTransition(Duration.seconds(3));
			halt.setOnFinished(p -> {
				System.exit(0);
			});
			halt.play();
			
		}
		clientConnection.start();
});
		// play again condition
		pressButton = new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				Button b = (Button)event.getSource();
				//System.out.print(b.getText());
				String s = b.getText();
				if (countCat == 0) {
					Cat1.setText(s);
				} else if (countCat == 1) {
					Cat2.setText(s);
				} else {
					Cat3.setText(s);
				}
				b.setText("CHOSEN");
				b.setDisable(true);
				catStrings[countCat] = s;
				bArray[countCat] = b;
				countCat++;
				if (countCat == 3) {
					countCat = 0;
					Cat1.setDisable(false);
					Cat2.setDisable(false);
					Cat3.setDisable(false);
					Cat1.setStyle("-fx-background-color: #ff0000; ");
					Cat2.setStyle("-fx-background-color: #ff0000; ");
					Cat3.setStyle("-fx-background-color: #ff0000; ");
					for(int i = 0; i < 3; i++) {
						bArray[i].setDisable(false);
						bArray[i].setText(catStrings[i]);
					}
					dummyStage.setScene(sceneMap.get("game"));
					label2.setText("PICK A CATEGORY");
				}
			}
		};
		
		// after category is selected
		categPress = new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				Button array[] = {Cat1, Cat2, Cat3};
				Button b = (Button)event.getSource();
				b.setStyle("-fx-background-color: lightBlue");
				for(int i = 0; i < 3; i++) {
					if (b != array[i]) {
						array[i].setDisable(true);
					} else {
						clientConnection.data.attemptIndex = i;
					}
				}
				b.setDisable(true);
				currentCat = b;
				clientConnection.data.choiceMade = true;
				clientConnection.data.currentCategory = b.getText();
				clientConnection.send(clientConnection.data);
				// setting the first graphic
				image = "Hangman0.jpeg";
				img = new Image(image);
				view = new ImageView(img);
				view.setFitHeight(300);
				view.setPreserveRatio(true);
				label6.setGraphic(view);
				
			}
			};
			
		// play again condition	
		again = new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				clientConnection.data = new GameStatus();
				primaryStage.setScene(sceneMap.get("choose"));
			}
		};
		
		// inserting the scenes in sceneMap
		sceneMap.put("welcome", welcome);
		sceneMap.put("choose", getCategories());
		sceneMap.put("game", gameScene());
		sceneMap.put("end", endScene());
		dummyStage.setScene(sceneMap.get("welcome"));
		dummyStage.show();
	}
	
	// Sets the three different categories.
	public Scene getCategories() {
		Label instruction = new Label("Pick 3 Categories");
		instruction.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		instruction.setStyle("-fx-background-color: white");
		
		b1 = new Button ("Marvel");
		b2 = new Button("Celebrities");
		HBox hbox = new HBox(10,b1,b2);
		hbox.setAlignment(Pos.CENTER);
		b3 = new Button("Food");
		b4 = new Button("Brands");
		HBox hbox2 = new HBox(10,b3,b4);
		hbox2.setAlignment(Pos.CENTER);
		b5 = new Button("Countries");
		b6 = new Button("Animals");
		HBox hbox3 = new HBox(10,b5,b6);
		hbox3.setAlignment(Pos.CENTER);
		b1.setOnAction(pressButton);
		b2.setOnAction(pressButton);
		b3.setOnAction(pressButton);
		b4.setOnAction(pressButton);
		b5.setOnAction(pressButton);
		b6.setOnAction(pressButton);
		Button array[] = {b1, b2, b3, b4, b5, b6};
		for (int i = 0; i < 6; i++) {
			array[i].setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
			array[i].setStyle("-fx-background-color: white");
		}
		VBox box = new VBox(20, instruction, hbox, hbox2, hbox3);
		box.setAlignment(Pos.CENTER);
		box.setStyle("-fx-background-color: blue");
		return new Scene(box, 300, 300);
	}
	// game scene function
	public Scene gameScene() {
		Cat1.setOnAction(categPress);
		Cat2.setOnAction(categPress);
		Cat3.setOnAction(categPress);
		Cat1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		Cat2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		Cat3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		TextField t1 = new TextField();
		t1.setPrefWidth(50);
		// All labels
		label = new Label("Categories");
		label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		label2 = new Label("");
		label2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		label3 = new Label("Guess the Letter:");
		label3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		label4 = new Label("Number of Misses: ");
		label4.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		Button check = new Button("Check");
		Button clear = new Button("Clear");
		check.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		clear.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		HBox box = new HBox(10, Cat1, Cat2, Cat3);
		box.setAlignment(Pos.CENTER);
		HBox box2 = new HBox(10, check, clear);
		box2.setAlignment(Pos.CENTER);
		HBox box3 = new HBox(10, label3, t1);
		box3.setAlignment(Pos.CENTER);
		HBox box4 = new HBox(10, label4);
		box4.setAlignment(Pos.CENTER);
		// Menu
		menu = new MenuBar();
		options = new Menu("options");
		exit = new MenuItem("exit");
		howToPlay = new MenuItem("Help");
		options.getItems().addAll(howToPlay, exit);
		menu.getMenus().addAll(options);
		
		// set menu on action
		exit.setOnAction(e -> {
			System.exit(0);
		});
		// How to play when pressed
		howToPlay.setOnAction(e -> {
			// will display a dialog box.
			 Dialog<String> dialog = new Dialog<String>();
		     dialog.setTitle("Help?");
		     ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
		     dialog.setContentText("A train has been highjacked by a group of intruders and the crew has been captured by them.\n"
		    		 + "You need atleast 3 crew members to get you out of the train and get you to safety\n" 
		    		 + "The Crew has been locked in a room which can be only opened by if you play a game of hangman," 
		    		 + " you have to guess words in 3 separate categories which makes it challenging.\n"
		     		 + " If you lose, the latch door beneath the crew opens and they all fall the same\n"
		    		 + "1. Pick 3 categories that you think you are familar with.\n"
		     		 + "2. Each category that you pick will have 3 attempts, so you can answer 3 times\n"
		    		 + "3. If you run out of the attempts from any category, you lose.\n"
		     		 + "4. To win you must get words from all 3 categories right.\n"
		    		 + "5. You must only enter one character at a time and click check to see if it exists in the word\n"
		     		 + "6. To clear Text Field press Clear\n");
		     dialog.getDialogPane().getButtonTypes().add(type);
		     dialog.showAndWait();
		});
		// VBox
		VBox gameVBox = new VBox(10, label, box, label2, box3, box2, box4, label6);
		gameVBox.setAlignment(Pos.CENTER);
		// Scene Changer
		check.setOnAction(e->{
			String temp = t1.getText().toLowerCase();
			if(temp.length() == 1) {
				clientConnection.data.guess_letter = temp.charAt(0);
				clientConnection.data.sentChar = true;
				clientConnection.send(clientConnection.data);
			}
			t1.clear();
			});
		clear.setOnAction(e -> t1.clear());
		// Border Pane
		gamePane = new BorderPane();
		gamePane.setTop(menu);
		gamePane.setCenter(gameVBox);
		gamePane.setStyle("-fx-background-color: aquamarine");
		return new Scene(gamePane, 512, 512);
	}
	
	// win/lose Scene
	public Scene endScene() {
		Image newImage = new Image("gameOver.jpg");
		BackgroundSize Size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		Button playAgain = new Button("playAgain");
		Button exit = new Button("Exit");
		exit.setOnAction(e -> System.exit(0));
		endMessage.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		HBox hbox = new HBox(10, playAgain, exit);
		hbox.setAlignment(Pos.CENTER);
		VBox vbox = new VBox(100, endMessage, hbox);
		vbox.setAlignment(Pos.CENTER);
		playAgain.setOnAction(again);
		BorderPane pane = new BorderPane();
		pane.setCenter(vbox);
		pane.setBackground(new Background(new BackgroundImage(newImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, Size)));
		return new Scene(pane, 400, 400);
	}

}
