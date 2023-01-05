package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.exceptions.PlaysException;

import java.util.List;

/**
 * Dao interface for Directors domain bean
 * @author Adna Herak
 */
public interface DirectorsDao extends Dao<Directors> {
    public Directors searchByDirectorName(String name) throws PlaysException;
    public Directors searchById(int Id) throws PlaysException;

}
