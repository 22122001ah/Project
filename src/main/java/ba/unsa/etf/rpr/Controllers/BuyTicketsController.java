package ba.unsa.etf.rpr.Controllers;
import ba.unsa.etf.rpr.business.DirectorsManager;
import ba.unsa.etf.rpr.business.PlaysManager;
import ba.unsa.etf.rpr.business.WritersManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;


public class BuyTicketsController  {
    public Spinner<Integer> spinner=new Spinner<>();
    public Text price=new Text();

    public ChoiceBox<String> choiceBox=new ChoiceBox<>();
    public Label name=new Label();
    DirectorsManager directorsManager;
    WritersManager writersManager;
    PlaysManager playsManager=new PlaysManager();
    public Label Price1=new Label();
    public Label date=new Label();

    /**
     * Helper method to set the name of the play
     * @param ime
     */
    public void setName(String ime){
        name.setText(ime);
        try {
            date.setText(String.valueOf(DaoFactory.playsDao().searchByPlayName(ime).get(0).getDate()));
        } catch (PlaysException e) {
            throw new RuntimeException(e);
        }
    }
    public void setPrice(int text) {
        Price1.setText(String.valueOf(text));
        IntegerBinding Price= ReadOnlyIntegerProperty.readOnlyIntegerProperty(spinner.valueProperty()).multiply(text);
        price.textProperty().bind(Price.asString());
    }
    @FXML
    public void initialize(){
        String location[]={"Sarajevo","Banja Luka","Tuzla","Zenica","Mostar","Bihać","Trebinje","Livno"};
        choiceBox.setItems(FXCollections.observableArrayList(location));
        int initialValue = 1;
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, initialValue);
        spinner.setValueFactory(valueFactory);
    }

    /**
     * method that checks whether the play is sold out
     * if the play is not sold out, it changes the capacity in DB
     * @param actionEvent
     * @throws PlaysException
     */
    public void Buy(ActionEvent actionEvent) throws PlaysException {
        Plays P=new Plays();
        P=P.searchByPlayName(name.getText()).get(0);
        if(P.getMaxcap()==0)
            new Alert(Alert.AlertType.NONE,"SOLD OUT",ButtonType.OK).show();
      else {
          int p=P.getMaxcap()-(spinner.getValue());
            P.setMaxcap(p);
            playsManager.update(P);
            Button b=(Button) actionEvent.getTarget();
            Stage s=(Stage) b.getScene().getWindow();
            s.close();
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
        }

        public Plays toPlay() throws PlaysException {

            Plays q = new Plays();
            q.setPlay_name(this.play_name.getValue());
            q.setPrice(Integer.parseInt(this.price.getValue()));
            q.setDate(Date.valueOf(this.date.getValue()));
            q.setDirector(directorsManager.searchByDirectorName(this.director.getValue()));
            q.setWriter(writersManager.searchByWriterName(this.writer.getValue()));
            q.setGenre(this.genre.getValue());

            return q;
        }
    }
}
