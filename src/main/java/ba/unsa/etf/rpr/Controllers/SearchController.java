package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.PlaysManager;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Date;

/**
 * Controller for managing Plays
 */
public class SearchController{
    private final PlaysManager playsManager= new PlaysManager();
    private final PlaysManager PlaysManager=new PlaysManager();
    public TableView playsTable;
    public TextField search;
    public Slider slider;
    public Label maxprice;
    public ChoiceBox<String> choice=new ChoiceBox<>();

    public TableColumn<Plays, String> playsColumn;
    public TableColumn<Plays, Date> createdColumn;
    public TableColumn<Plays,Integer> price;
    public TableColumn<Plays,String> genre;



    public void initialize()throws PlaysException {
        playsColumn.setCellValueFactory(new PropertyValueFactory<Plays, String>("play_name"));
        createdColumn.setCellValueFactory(new PropertyValueFactory<Plays, Date>("date"));
        price.setCellValueFactory(new PropertyValueFactory<Plays,Integer>("price"));
        genre.setCellValueFactory(new PropertyValueFactory<Plays,String>("genre"));
        choice.setItems(FXCollections.observableList(playsManager.getAllGenres()));
        maxprice.setText(Integer.toString((int)slider.getValue()));
        SimpleStringProperty p1=new SimpleStringProperty(String.valueOf(slider.getValue()));
        slider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        int k=(int) slider.getValue();
                        maxprice.setText(Integer.toString(k));
                    }
                }
        );
        refreshPlays();
    }


    /**
     * search plays event handler
     * @param event
     */
    public void searchPlays(ActionEvent event){
      search();
    }
    public void search(){
        try {
            playsTable.setItems(FXCollections.observableList(PlaysManager.searchByPlayName(search.getText())));
            playsTable.refresh();
        } catch (PlaysException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    public void ENTER(KeyEvent keyEvent){
        if(keyEvent.getCode()== KeyCode.ENTER) {
            search();
        }
    }

    /**
     * fetch plays from DB
     */
    private void refreshPlays(){
        try {
            ArrayList<Plays> p=new ArrayList<>();
            for(int i=0;i<5;i++)
                p.add(playsManager.randomPlay());
            playsTable.setItems(FXCollections.observableList(p));
            playsTable.refresh();
        } catch (PlaysException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}