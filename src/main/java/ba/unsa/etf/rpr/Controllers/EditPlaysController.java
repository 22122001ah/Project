package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.PlaysManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Controller for managing Plays
 */
public class EditPlaysController{
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
    public TableColumn<Plays, Integer> actionColumn;
    Button edit=new Button("Edit");

    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<Plays, String>("Id"));
        playsColumn.setCellValueFactory(new PropertyValueFactory<Plays, String>("play_name"));
        createdColumn.setCellValueFactory(new PropertyValueFactory<Plays, Date>("date"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<Plays, Integer>("Id"));
        actionColumn.setCellFactory(new ButtonFactory(editEvent -> {
            int quoteId = Integer.parseInt(((Button)editEvent.getSource()).getUserData().toString());
            editPlayScene(quoteId);
        }, (deleteEvent -> {
            int quoteId = Integer.parseInt(((Button)deleteEvent.getSource()).getUserData().toString());
            deletePlays(quoteId);
        }),(ViewEvent -> {
            int qouteId=Integer.parseInt(((Button) ViewEvent.getSource()).getUserData().toString());
            try {
                PlayDesription(qouteId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        })));
        refreshPlays();
    }
    /**
     * opening new window with the description of the selected play
     * @param id
     * @throws IOException
     */
    public void PlayDesription(int id) throws IOException {
        try{

            Stage Secondstage=new Stage();
            FXMLLoader fl=new FXMLLoader(getClass().getResource("/fxml/Info.fxml"));
            Parent root =fl.load();
            InfoController noviprozor=fl.getController();
            noviprozor.setText(DaoFactory.playsDao().getById(id).toString());
            Secondstage.setTitle("Play description");
            Secondstage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
            Secondstage.show();

        }
        catch(Exception e){
            System.out.println(e);
        }
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
     * Event handler for deletion of plays. It has a confirm box before deletion
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
     * @param playId - only for edit if we know which play is being edited.
     */
    public void editPlayScene(Integer playId){
        try{
            ((Stage)playScreen.getScene().getWindow()).hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddPlays.fxml"));
            loader.setController(new ModelController(playId));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Edit");
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
     * Event handler for creation of plays
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