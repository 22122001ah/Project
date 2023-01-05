package ba.unsa.etf.rpr.Controllers;


import ba.unsa.etf.rpr.business.PlaysManager;
import ba.unsa.etf.rpr.business.DirectorsManager;
import ba.unsa.etf.rpr.business.WritersManager;
import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
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
import java.time.ZoneId;
import java.util.ArrayList;

/**
 * JavaFX controller for creation and alteration of Quote object
 *
 * @author Adna Herak
 */
public class ModelController {
    // helper components
    @FXML
    public GridPane addPlays;

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
    public TextField directorId;
    public TextField writer;
    public TextField artists;

    public ModelController(Integer editQuoteId){
        this.editPlayId = editQuoteId;
    }

    public void initialize() {
        try{

            Play_name.textProperty().bindBidirectional(model.play_name);

            date.valueProperty().bindBidirectional(model.date);
            writer.textProperty().bindBidirectional(model.writer);
            artists.textProperty().bindBidirectional(model.artist);
            directorId.textProperty().bindBidirectional(model.director);
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
        addPlays.getScene().getWindow().hide();
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
            addPlays.getScene().getWindow().hide();
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
        public SimpleObjectProperty<String> director = new SimpleObjectProperty<String>();
        public SimpleObjectProperty<String> writer = new SimpleObjectProperty<String>();
       public SimpleObjectProperty<String> artist= new SimpleObjectProperty<>();
        public SimpleObjectProperty<plays_in> plays_in=new SimpleObjectProperty<plays_in>();

        public void fromPlay(Plays q) throws PlaysException {
            this.play_name.set(q.getPlay_name());
            this.date.set(((Date)q.getDate()).toLocalDate());
           // this.price.set(q.getPrice());
          //  this.genre.set(q.getGenre());
            this.director.set(q.getDirector().getFirst_name());
            this.writer.set(q.getWriter().getFirst_name());
            ArrayList<Artists>a=(ArrayList<Artists>) plays_inManager.searchByPlay(q);
            for(int i=0;i<a.size();i++){
                artist.set(a.get(i).getArtist_name());

            }
        }

        public Plays toPlay() throws PlaysException {
            Plays q = new Plays();
            ArrayList<plays_in >p=new ArrayList<>();
            q.setPlay_name(this.play_name.getValue());
            //  q.setPrice(this.price.getValue());
            q.setDate(Date.valueOf(this.date.getValue()));
            q.setDirector(directorsManager.searchByDirectorName(this.director.getValue()));
            q.setWriter(writersManager.searchByWriterName(this.writer.getValue()));
            //  q.setGenre(this.genre.getName());

            String[] a;
            a=this.artist.getValue().split(",");
            for (int i=0;i<a.length;i++)
            {  plays_in plays_in1=new plays_in();
                plays_in1.setPlays_id(q.getId());
                plays_in1.setArtist_id(artistManager.searchByArtistName(a[i]).getId());
                p.add(plays_in1);}
            return q;

    }
    }
}
