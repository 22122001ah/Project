package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.PlaysException;

import java.util.List;

/**
 * Dao interface for Artist domain bean
 * @author Adna Herak
 */
public interface ArtistDao extends Dao<Artist> {
    public List<Artist> searchByArtistName(String name) throws PlaysException;
    public Artist searchById(int Id) throws PlaysException;
}
