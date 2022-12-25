package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Writers;
import ba.unsa.etf.rpr.exceptions.PlaysException;

import java.util.List;

/**
 * Dao interface for Writers domain bean
 * @author Adna Herak
 */
public interface WritersDao extends Dao<Writers> {
    public List<Writers> searchByWriterName(String name) throws PlaysException;
    public Writers searchById(int Id) throws PlaysException;
}
