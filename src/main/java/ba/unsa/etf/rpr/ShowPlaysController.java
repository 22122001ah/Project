package ba.unsa.etf.rpr;
import ba.unsa.etf.rpr.domain.Plays;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ShowPlaysController {
    public static ObservableList<String> list;
    public static int brojac=0;
    @FXML
    public void initialize() {

    }
    public ObservableList<String>getList(){
        return this.list;
    }
    public int getBrojac(){
        return this.brojac;
    }
    public void PlayDesription(ActionEvent actionEvent) throws IOException {
        try{        Button numberButton = (Button) actionEvent.getTarget();
            list= FXCollections.observableArrayList(numberButton.getText());

            ListView<String> s=new ListView<>();
            PlayInfoController p=new PlayInfoController();
            s.setItems(list);
            p.setString(s);
            Stage Secondstage=new Stage();
            FXMLLoader fl=new FXMLLoader(getClass().getResource("/fxml/InfoPlays.fxml"));
            Parent root =fl.load();
            PlayInfoController noviprozor=fl.getController();
            Secondstage.setTitle("Login");
            Secondstage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
            Secondstage.show();

        }
        catch(Exception e){
            System.out.println(e);
        }
    }


}
