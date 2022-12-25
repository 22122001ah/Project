package ba.unsa.etf.rpr.Controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

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
            PlayInfoController p=new PlayInfoController();
            p.setString(list);
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
