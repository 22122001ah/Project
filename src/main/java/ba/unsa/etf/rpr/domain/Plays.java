package ba.unsa.etf.rpr.domain;
import java.util.Date;
import java.util.Objects;

/**
 * Java bean class for table Plays
 * @author Adna Herak
 */
public class Plays {
    private int play_id;
    private String play_name;
    private String genre;
    private Date date;
    private int price;
    private String pick_up_location;
    private Directors director;
    private Writers writer;
    /**
     * setters and getters for each private attribute
     */
    public void setPlay_id(int play_id) {
        this.play_id = play_id;
    }

    public void setPlay_name(String play_name) {
        this.play_name = play_name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPick_up_location(String pick_up_location) {
        this.pick_up_location = pick_up_location;
    }

    public void setDirector(Directors director) {
        this.director = director;
    }

    public void setWriter(Writers writer) {
        this.writer = writer;
    }

    public int getPlay_id() {
        return play_id;
    }

    public String getPlay_name() {
        return play_name;
    }

    public String getGenre() {
        return genre;
    }

    public Date getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }

    public String getPick_up_location() {
        return pick_up_location;
    }

    public Directors getDirector() {
        return director;
    }

    public Writers getWriter() {
        return writer;
    }
    @Override
    public String toString(){
        return "Play{ id="+play_id+",name="+play_name+
                ",genre="+genre+
                ",date="+date+
                ",price="+price+
                ",pick up location="+pick_up_location+
                ",director="+director+
                ",writer="+writer;
    }
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass()!=o.getClass()) return false;
        Plays p=(Plays) o;
        return play_id==p.play_id;
    }
    @Override
    public int hashCode(){
        return Objects.hash(play_id,play_name,genre,date,price,pick_up_location,director,writer);
    }
}
