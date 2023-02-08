package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Writers;
import ba.unsa.etf.rpr.exceptions.PlaysException;

/**
 * Dao interface for Writers domain bean
 * @author Adna Herak
 */
public interface WritersDao extends Dao<Writers> {
     Writers searchByWriterName(String name) throws PlaysException;
     Writers searchById(int Id) throws PlaysException;
}
