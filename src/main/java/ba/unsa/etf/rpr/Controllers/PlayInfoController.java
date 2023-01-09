package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.domain.Plays;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class PlayInfoController extends ShowPlaysController{
    Plays p;
    public void setString(ObservableList<String>list) {
        string.getItems().clear();
        string.setItems(list);
    }

    public static ListView<String> string;

    public PlayInfoController() {

    }
    @FXML
    public void initialize()  {

    }


}
