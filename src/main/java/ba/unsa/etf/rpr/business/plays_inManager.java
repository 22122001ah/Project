package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.plays_in;
import ba.unsa.etf.rpr.exceptions.PlaysException;

import java.util.List;

public class plays_inManager {
    public List<Artists> searchByPlay(Plays play) throws PlaysException{
        return DaoFactory.playsin_Dao().searchByPlay(play);
    }
    public List<Plays> searchByArtist(Artists artists) throws PlaysException{
        return DaoFactory.playsin_Dao().searchByArtist(artists);
    }
    public List<plays_in> getAll() throws PlaysException{
        return DaoFactory.playsin_Dao().getAll();
    }
    public plays_in add(plays_in item) throws PlaysException{
        return DaoFactory.playsin_Dao().add(item);
    }
    public plays_in getById(int id) throws PlaysException {
        return DaoFactory.playsin_Dao().getById(id);
    } public plays_in update(plays_in item) throws PlaysException{
     return DaoFactory.playsin_Dao().update(item);
    }

}
