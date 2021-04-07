
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GuiServer extends Application{

	
	TextField s1,s2,s3,s4, c1;
	Button serverChoice,clientChoice,b1,helpButtons;
	HashMap<String, Scene> sceneMap;
	GridPane grid;
	HBox buttonBox;
	HBox buttons;
	VBox clientBox;
	Scene startScene;
	BorderPane startPane;
	Server serverConnection;
	
	ListView<String> listItems, listItems2;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		c1 = new TextField();
		c1.setPrefWidth(100);
		primaryStage.setTitle("Welcome to the Server");
		Label label = new Label("Enter Port Number:");
		label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		this.serverChoice = new Button("Login");
		this.helpButtons = new Button("Help");
		serverChoice.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		helpButtons.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		buttonBox = new HBox(10, label, c1);
		buttonBox.setAlignment(Pos.CENTER);
		buttons = new HBox(10, serverChoice, helpButtons);
		buttons.setAlignment(Pos.CENTER);
		this.serverChoice.setOnAction(e->{ primaryStage.setScene(sceneMap.get("server"));
											primaryStage.setTitle("This is the Server");
				serverConnection = new Server(data -> {
					Platform.runLater(()->{
						listItems.getItems().add(data.toString());
					});

				},Integer.parseInt(c1.getText()));
											
		});
		this.helpButtons.setOnAction(e -> {
			 Dialog<String> dialog = new Dialog<String>();
		     dialog.setTitle("Help?");
		     ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
		     dialog.setContentText("1. Enter either 5555 or 5556 as the input to login\n"
		    		 + "2. Do the same for both server and client\n");
		     dialog.getDialogPane().getButtonTypes().add(type);
		     dialog.showAndWait();
		});
		VBox vbox = new VBox(10, buttonBox, buttons);
		vbox.setAlignment(Pos.CENTER);
		startPane = new BorderPane();
		startPane.setCenter(vbox);
		startPane.setStyle("-fx-background-color: lightgreen");
		startScene = new Scene(startPane, 300,300);
		
		listItems = new ListView<String>();
		listItems2 = new ListView<String>();
//		b1 = new Button("Send");
//		b1.setOnAction(e->{clientConnection.send(c1.getText()); c1.clear();});
		
		sceneMap = new HashMap<String, Scene>();
		
		sceneMap.put("server",  createServerGui());
//		sceneMap.put("client",  createClientGui());
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
		
		 
		
		primaryStage.setScene(startScene);
		primaryStage.show();
		
	}
	
	public Scene createServerGui() {
		Image newImage = new Image("server.gif");
		BackgroundSize Size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setBackground(new Background(new BackgroundImage(newImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, Size)));
		pane.setCenter(listItems);
	
		return new Scene(pane, 500, 500);
		
		
	}
	
	public Scene createClientGui() {
		
		clientBox = new VBox(10, c1,b1,listItems2);
		clientBox.setStyle("-fx-background-color: blue");
		return new Scene(clientBox, 400, 300);
		
	}

}
