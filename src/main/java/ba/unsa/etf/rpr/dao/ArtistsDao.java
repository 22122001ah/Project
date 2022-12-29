package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.exceptions.PlaysException;

import java.util.List;

/**
 * Dao interface for Artist domain bean
 * @author Adna Herak
 */
public interface ArtistsDao extends Dao<Artists> {
    public List<Artists> searchByArtistName(String name) throws PlaysException;
    public Artists searchById(int Id) throws PlaysException;
}
