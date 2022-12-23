package ba.unsa.etf.rpr.domain;
import ba.unsa.etf.rpr.dao.PlaysDaoSQLimpl;

import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

/**
 * Java bean class for table Plays
 * @author Adna Herak
 */
public class Plays extends PlaysDaoSQLimpl implements Idable {
    private int play_id;
    private String play_name;
    private String genre;
    private Date date;
    private int price;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String about) {
        Description = about;
    }

    private String Description;
    private Directors director;
    private Writers writer;



    public Plays() throws SQLException, IOException {
    /*    FileReader reader = new FileReader("src/main/resources/database.properties");
        Properties p = new Properties();
        p.load(reader);
        String url = p.getProperty("url");
        String user = p.getProperty("username");
        String password = p.getProperty("password");
        this.connection = DriverManager.getConnection(url,user,password);*/
    }

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



    public Directors getDirector() {
        return director;
    }

    public Writers getWriter() {
        return writer;
    }

    @Override
    public String toString() {
        return "Plays{" +
                "play_id=" + play_id +
                ", play_name='" + play_name + '\'' +
                ", genre='" + genre + '\'' +
                ", date=" + date +
                ", price=" + price +
                ", About='" + Description + '\'' +
                ", director=" + director +
                ", writer=" + writer +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(play_id, play_name, genre, date, price, Description, director, writer);
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass()!=o.getClass()) return false;
        Plays p=(Plays) o;
        return play_id==p.play_id;
    }


    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int anInt) {

    }
}
