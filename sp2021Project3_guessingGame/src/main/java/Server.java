import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.scene.control.ListView;
/*
 * Clicker: A: I really get it    B: No idea what you are talking about
 * C: kind of following
 */

public class Server{

	int count = 1;	
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	TheServer server;
	private Consumer<Serializable> callback;
	int port;
	
	Server(Consumer<Serializable> call, int assignPort){
	
		callback = call;
		port = assignPort;
		server = new TheServer();
		server.start();
	}
	
	
	public class TheServer extends Thread{
		
		public void run() {
		
			try(ServerSocket mysocket = new ServerSocket(port);){
		    System.out.println("Server is waiting for a client!");
		  
			
		    while(true) {
		
				ClientThread c = new ClientThread(mysocket.accept(), count);
				callback.accept("client has connected to server: " + "client #" + count);
				clients.add(c);
				c.start();
				
				count++;
				
			    }
			}//end of try
				catch(Exception e) {
					callback.accept("Server socket did not launch");
				}
			}//end of while
		}
	

		class ClientThread extends Thread{
			
		
			Socket connection;
			int count;
			ObjectInputStream in;
			ObjectOutputStream out;
			
			ClientThread(Socket s, int count){
				this.connection = s;
				this.count = count;	
			}
			
			public void updateClients(GameStatus message) {
//				for(int i = 0; i < clients.size(); i++) {
//					ClientThread t = clients.get(i);
					try {
					 this.out.writeObject(message);
					}
					catch(Exception e) {}
//  			}
			}
			
			public void run(){
					
				try {
					in = new ObjectInputStream(connection.getInputStream());
					out = new ObjectOutputStream(connection.getOutputStream());
					connection.setTcpNoDelay(true);	
				}
				catch(Exception e) {
					System.out.println("Streams not open");
				}
				
//				updateClients("new client on server: client #"+count);
				callback.accept("new client on server: client #"+ count);
				 while(true) {
					    try {
					    	GameStatus data = (GameStatus)in.readObject();
					    	callback.accept("client: " + count + " sent: " + data.guess_letter);
					    	if (data.choiceMade) {
					    		data.getWord(data.currentCategory);
					    		data.makeGuessingWord();
					    		callback.accept("Category Picked " + data.currentCategory + " Word to guess : " + data.wordToGuess);
					    		data.attemptsLeft[data.attemptIndex]--;
					    		data.choiceMade = false;
					    	}
					    	if(data.sentChar) {
					    		if(data.checkExists(data.guess_letter)) {
					    			callback.accept("Letter " + data.guess_letter + " exists");
					    			if (data.winFlag) {
					    				data.clientMessage = "Category Won";
					    				if (data.winCounter == 3) {
					    					data.clientMessage = "Client Wins Game";
					    				}
					    			}
					    		} else {
					    			callback.accept("Letter " + data.guess_letter + " does not exist");
//					    			if (data.countWrong == 6) {
//					    				callback.accept("Client: " + count + "lost");
//					    			}
					    			data.countWrong++;
					    			callback.accept("Misses = " + data.countWrong);
					    			if (data.countWrong == 6) {
					    				callback.accept("Client: " + count + "lost");
					    				data.clientMessage = "Category Lost";
					    				if (data.attemptsLeft[data.attemptIndex] == 0) {
					    					data.clientMessage = "Client Looses Game";
					    				}
						    		}
					    		}
					    		data.sentChar = false;
					    		data.validChar = false;
					    	}
					    	//updateClients("client #"+count+" said: "+data);
					    	// callback.accept("client #"+count+" said: "+data);
					    	updateClients(data);
					    	}
					    catch(Exception e) {
					    	callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
			
					    	callback.accept("Client #"+count+" has left the server!");
					    	clients.remove(this);
					    	break;
					    }
					}
				}//end of run
			
			
		}//end of client thread
}
