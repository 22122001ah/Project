package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class noviProzor {
    public Label labels;
    public void zatvoriProzorPropuhJe(ActionEvent actionEvent) {
        Stage stage = (Stage) labels.getScene().getWindow();
        stage.close();
    }

}
