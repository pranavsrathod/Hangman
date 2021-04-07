import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;



public class Client extends Thread{

	
	Socket socketClient;
	
	ObjectOutputStream out;
	ObjectInputStream in;
	int portNumber;
	GameStatus data = new GameStatus();
	
	private Consumer<Serializable> callback;
	
	Client(Consumer<Serializable> call, int port){
		portNumber = port;
		callback = call;
	}
	
	public void run() {
		
		try {
		socketClient= new Socket("127.0.0.1",portNumber);
	    out = new ObjectOutputStream(socketClient.getOutputStream());
	    in = new ObjectInputStream(socketClient.getInputStream());
	    socketClient.setTcpNoDelay(true);
		}
		catch(Exception e) {
			System.out.println("Failed!!");
		}
		
		while(true) {
			 
			try {
			GameStatus message = (GameStatus) in.readObject();
			callback.accept(message);
			}
			catch(Exception e) {}
		}
	
    }
	
	public void send(GameStatus data) {
		
		try {
			out.writeObject(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
