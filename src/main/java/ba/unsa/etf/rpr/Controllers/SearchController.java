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

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

/**
 * Controller for managing Quotes Entity
 * @author Dino Keco
 */
public class SearchController{
    // managers
    private final PlaysManager playsManager= new PlaysManager();

    // helper components
    @FXML
    public BorderPane playScreen;
    private final PlaysManager PlaysManager=new PlaysManager();
    // components
    public TableView playsTable;
    public TextField search;

    public TableColumn<Plays, String> idColumn;
    public TableColumn<Plays, String> playsColumn;
    public TableColumn<Plays, Date> createdColumn;
    Button edit=new Button("Edit");

    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<Plays, String>("Id"));
        playsColumn.setCellValueFactory(new PropertyValueFactory<Plays, String>("play_name"));
        createdColumn.setCellValueFactory(new PropertyValueFactory<Plays, Date>("date"));
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
     * @param quoteId
     */
    public void deleteQuote(Integer quoteId){
        try {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete");
            Optional<ButtonType> result = confirmation.showAndWait();
            if (!result.get().getButtonData().isCancelButton()){
                PlaysManager.delete(quoteId);
                refreshPlays();
            }
        } catch (PlaysException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Open form for editing or creating quote
     *
     * @param quoteId - only for edit if we know which quote is being edited.
     */
    public void editQuoteScene(Integer quoteId){
        try{
            ((Stage)playScreen.getScene().getWindow()).hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddPlays.fxml"));
            loader.setController(new ModelController(quoteId));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Edit Quote");
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
        editQuoteScene(null);
    }

    /**
     * fetch quotes from DB
     */
    private void refreshPlays(){
        try {
            ArrayList<Plays> p=new ArrayList<>();
            for(int i=0;i<5;i++)
                p.add(playsManager.randomPlay());
            playsTable.setItems(FXCollections.observableList(p));
            playsTable.refresh();
        } catch (PlaysException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}