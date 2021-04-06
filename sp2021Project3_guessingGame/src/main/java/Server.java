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
					    		data.choiceMade = false;
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


	
	

	
