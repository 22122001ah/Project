package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.PlaysManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class MainController  {
    RegisterController noviprozor2;
    LoginController noviprozor1;
    @FXML
    public void initialize() throws PlaysException {}


    public void RegisterBttn(ActionEvent actionEvent) throws IOException {
        try{

            Stage Secondstage=new Stage();
            FXMLLoader fl=new FXMLLoader(getClass().getResource("/fxml/Register.fxml"));
            Parent root =fl.load();
            noviprozor2=fl.getController();
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
            noviprozor1=fl.getController();
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
            noviprozor.setM1(noviprozor2);
            noviprozor.setM2(noviprozor1);
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
    public void PlayDesription(ActionEvent actionEvent) throws IOException {
        try{
            Button numberButton = (Button) actionEvent.getTarget();
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
       if(noviprozor2==null && noviprozor1==null)
       {
           new Alert(Alert.AlertType.NONE,"You need to have an account and be logged in in order to buy tickets.",ButtonType.OK).show();

       }
        else{Button numberButton = (Button) actionEvent.getTarget();
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
    public void editPlay(Integer play_id){
        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditPlays.fxml"));

            loader.setController(new EditPlaysController());
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle("Edit Play");
            stage.show();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void search(ActionEvent actionEvent){
        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/searchPlays.fxml"));
            loader.setController(new SearchController());
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle("Search play");
            stage.show();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    /**
     * Event handler for creation of quote
     * @param event
     */
    public void addPlay(ActionEvent event){
        if(noviprozor2!=null && noviprozor2.getU().getManagement()!=1)
            new Alert(Alert.AlertType.NONE,"You are not authorised to add plays",ButtonType.OK).show();
        else if(noviprozor1!=null && noviprozor1.getU().getManagement()!=1)
            new Alert(Alert.AlertType.NONE,"You are not authorised to add plays",ButtonType.OK).show();
        else
            editPlay(null);
    }

}