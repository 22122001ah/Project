package ba.unsa.etf.rpr.Controllers;


import ba.unsa.etf.rpr.business.PlaysManager;
import ba.unsa.etf.rpr.business.DirectorsManager;
import ba.unsa.etf.rpr.business.WritersManager;
import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import ba.unsa.etf.rpr.domain.plays_ins;
import ba.unsa.etf.rpr.business.plays_inManager;
import javafx.util.Pair;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * JavaFX controller for creation and alteration of plays and plays_in(connects plays and actors) object
 *
 * @author Adna Herak
 */
public class ModelController {
    @FXML
    public GridPane addPlays;

    private final PlaysManager playsManager = new PlaysManager();
    private final DirectorsManager directorsManager = new DirectorsManager();
    private final WritersManager writersManager=new WritersManager();
    private final plays_inManager plays_inManager=new plays_inManager();
    private final ArtistManager artistManager=new ArtistManager();


    private PlayModel model = new PlayModel();

    private Integer editPlayId;

    public TextArea Play_name;
    public DatePicker date;
    public TextField directorId;
    public TextField writer;
    public TextField artists;
    public TextField price;
    public TextField genre;

    public ModelController(Integer editPlayId){
        this.editPlayId = editPlayId;
    }

    public void initialize() {
        try{

            Play_name.textProperty().bindBidirectional(model.play_name);

            date.valueProperty().bindBidirectional(model.date);
            writer.textProperty().bindBidirectional(model.writer);
            artists.textProperty().bindBidirectional(model.artist);
            directorId.textProperty().bindBidirectional(model.director);
            price.textProperty().bindBidirectional(model.price);
            genre.textProperty().bindBidirectional(model.genre);
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
     * save button event handler (update and add plays)
     * @param event
     */
    public void saveAoUForm(ActionEvent event){
        try{
            Plays q = model.toPlay().getKey();
            ArrayList<plays_ins> p=model.toPlay().getValue();
            if (editPlayId != null){
                q.setId(editPlayId);
                playsManager.update(q);
                for(int i=0;i<p.size();i++) {
                    if(p.get(i).getId()==0)
                        plays_inManager.add(p.get(i));
                    else
                    plays_inManager.update(p.get(i));
                }
            }else{
                playsManager.add(q);
                for(int i=0;i<p.size();i++)
                    plays_inManager.add(p.get(i));
            }
            addPlays.getScene().getWindow().hide();
        }catch (PlaysException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }


    /**
     * Helper Model class that supports 2 way data binding with form for play management
     *
     */
    public class PlayModel{
        public SimpleStringProperty play_name = new SimpleStringProperty("");
        public SimpleObjectProperty<String> price = new SimpleObjectProperty<String>();
        public SimpleObjectProperty<LocalDate> date = new SimpleObjectProperty<LocalDate>();
        public SimpleStringProperty genre = new SimpleStringProperty("");
        public SimpleObjectProperty<String> director = new SimpleObjectProperty<String>();
        public SimpleObjectProperty<String> writer = new SimpleObjectProperty<String>();
        public SimpleObjectProperty<String> artist= new SimpleObjectProperty<>();

        public void fromPlay(Plays q) throws PlaysException {
            this.play_name.set(q.getPlay_name());
            this.date.set(((Date)q.getDate()).toLocalDate());
            this.price.set(String.valueOf(q.getPrice()));
            this.genre.set(q.getGenre());
            this.director.set(q.getDirector().getFirst_name());
            this.writer.set(q.getWriter().getFirst_name());
            ArrayList<Artists>a=(ArrayList<Artists>) plays_inManager.searchByPlay(q);
            String k=new String();
            for(int i=0;i<a.size();i++){
              k+=a.get(i).getArtist_name()+",";
            }
            this.artist.set(k);
        }

        public Pair<Plays,ArrayList<plays_ins>> toPlay() throws PlaysException {

            Plays q = new Plays();
            ArrayList<plays_ins>p=new ArrayList<>();
            q.setPlay_name(this.play_name.getValue());
            q.setPrice(Integer.parseInt(this.price.getValue()));
            q.setDate(Date.valueOf(this.date.getValue()));
            q.setDirector(directorsManager.searchByDirectorName(this.director.getValue()));
            q.setWriter(writersManager.searchByWriterName(this.writer.getValue()));
            q.setGenre(this.genre.getValue());
            q.setId(q.searchByPlayName(this.play_name.getValue()).get(0).getId());
            String[] a;
            a=this.artist.getValue().split(",");
            for (int i=0;i<a.length;i++)
            {  plays_ins plays_in1=new plays_ins();
                if(q.getId()!=0)
                plays_in1.setPlays_id(q.getId());
                else plays_in1.setPlays_id(q.getAll().size());
                plays_in1.setArtist_id(artistManager.searchByArtistName(a[i]).getId());
                p.add(plays_in1);}
            Pair<Plays,ArrayList<plays_ins>> pl=new Pair<>(q,p);
            return pl;
        }
    }
}
