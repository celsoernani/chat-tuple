 package chat;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainLauncher extends Application {
	
	public Server server;
    private static Stage primaryStageObj;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStageObj = primaryStage;
    	// connectToServer();
		Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        primaryStage.setTitle("Tuple Ch@t !");
        Scene mainScene = new Scene(root, 350, 420);
        mainScene.setRoot(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent e) {
				Platform.exit();
			}
		});
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStageObj;
    }
    /*
    private void connectToServer(){
    	  
        System.out.println("Conectando ao servidor");
		server = new Server();
		server.run();
    }*/
    
}