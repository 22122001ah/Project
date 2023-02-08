package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.exceptions.PlaysException;

/**
 * Dao interface for Directors domain bean
 * @author Adna Herak
 */
public interface DirectorsDao extends Dao<Directors> {
     Directors searchByDirectorName(String name) throws PlaysException;
     Directors searchById(int Id) throws PlaysException;

}
