package ba.unsa.etf.rpr.Controllers;


import ba.unsa.etf.rpr.business.PlaysManager;
import ba.unsa.etf.rpr.business.DirectorsManager;
import ba.unsa.etf.rpr.business.WritersManager;
import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.domain.Writers;
import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import ba.unsa.etf.rpr.domain.plays_in;
import ba.unsa.etf.rpr.business.plays_inManager;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * JavaFX controller for creation and alteration of Quote object
 *
 * @author Dino Keco
 */
public class ModelController {
    // helper components
    @FXML
    public GridPane aouQuotePane;

    // managers
    private final PlaysManager playsManager = new PlaysManager();
    private final DirectorsManager directorsManager = new DirectorsManager();
    private final WritersManager writersManager=new WritersManager();
    private final plays_inManager plays_inManager=new plays_inManager();
    private final ArtistManager artistManager=new ArtistManager();


    // model
    private PlayModel model = new PlayModel();

    // id of quote that is edited
    private Integer editPlayId;

    // form fields
    public TextArea Play_name;
    public DatePicker date;
    public ComboBox<Directors> directorId;
    public ComboBox<Writers> writer;

    public ModelController(Integer editQuoteId){
        this.editPlayId = editQuoteId;
    }

    public void initialize(){
        try{
            directorId.setItems(FXCollections.observableList((directorsManager.getAll())));
            Play_name.textProperty().bindBidirectional(model.play_name);
            date.valueProperty().bindBidirectional(model.date);
            directorId.valueProperty().bindBidirectional(model.director);
            writer.valueProperty().bindBidirectional(model.writer);

            if (editPlayId != null) {
                model.fromPlay(playsManager.getById(editPlayId));
            }
        }catch (PlaysException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * cancel button event handler
     * @param event
     */
    public void cancelAoUForm(ActionEvent event){
        aouQuotePane.getScene().getWindow().hide();
    }

    /**
     * save button event handler (update and add quote)
     * @param event
     */
    public void saveAoUForm(ActionEvent event){
        try{
            Plays q = model.toPlay();
            if (editPlayId != null){
                q.setId(editPlayId);
                playsManager.update(q);
            }else{
                playsManager.add(q);
            }
            aouQuotePane.getScene().getWindow().hide();
        }catch (PlaysException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }


    /**
     * Helper Model class that supports 2 way data binding with form for Quote management
     * @author Dino Keco
     *
     */
    public class PlayModel{
        public SimpleStringProperty play_name = new SimpleStringProperty("");
        public SimpleObjectProperty<Integer> price = new SimpleObjectProperty<Integer>();
        public SimpleObjectProperty<LocalDate> date = new SimpleObjectProperty<LocalDate>();
        public SimpleStringProperty genre = new SimpleStringProperty("");
        public SimpleStringProperty description = new SimpleStringProperty("");
        public SimpleObjectProperty<Directors> director = new SimpleObjectProperty<Directors>();
        public SimpleObjectProperty<Writers> writer = new SimpleObjectProperty<Writers>();
        public ArrayList<SimpleObjectProperty<Artists>> artist= new ArrayList<>();
        public SimpleObjectProperty<plays_in> plays_in=new SimpleObjectProperty<plays_in>();

        public void fromPlay(Plays q) throws PlaysException {
            this.play_name.set(q.getPlay_name());
            this.date.set(((Date)q.getDate()).toLocalDate());
            this.price.set(q.getPrice());
            this.genre.set(q.getGenre());
            this.description.set(q.getDescription());
            this.director.set(q.getDirector());
            this.writer.set(q.getWriter());
            this.director.set(directorsManager.searchById(q.getDirector().getId()));
            ArrayList<Artists>a=(ArrayList<Artists>) plays_inManager.searchByPlay(q);
            for(int i=0;i<a.size();i++){
                SimpleObjectProperty<Artists> ar=new SimpleObjectProperty<>();
                ar.set(a.get(i));
                artist.set(i,ar);
            }
        }

        public Plays toPlay() throws PlaysException {
            Plays q = new Plays();
            q.setPlay_name(this.play_name.getValue());
            q.setPrice(this.price.getValue());
            q.setDate(Date.valueOf(this.date.getValue()));
            q.setDirector(this.director.getValue());
            q.setWriter(this.writer.getValue());
            q.setGenre(this.genre.getName());
            q.setDescription(this.description.getValue());
            return q;
        }
    }
}