package chat;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Chat extends Application {
	public Server server;
	
    private void connectToServer(){
  	  
			System.out.println("Conectando ao servidor");
			server = new Server();
			server.run();
    }
	
@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();	
    	connectToServer();
	}

	public static void main(String[] args) {
		launch(args);
	}
	

   

}

