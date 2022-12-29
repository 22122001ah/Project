package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.plays_in;
import java.util.List;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.exceptions.PlaysException;

/**
 * Dao interface for plays_in domain bean
 * @author Adna Herak
 */
public interface plays_inDao extends Dao<plays_in> {
    /**
     * searches for all Plays in which given actor plays
     * @param artists
     * @return List of plays in which actor plays
     */
    List<Plays> searchByArtist(Artists artists) throws PlaysException;
    /**
     * searches for all actors that play in given Play
     * @param play
     * @return List of actors that play in given Play
     */
    List<Artists> searchByPlay(Plays play) throws PlaysException;
}

