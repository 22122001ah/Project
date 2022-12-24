package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.domain.Plays;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class PlayInfoController extends ShowPlaysController{
    Plays play;
    public TableView<String> stringTableView;
    ObservableList<String> l;
    PlayInfoController showPlaysController;

    public PlayInfoController() {
    }

    public PlayInfoController(String des) {
    }

    @FXML
    public void initialize()  {

        try {
            play=new Plays();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ShowPlaysController s=new ShowPlaysController();
        play=play.searchByPlayName(s.getList().get(0));
        stringTableView.getItems().clear();
        l= FXCollections.observableArrayList(play.getPlay_name());
        stringTableView.refresh();
        stringTableView.setItems(l);
String des=stringTableView.getSelectionModel().getSelectedItem();
         showPlaysController = new PlayInfoController(des);
    }
    PlayInfoController getShowPlaysController(){
        return this.showPlaysController;
    }

}
