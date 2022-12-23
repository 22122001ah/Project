package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class MainController  {
    public Label labels;
    public TextField fieldUsername;
    @FXML
    public void initialize() {
}


    public void RegisterBttn(ActionEvent actionEvent) throws IOException {
       try{

        Stage Secondstage=new Stage();
        FXMLLoader fl=new FXMLLoader(getClass().getResource("/fxml/Register.fxml"));
        Parent root =fl.load();
        RegisterController noviprozor=fl.getController();
        Secondstage.setTitle("Register");
       Secondstage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        //Alert alert=new Alert(Alert.AlertType.INFORMATION);
        //alert.setTitle("Pozdrav");
        //alert.show();
Secondstage.show();}
       catch(Exception e){
           System.out.println(e);
       }
    }
    public void LoginBttn(ActionEvent actionEvent) throws IOException {
        try{
            Stage Secondstage=new Stage();
            FXMLLoader fl=new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent root =fl.load();
            LoginController noviprozor=fl.getController();
            Secondstage.setTitle("Login");
            Secondstage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
            //Alert alert=new Alert(Alert.AlertType.INFORMATION);
            //alert.setTitle("Pozdrav");
            //alert.show();
            Secondstage.show();}
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void showPlays(ActionEvent actionEvent) throws IOException{
        try{
            Stage Secondstage=new Stage();
            FXMLLoader fl=new FXMLLoader(getClass().getResource("/fxml/showPlays.fxml"));
            Parent root =fl.load();
            ShowPlaysController noviprozor=fl.getController();
            Secondstage.setTitle("Plays");
            Secondstage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
            //Alert alert=new Alert(Alert.AlertType.INFORMATION);
            //alert.setTitle("Pozdrav");
            //alert.show();
            Secondstage.show();}
        catch(Exception e){
            System.out.println(e);
        }
    }

}