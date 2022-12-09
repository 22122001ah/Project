package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Writers;
import java.util.List;
import java.util.Date;

/**
 * Dao interface for Plays domain bean
 * @author Adna Herak
 */
public interface PlaysDao extends Dao<Plays> {

    List<Plays> searchByPrice(int prices);
    List<Plays> searchByWriter(Writers writer);
    List<Plays> searchByDirector(Directors director);
    List<Plays> searchByArtist(Artist artist);
    List<Plays> searchByDate(Date date);
    List<Plays> searchByPlayName(String play_name);

}
