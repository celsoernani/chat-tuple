package chat;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;


import java.io.IOException;

import org.jspace.RemoteSpace;


public class FXMLLoginController  {
    @FXML private TextField classroomNameTextfield;
    @FXML private TextField usernameTextfield;
    @FXML private BorderPane borderPane;
    @FXML private MenuButton classesRooms;
    @FXML private Label feedbackCreate;
    public static FXMLChatController con;
    public String classRoomselected; 
    public Client client;
	public Server server;

    EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() { 
        public void handle(ActionEvent e) 
        { 
        	String nameSelected = ((MenuItem)e.getSource()).getText();
        	classesRooms.setText(nameSelected);
        	classRoomselected = nameSelected;
        } 
    }; 		
       
    public void createClass() throws IOException {
        try {
        		 String roomname = classroomNameTextfield.getText();
                 this.server.addClassRoom(roomname);
                 MenuItem classroom = new MenuItem(roomname);
                 classroom.setOnAction(event1);
                 classesRooms.getItems().add(classroom); 
                 feedbackCreate.setText("Sala criada com sucesso !");
           
        }
	    catch (Exception e){
            feedbackCreate.setText("Espaço já criado !");
	    }
    }
    public void loginButtonAction() throws Exception {
    	if(!classroomNameTextfield.getText().isEmpty()) { 
    		String username = usernameTextfield.getText();
            client = new Client(username,classRoomselected);

            try {
            	server.addClient(client);
    			client.run();
    			 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLChat.fxml"));
    	            Parent root1 = (Parent) fxmlLoader.load();
    	            con =(FXMLChatController)fxmlLoader.getController();
    	            System.out.println(con);
    	            con.setUsernameLabel(usernameTextfield.getText());
    	            con.setRoomLabel(classRoomselected);
    	            con.setClient(client);
    	            RemoteSpace chat = new RemoteSpace("tcp://127.0.0.1:9001/"+classRoomselected+"?keep");
    	            Listener listener = new Listener(chat,client,con);
    	            listener.run();
    	            Stage stage = new Stage();
    	            stage.setTitle("ch@T TUPLE");   
    	            stage.setScene(new Scene(root1));  
    	            stage.show();

            } catch (Exception e){
    			// TODO Auto-generated catch block
                feedbackCreate.setText("Usuário já está no chat !");
    		}        
           
    	}else { 
           feedbackCreate.setText("Digite o nome sala !");
    	}
    }
   
    @FXML
    public void initialize() {
    	connectToServer();
    
    }
    
    private void connectToServer(){
        System.out.println("Conectando ao servidor");
		this.server = new Server();
		// server.run();
    }
    
   


    /* Terminates Application */
    public void closeSystem(){
        Platform.exit();
        System.exit(0);
    }

}