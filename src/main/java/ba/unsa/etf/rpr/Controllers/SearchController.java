package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.PlaysManager;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Controller for managing Plays
 */
public class SearchController{
    private final PlaysManager playsManager= new PlaysManager();
    private final PlaysManager PlaysManager=new PlaysManager();
    public TableView playsTable;
    public TextField search;
    public Slider slider;
    public Label maxprice;
    public ChoiceBox<String> choice=new ChoiceBox<>();

    public TableColumn<Plays, String> playsColumn;
    public TableColumn<Plays, Date> createdColumn;
    public TableColumn<Plays,Integer> price;
    public TableColumn<Plays,String> genre;
    public TableColumn<Plays, Integer> actionColumn;
public LoginController noviprozor1;
public RegisterController noviprozor2;

    public SearchController(LoginController noviprozor1, RegisterController noviprozor2) {
        this.noviprozor1 = noviprozor1;
        this.noviprozor2 = noviprozor2;
    }

    public void initialize()throws PlaysException {
        playsColumn.setCellValueFactory(new PropertyValueFactory<Plays, String>("play_name"));
        createdColumn.setCellValueFactory(new PropertyValueFactory<Plays, Date>("date"));
        price.setCellValueFactory(new PropertyValueFactory<Plays,Integer>("price"));
        genre.setCellValueFactory(new PropertyValueFactory<Plays,String>("genre"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<Plays, Integer>("Id"));
        actionColumn.setCellFactory(new ButtonFactory2Actions(buyEvent -> {
            int playId = Integer.parseInt(((Button)buyEvent.getSource()).getUserData().toString());
            try {
                buy(playId);
            } catch (PlaysException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, (viewEvent -> {
            int playId = Integer.parseInt(((Button)viewEvent.getSource()).getUserData().toString());
            try {
                PlayDesription(playId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        })));
        choice.setItems(FXCollections.observableList(playsManager.getAllGenres()));
        Platform.runLater(() -> {
            SkinBase<ChoiceBox<String>> skin = (SkinBase<ChoiceBox<String>>) choice.getSkin();
            for (Node child : skin.getChildren()) {
                if (child instanceof Label) {
                    Label label = (Label) child;
                    if (label.getText().isEmpty()) {
                        label.setText("Genre");
                        Font f=new Font("Bodoni MT Bold Italic",12);
                        label.setFont(f);
                    }
                    return;
                }
            }
        });
        maxprice.setText(Integer.toString((int)slider.getValue()));
        slider.valueProperty().addListener(
                (observableValue, number, t1) -> maxprice.setText(Integer.toString((int) slider.getValue()))
        );
        refreshPlays();
    }
    public void buy(int Id) throws PlaysException, IOException {
        if(noviprozor2==null && noviprozor1==null)
        {
            new Alert(Alert.AlertType.NONE,"You need to have an account and be logged in in order to buy tickets.",ButtonType.OK).show();

        }
        else if(noviprozor1.getU()==null && noviprozor2.getU()==null)
        {
            new Alert(Alert.AlertType.NONE,"You need to have an account and be logged in in order to buy tickets.",ButtonType.OK).show();
        }

        else{

            Stage secondstage=new Stage();
            FXMLLoader fl=new FXMLLoader(getClass().getResource("/fxml/BuyTickets.fxml"));
            Parent root=fl.load();
            BuyTicketsController buyTicketsController=fl.getController();
            buyTicketsController.setPrice(playsManager.getById(Id).getPrice());
            buyTicketsController.setName(playsManager.getById(Id).getPlay_name());
            secondstage.setTitle("Buy tickets");
            secondstage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
            secondstage.setResizable(false);
            secondstage.show();}
    }

    public void PlayDesription(int id) throws IOException {
        try{

            Stage Secondstage=new Stage();
            FXMLLoader fl=new FXMLLoader(getClass().getResource("/fxml/Info.fxml"));
            Parent root =fl.load();
            InfoController noviprozor=fl.getController();
            noviprozor.setText(playsManager.getById(id).toString());
            Secondstage.setTitle("Play description");
            Secondstage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
            Secondstage.setResizable(false);
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
      search();
    }
    public void search(){
        try {
            if(Integer.parseInt(maxprice.getText())==0 && choice.getValue()==null)
                playsTable.setItems(FXCollections.observableList(playsManager.searchByPlayName(search.getText())));
           else if(Integer.parseInt(maxprice.getText())==0 && choice.getValue()!=null){
playsTable.setItems(FXCollections.observableList(playsManager.searchByPlaynameandGenre(search.getText(),choice.getValue())));
            }
           else if(Integer.parseInt(maxprice.getText())!=0 && choice.getValue()==null){
                playsTable.setItems(FXCollections.observableList(playsManager.searchByPlaynameandPrice(search.getText(),Integer.parseInt(maxprice.getText()))));
            }
            else {
                playsTable.setItems(FXCollections.observableList(PlaysManager.searchByPlaynameandPriceandGenre(search.getText(),Integer.parseInt(maxprice.getText()),choice.getValue())));
            }
            playsTable.refresh();
        } catch (PlaysException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    public void ENTER(KeyEvent keyEvent){
        if(keyEvent.getCode()== KeyCode.ENTER) {
            search();
        }
    }

    /**
     * fetch plays from DB
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