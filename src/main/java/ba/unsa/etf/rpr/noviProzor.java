package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class noviProzor {
    public Label labels;
    public TextField fieldUsername,fieldFirstname,fieldLastname;
    public PasswordField fieldPass;

    public void zatvoriProzorPropuhJe(ActionEvent actionEvent) {
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
        if (fieldUsername.getText().isEmpty()  || fieldFirstname.getText().isEmpty() || fieldLastname.getText().isEmpty() || fieldPass.getText().isEmpty())
            return;

        Stage stage = (Stage) labels.getScene().getWindow();
        stage.close();


    }
}
