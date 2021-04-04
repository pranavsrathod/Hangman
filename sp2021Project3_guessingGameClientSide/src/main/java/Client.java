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
	//private String ipAddress;
	private int port;
	WordInfo wordsClass;
	
	private Consumer<Serializable> callback;
	
	Client(Consumer<Serializable> call, int assignPort){
		wordsClass = new WordInfo();
		callback = call;
//		String assignIp
//		ipAddress = assignIp;
		port = assignPort;
	}
	
	public void run() {
		
		try {
		socketClient= new Socket("127.0.0.1",port);
	    out = new ObjectOutputStream(socketClient.getOutputStream());
	    in = new ObjectInputStream(socketClient.getInputStream());
	    socketClient.setTcpNoDelay(true);
		}
		catch(Exception e) {
			System.out.println("CLIENT THREAD FAILED TO LAUNCH");
		}
		
		while(true) {
			 
			try {
			String message = in.readObject().toString();
			callback.accept(message);
			}
			catch(Exception e) {}
		}
	
    }
	
	public void send(WordInfo object) {
		
		try {
			out.writeObject(object);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}