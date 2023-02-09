package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.PlaysManager;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

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
        Platform.runLater(() -> {
            SkinBase<ChoiceBox<String>> skin = (SkinBase<ChoiceBox<String>>) choice.getSkin();
            for (Node child : skin.getChildren()) {
                if (child instanceof Label) {
                    Label label = (Label) child;
                    if (label.getText().isEmpty()) {
                        label.setText("Genre");
                        Font f=new Font("Bodoni MT Bold Italic",12);
                        label.setFont(f);
                    }
                    return;
                }
            }
        });
        maxprice.setText(Integer.toString((int)slider.getValue()));
        slider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        maxprice.setText(Integer.toString((int) slider.getValue()));
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
            playsTable.setItems(FXCollections.observableList(PlaysManager.randomPlay().searchByPlaynameandPriceandGenre(search.getText(),Integer.parseInt(maxprice.getText()),choice.getValue())));
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