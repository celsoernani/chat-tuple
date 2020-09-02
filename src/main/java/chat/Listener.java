package chat;

import java.io.*;
import java.util.List;

import org.jspace.FormalField;
import org.jspace.RemoteSpace;



public class Listener {
    public FXMLChatController controller;
    public static Client client;
    public RemoteSpace chat;
    public List<Client> clientList;


   
    
    public Listener(RemoteSpace spaceNew, Client client,FXMLChatController controller,  List<Client> users) {
        this.chat = spaceNew;
        Listener.client = client;
        this.controller = controller;
        this.clientList = users;
    }

	public void run() {
		controller.setUserList(this.clientList);
		new Thread() {
		    @Override
		    public void run() {
		    	  try {
		              System.out.println("Procurando pelo servico JavaSpace...");
		              if (chat == null) {
		                  System.out.println("O servico JavaSpace nao foi encontrado. Encerrando...");
		                  System.exit(-1);
		              }
		              System.out.println("O servico JavaSpace foi encontrado.");
		              while (true) {
		              	Object[] t;
		  				try {
		  					  t = chat.get(new FormalField(String.class), new FormalField(String.class), new FormalField(String.class));
		  					  Message template = new Message();
			                  template.setName(t[0].toString());
			                  template.setMsg(t[1].toString());
			                  template.setClassroom(t[2].toString());
		  					  System.out.println("enviou: " + t[0]  + "msg: " + t[1] + "sala: " + t[2]);
		  					  controller.addToChat(template);
		  				} catch (InterruptedException e) {
		  					// TODO Auto-generated catch block
		  					e.printStackTrace();
		  				}
		                 
		                 
		              }
		          } catch (Exception e) {
		              e.printStackTrace();
		          }
		          throw new UnsupportedOperationException("Deu merda !"); 
		    }
		  }.start();
	}

    
    public static void send(String msg) throws IOException {
    	client.sendMessage(msg);
    }

}
