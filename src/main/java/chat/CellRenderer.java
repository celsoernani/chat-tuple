package chat;

import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * A Class for Rendering users images / name on the userlist.
 */
class CellRenderer implements Callback<ListView<Client>,ListCell<Client>>{
        public ListCell<Client> call(ListView<Client> p) {

        ListCell<Client> cell = new ListCell<Client>(){

            @Override
            protected void updateItem(Client client, boolean bln) {
                super.updateItem(client, bln);
                setGraphic(null);
                setText(null);
                if (client != null) {
                    HBox hBox = new HBox();

                    Text name = new Text("["+client.classRoom+"]"+client.userName);


                    hBox.getChildren().addAll(name);
                    hBox.setAlignment(Pos.CENTER_LEFT);

                    setGraphic(hBox);
                }
            }
        };
        return cell;
    }
}