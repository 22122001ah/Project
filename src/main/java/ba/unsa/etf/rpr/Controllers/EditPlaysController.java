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

import java.util.Date;
import java.util.Optional;

/**
 * Controller for managing Quotes Entity
 * @author Dino Keco
 */
public class EditPlaysController extends TableCell<Plays, Button>{
    // managers
    private final PlaysManager quoteManager = new PlaysManager();

    // helper components
    @FXML
    public BorderPane quoteScreen;
private final PlaysManager PlaysManager=new PlaysManager();
    // components
    public TableView quotesTable;
    public TextField search;

    public TableColumn<Plays, String> idColumn;
    public TableColumn<Plays, String> quoteColumn;
    public TableColumn<Plays, Date> createdColumn;
    public TableColumn<Plays, Integer> actionColumn;
    Button edit=new Button("Edit");

    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<Plays, String>("Id"));
        quoteColumn.setCellValueFactory(new PropertyValueFactory<Plays, String>("play_name"));
        createdColumn.setCellValueFactory(new PropertyValueFactory<Plays, Date>("date"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<Plays, Integer>("Id"));

        refreshQuotes();
    }


    /**
     * search quote event handler
     * @param event
     */
    public void searchQuotes(ActionEvent event){
        try {
            quotesTable.setItems(FXCollections.observableList(PlaysManager.searchByPlayName(search.getText())));
            quotesTable.refresh();
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
                refreshQuotes();
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
            ((Stage)quoteScreen.getScene().getWindow()).hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddPlays.fxml"));
            loader.setController(new ModelController(quoteId));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Edit Quote");
            stage.show();
            stage.setOnHiding(event -> {
                ((Stage)quoteScreen.getScene().getWindow()).show();
                refreshQuotes();
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
    private void refreshQuotes(){
        try {
            quotesTable.setItems(FXCollections.observableList(quoteManager.getAll()));
            quotesTable.refresh();
        } catch (PlaysException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}