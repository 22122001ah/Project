package ba.unsa.etf.rpr.domain;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.PlaysDaoSQLimpl;
import ba.unsa.etf.rpr.exceptions.PlaysException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * Java bean class for table Plays
 * @author Adna Herak
 */
public class Plays extends PlaysDaoSQLimpl implements Idable {
    private int Id;
    private String play_name;
    private String genre;
    private Date date;
    private int price;
    private Directors director;
    private Writers writer;
    private Users management;

    public Users getManagement() {
        return management;
    }

    public void setManagement(Users user) {
        this.management = user;
    }

    public Plays() throws PlaysException {
    }

    /**
     * setters and getters for each private attribute
     */



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

    public void setDirector(Directors director) {
        this.director = director;
    }

    public void setWriter(Writers writer) {
        this.writer = writer;
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
    public Directors getDirector() {
        return director;
    }

    public Writers getWriter() {
        return writer;
    }

    @Override
    public String toString(){
        ArrayList<Artists> artists= null;
        try {
            artists = (ArrayList<Artists>) DaoFactory.playsin_Dao().searchByPlay(getById(Id));
        } catch (PlaysException e) {
            throw new RuntimeException(e);
        }
        String ret= play_name+"\nGenre: "+genre+"\nDate: "+date+"\nDirector: "+ director.getFirst_name()
                +"\nWriter: "+writer.getFirst_name()
                +"\nActors: ";
        for(int i=0;i<artists.size();i++)
        {    ret+=artists.get(i).getArtist_name();
        if(i!=artists.size()-1)
        ret+=",";
        }
        return ret;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, play_name, genre, date, price, director, writer,management);
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass()!=o.getClass()) return false;
        Plays p=(Plays) o;
        return Id==p.Id;
    }


    @Override
    public int getId() {
        return Id;
    }

    @Override
    public void setId(int anInt) {
this.Id=anInt;
    }
}
