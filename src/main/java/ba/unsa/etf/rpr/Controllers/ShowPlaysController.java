package ba.unsa.etf.rpr.Controllers;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ShowPlaysController {
    @FXML
    public void initialize() {

    }
 RegisterController m1;
    LoginController m2;

    public void setM1(RegisterController m1) {
        this.m1 = m1;
    }

    public void setM2(LoginController m2) {
        this.m2 = m2;
    }

    public void PlayDesription(ActionEvent actionEvent) throws IOException {
        try{     Button numberButton = (Button) actionEvent.getTarget();
            Stage Secondstage=new Stage();
            FXMLLoader fl=new FXMLLoader(getClass().getResource("/fxml/Info.fxml"));
            Parent root =fl.load();
            InfoController noviprozor=fl.getController();
            noviprozor.setText(DaoFactory.playsDao().searchByPlayName(numberButton.getText()).toString());
            Secondstage.setTitle("Play description");
            Secondstage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
            Secondstage.show();

        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void buy(ActionEvent actionEvent) throws IOException, PlaysException {
     if(m1==null && m2==null){
         new Alert(Alert.AlertType.NONE,"You need to have an account and be logged in in order to buy tickets.", ButtonType.OK).show();
     }
     else if(m2.getU()==null){
         new Alert(Alert.AlertType.NONE,"You need to have an account and be logged in in order to buy tickets.", ButtonType.OK).show();
     }
      else{  Button numberButton = (Button) actionEvent.getTarget();
        Stage secondstage=new Stage();
        FXMLLoader fl=new FXMLLoader(getClass().getResource("/fxml/BuyTickets.fxml"));
        Parent root=fl.load();
        BuyTicketsController buyTicketsController=fl.getController();
        buyTicketsController.setPrice(DaoFactory.playsDao().searchByPlayName(numberButton.getText()).get(0).getPrice());
        buyTicketsController.setName(numberButton.getText());
        secondstage.setTitle("Buy tickets");
        secondstage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        secondstage.show();}
    }

}
