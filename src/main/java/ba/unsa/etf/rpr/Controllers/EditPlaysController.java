package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.PlaysManager;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import java.util.Date;
import java.util.Optional;

/**
 * Controller for managing Plays
 */
public class EditPlaysController{

    private final PlaysManager playsManager= new PlaysManager();
    @FXML
    public BorderPane playScreen;
    private final PlaysManager PlaysManager=new PlaysManager();
    public TableView playsTable;
    public TextField search;

    public TableColumn<Plays, String> idColumn;
    public TableColumn<Plays, String> playsColumn;
    public TableColumn<Plays, Date> createdColumn;
    public TableColumn<Plays, Integer> actionColumn;
    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<Plays, String>("Id"));
        playsColumn.setCellValueFactory(new PropertyValueFactory<Plays, String>("play_name"));
        createdColumn.setCellValueFactory(new PropertyValueFactory<Plays, Date>("date"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<Plays, Integer>("Id"));

        refreshPlays();
    }


    /**
     * search plays event handler
     * @param event
     */
    public void searchPlays(ActionEvent event){
        try {
            playsTable.setItems(FXCollections.observableList(PlaysManager.searchByPlayName(search.getText())));
            playsTable.refresh();
        } catch (PlaysException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Event handler for deletion of quote. It has confirm box before deletion
     * @param playId
     */
    public void deletePlays(Integer playId){
        try {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete");
            Optional<ButtonType> result = confirmation.showAndWait();
            if (!result.get().getButtonData().isCancelButton()){
                PlaysManager.delete(playId);
                refreshPlays();
            }
        } catch (PlaysException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Open form for editing or creating plays
     *
     * @param playId - only for edit if we know which quote is being edited.
     */
    public void editPlayScene(Integer playId){
        try{
            ((Stage)playScreen.getScene().getWindow()).hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddPlays.fxml"));
            loader.setController(new ModelController(playId));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle("Edit Plays");
            stage.show();
            stage.setOnHiding(event -> {
                ((Stage)playScreen.getScene().getWindow()).show();
                refreshPlays();
            });
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }


    /**
     * Event handler for creation of quote
     * @param event
     */
    public void addPlay(ActionEvent event){
        editPlayScene(null);
    }

    /**
     * fetch plays from DB
     */
    private void refreshPlays(){
        try {
            playsTable.setItems(FXCollections.observableList(playsManager.getAll()));
            playsTable.refresh();
        } catch (PlaysException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}