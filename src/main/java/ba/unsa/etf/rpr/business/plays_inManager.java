package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.plays_ins;
import ba.unsa.etf.rpr.exceptions.PlaysException;

import java.util.List;

public class plays_inManager {
    public List<Artists> searchByPlay(Plays play) throws PlaysException{
        return DaoFactory.playsin_Dao().searchByPlay(play);
    }
    public List<Plays> searchByArtist(Artists artists) throws PlaysException{
        return DaoFactory.playsin_Dao().searchByArtist(artists);
    }
    public List<plays_ins> getAll() throws PlaysException{
        return DaoFactory.playsin_Dao().getAll();
    }
    public plays_ins add(plays_ins item) throws PlaysException{
        return DaoFactory.playsin_Dao().add(item);
    }
    public plays_ins getById(int id) throws PlaysException {
        return DaoFactory.playsin_Dao().getById(id);
    } public plays_ins update(plays_ins item) throws PlaysException{
        return DaoFactory.playsin_Dao().update(item);
    }
public void delete(int item) throws PlaysException {
    DaoFactory.playsin_Dao().delete(item);
}
    public List<plays_ins> searchPlays(Plays play) throws PlaysException{
        return DaoFactory.playsin_Dao().searchPlays(play);
    }
    public List<plays_ins> searchArtists(Artists artists) throws PlaysException{
        return DaoFactory.playsin_Dao().searchArtists(artists);
    }
}
