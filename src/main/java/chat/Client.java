package chat;

import org.jspace.RemoteSpace;
import java.io.IOException;
import java.net.UnknownHostException;

public class Client {
public String userName; 
	public String classRoom; 
	static String message;
	public RemoteSpace chat;
	
	public Client(String userName, String classRoom) {
		  this.userName=userName;
		  this.classRoom=classRoom; 
	 }

   public void createChat(String uri) throws IOException, IOException {
		System.out.println(uri);	
        this.chat = new RemoteSpace(uri);
    }
   
   public void sendMessage(String message) {
		try {	
			this.chat.put(userName, message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

    }
	   
	public void run() throws InterruptedException {
			try {
				String uri = "tcp://127.0.0.1:9001/"+classRoom+"?keep";
				// Connect to the remote chat space 
				System.out.println("Connecting to chat space " + uri + "...");
				createChat(uri);		

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	   
}