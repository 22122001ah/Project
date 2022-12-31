package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.exceptions.PlaysException;
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
    public Label Price1=new Label();
    public Label date=new Label();
    public void setName(String ime){
        name.setText(ime);
        try {
            date.setText(String.valueOf(DaoFactory.playsDao().searchByPlayName(ime).getDate()));
        } catch (PlaysException e) {
            throw new RuntimeException(e);
        }
    }
    public void setPrice(int text) {
        Price1.setText(String.valueOf(text));
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
