package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.exceptions.PlaysException;

import java.util.List;

public class plays_inManager {
    public List<Artist> searchByPlay(Plays play) throws PlaysException{
        return DaoFactory.playsin_Dao.searchByPlay(play);
    }
    public List<Plays> searchByArtist(Artist artist) throws PlaysException{
        return DaoFactory.playsin_Dao.searchByArtist(artist);
    }
}
