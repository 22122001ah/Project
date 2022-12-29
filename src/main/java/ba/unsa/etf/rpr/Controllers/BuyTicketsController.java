package ba.unsa.etf.rpr.Controllers;

import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.text.Text;

public class BuyTicketsController {
    public Spinner<Integer> spinner=new Spinner<>();
    public Text price=new Text();
    public ChoiceBox<String> choiceBox=new ChoiceBox<>();
    public Label name=new Label();
    public void setName(String ime){
        name.setText(ime);
    }
    public void setPrice(int text) {
        IntegerBinding Price= ReadOnlyIntegerProperty.readOnlyIntegerProperty(spinner.valueProperty()).multiply(text);
        price.textProperty().bind(Price.asString());
    }
    @FXML
    public void initialize(){

        String location[]={"Sarajevo","Banja Luka","Tuzla","Zenica","Mostar","Bihać","Trebinje","Livno"};
        choiceBox.setItems(FXCollections.observableArrayList(location));
        int initialValue = 1;
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, initialValue);
        spinner.setValueFactory(valueFactory);
    }
}
