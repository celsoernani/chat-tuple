package chat;

import java.net.URI;

import org.jspace.FormalField;
import org.jspace.RemoteSpace;
import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;
import java.util.*;
import java.util.function.Predicate;

public class Server {
	
	static SpaceRepository repository;
	String uri = "tcp://127.0.0.1:9001/?keep";
	static public String nameRoom;
    private List<Client> clientList;



    
    Server(){
        try {
        	this.clientList = new ArrayList<Client>();
        	repository = new SpaceRepository();
			URI myUri = new URI(uri);
			String gateUri = "tcp://" + myUri.getHost() + ":" + myUri.getPort() +  "?keep" ;
			repository.addGate(gateUri);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void addClassRoom(String nameClass) { 
    		SequentialSpace chat = new SequentialSpace();
    		repository.add(nameClass, chat);
    		nameRoom = nameClass;
            System.out.println("Espaco " + nameClass + " criado com sucesso");
    }
    
    
    public List<Client> getUSers() { 
		return clientList;
    }
    
    
    public void addClient(final Client client) throws Exception { 
    		
    	  Client c = clientList.stream().filter(new Predicate<Client>() {
			public boolean test(Client c) {
				return c.userName.equals(client.userName);
			}
		}).findFirst().orElse(null);

    	  if(c != null){ 
              throw new Exception("Nome de usuario ja esta sendo usado");
          }
          else{
	         System.out.println("Novo usuario " + client.userName + "na sala" + client.classRoom);
	      	 this.clientList.add(client);
          }
    }
    
	public  void run() {
		new Thread() {
		    @Override
		    public void run() {
		    	while (true) {
				}
		    }
		  }.start();
	}
	
}