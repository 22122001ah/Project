package ba.unsa.etf.rpr.Controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class InfoController {
    public InfoController() {
    }
 public Text text=new Text();



    public void setText(String text) {
        this.text.setText(text);
    }

    @FXML
    void initialize(){

    }
}
