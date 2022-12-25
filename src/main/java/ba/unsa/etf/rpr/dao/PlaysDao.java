package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Writers;
import ba.unsa.etf.rpr.exceptions.PlaysException;

import java.util.List;
import java.util.Date;

/**
 * Dao interface for Plays domain bean
 * @author Adna Herak
 */
public interface PlaysDao extends Dao<Plays> {
    /**
     * searches Plays based on prices
     * @param prices
     * @return List of Plays with wanted price
     */
    List<Plays> searchByPrice(int prices) throws PlaysException;

    /**
     * searches for Plays in given price range
     * @param price1,price2
     * @return List of Plays in given price range
     */
    List<Plays> searchByPrices(int price1,int price2) throws PlaysException;

    /**
     * searches for Plays written by wanted writer
     * @param writer
     * @return List of Plays written by wanted writer
     */
    List<Plays> searchByWriter(Writers writer) throws PlaysException;

    /**
     * Searches for Plays directed by wanted director
     * @param director
     * @return List of Plays directed by wanted director
     */
    List<Plays> searchByDirector(Directors director) throws PlaysException;


    /**
     * searches for Plays that are playing on a certain date
     * @param date
     * @return List of Plays playing on a certain date
     */
    List<Plays> searchByDate(Date date) throws PlaysException;

    /**
     * searches Plays by name
     * @param play_name
     * @return Play with that name
     */
    Plays searchByPlayName(String play_name) throws PlaysException;
    List<String>getAllGenres() throws PlaysException;
    List<Plays>searchByGenre(String genre) throws PlaysException;

}
