package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.domain.Plays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayInfoController extends ShowPlaysController{
    Plays p;
    public void setString(ObservableList<String>list) {
        string.getItems().clear();
        string.setItems(list);
    }

    public static ListView<String> string;

    public PlayInfoController() {
        try {
            p=new Plays();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Plays>plays=new ArrayList<>();
        try {
            plays= (ArrayList<Plays>) p.getAll();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        ArrayList<String> n=new ArrayList<>();
        ObservableList<String> names = null;
        for(int i=0;i< plays.size();i++)
            n.add(plays.get(i).getPlay_name());
        names=FXCollections.observableArrayList(n);
        string.refresh();
        string.setItems( names);
    }
    @FXML
    public void initialize()  {

    }


}
