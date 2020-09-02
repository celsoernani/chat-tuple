package chat;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class FXMLChatController implements Initializable {

    @FXML private TextArea messageBox;
    @FXML private Label usernameLabel;
    @FXML private Label roomNameID;
    // @FXML private ListView userList;
    @FXML private ImageView userImageView;
    @FXML private Button buttonSend;
    @FXML ListView<HBox> chatPane;
    @FXML BorderPane borderPane;
    public Client client;


    public void sendButtonAction() throws IOException {
        String msg = messageBox.getText();
        if (!messageBox.getText().isEmpty()) {
            this.client.sendMessage(msg);
            messageBox.clear();
        }
    }

    public void setUsernameLabel(String username) {
        this.usernameLabel.setText(username);
    }
    

    public void setRoomLabel(String roomname) {
        this.roomNameID.setText(roomname);
    }
    
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public synchronized void addToChat(final Message msg) {
        System.out.println("aqui" + this.usernameLabel);

        final Task<HBox> othersMessages = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {
                BubbledLabel bl6 = new BubbledLabel();
                bl6.setText(msg.getName() + ": " + msg.getMsg());
                bl6.setBackground(new Background(new BackgroundFill(Color.WHITE,null, null)));
                HBox x = new HBox();
                bl6.setBubbleSpec(BubbleSpec.FACE_LEFT_CENTER);
                x.getChildren().addAll(bl6);
                return x;
            }
        };

      othersMessages.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
		public void handle(WorkerStateEvent event) {
		    chatPane.getItems().add(othersMessages.getValue());
		 }
	});

        final Task<HBox> yourMessages = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {
                BubbledLabel bl6 = new BubbledLabel();
                bl6.setText(msg.getMsg());
                bl6.setBackground(new Background(new BackgroundFill(Color.CYAN,
                        null, null)));
                HBox x = new HBox();
                x.setMaxWidth(chatPane.getWidth() - 20);
                x.setAlignment(Pos.TOP_RIGHT);
                bl6.setBubbleSpec(BubbleSpec.FACE_RIGHT_CENTER);
                x.getChildren().addAll(bl6);
                return x;
            }
        };
       yourMessages.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
		public void handle(WorkerStateEvent event) {
			chatPane.getItems().add(yourMessages.getValue());
		}
	});

        if (msg.getName().equals(client.userName)) {
            Thread t2 = new Thread(yourMessages);
            t2.setDaemon(true);
            t2.start();
        } else {
          Thread t = new Thread(othersMessages);
            t.setDaemon(true);
            t.start();
        }
    }


  

    public void sendMethod(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            sendButtonAction();
        }
    }

    @FXML
    public void closeApplication() {
        Platform.exit();
        System.exit(0);
    }


    public void initialize(URL location, ResourceBundle resources) {
     

        /* Added to prevent the enter from adding a new line to inputMessageBox */
        messageBox.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
			    if (ke.getCode().equals(KeyCode.ENTER)) {
			        try {
			            sendButtonAction();
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
			        ke.consume();
			    }
			}
		});

    }
    /*
    public void setUserList(Message msg) {
        logger.info("setUserList() method Enter");
        Platform.runLater(() -> {
            ObservableList<User> users = FXCollections.observableList(msg.getUsers());
            userList.setItems(users);
            userList.setCellFactory(new CellRenderer());
            setOnlineLabel(String.valueOf(msg.getUserlist().size()));
        });
        logger.info("setUserList() method Exit");
    */
    /*
    public void logoutScene() {
        Platform.runLater(new Runnable() {
			public void run() {
			    FXMLLoader fmxlLoader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));
			    Parent window = null;
			    try {
			        window = (Pane) fmxlLoader.load();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			    Stage stage = MainLauncher.getPrimaryStage();
			    Scene scene = new Scene(window);
			    stage.setMaxWidth(350);
			    stage.setMaxHeight(420);
			    stage.setResizable(false);
			    stage.setScene(scene);
			    stage.centerOnScreen();
			}
		});
    }
        */

}
