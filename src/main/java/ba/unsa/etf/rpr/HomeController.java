package ba.unsa.etf.rpr;
import ba.unsa.etf.rpr.dao.PlaysDaoSQLimpl;
import ba.unsa.etf.rpr.domain.Plays;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {
    public ListView<Plays> playsListView;

    public Button showPlaysBtn;
    private PlaysDaoSQLimpl playsDaoSQL;
    private ObservableList<Plays> plays;
    public TableView<Plays> playsTableView;
    public TableColumn<Object, Object> colPlayGenre;
    public TableColumn<Object, Object> colPlayName;
    public TableColumn<Object, Object> colPlayDate;
    public TableColumn<Object, Object> colPlayPrice;


    /**
     *
     */
    public HomeController() {
        try {
            playsDaoSQL=new PlaysDaoSQLimpl();
            plays = FXCollections.observableArrayList(playsDaoSQL.searchByPrices(10,20));
        } catch (Exception e) {
            System.out.println("Something is not right with table of plays");
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize() {
        playsListView.setItems(plays);
        colPlayName.setCellValueFactory(new PropertyValueFactory<>("play_name"));
        colPlayPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPlayDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        playsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldCat, newCat) -> {
            try {
                playsTableView.getItems().clear();
                plays.addAll(playsDaoSQL.searchByPrice(newCat.getPrice()));
                playsTableView.refresh();
                playsTableView.setItems(plays);
            } catch (Exception e) {
                System.out.println("Something went wrong with searchByPricemethod from ŠlaysDaoSQlImpl");
                throw new RuntimeException(e);
            }
        });
    }

    public void onActionShowPlays(ActionEvent actionEvent) {
        Plays plays = playsTableView.getSelectionModel().getSelectedItem();
        if (plays == null)
            return;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Register.fxml"));
            ShowPlaysController showPlaysController = new ShowPlaysController(plays);
            loader.setController(showPlaysController);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle("Show qoute page");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void openAbout(ActionEvent actionEvent){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene((Parent) loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle("Add category");
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }
    public void ButtonClick(ActionEvent actionEvent) throws IOException {
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
    public void ButtonClick1(ActionEvent actionEvent) throws IOException {
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

}
