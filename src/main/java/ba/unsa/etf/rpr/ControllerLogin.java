package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ControllerLogin {
    public TextField fieldUsername;
    @FXML
    public void initialize() {
        fieldUsername.getStyleClass().add("poljeNijeIspravno");
        fieldUsername.textProperty().addListener(new ChangeListener<String>() {
             @Override
             public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                 if (fieldUsername.getText().trim().isEmpty()) {
                     fieldUsername.getStyleClass().removeAll("poljeJeIspravno");
                     fieldUsername.getStyleClass().add("poljeNijeIspravno");
                 } else {
                     fieldUsername.getStyleClass().removeAll("poljeNijeIspravno");
                     fieldUsername.getStyleClass().add("poljeJeIspravno");
                 }
             }
         });

}



    public void ButtonClick(ActionEvent actionEvent) {
        if (fieldUsername.getText().isEmpty()){
            fieldUsername.getStyleClass().add("poljeNijeIspravno");
            return;
        }
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pozdrav");

        alert.show();

    }
}