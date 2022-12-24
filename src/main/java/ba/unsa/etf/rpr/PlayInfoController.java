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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class PlayInfoController extends ShowPlaysController{
    Plays play;
    ObservableList<String> l;


    public void setString(ListView<String> string) {
        this.string = string;
    }

    public ListView<String> string;

    public PlayInfoController() {

    }
    @FXML
    public void initialize()  {

    }


}
