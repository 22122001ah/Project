package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.exceptions.PlaysException;

import java.util.List;

public class ArtistManager {
    public List<Artist> searchByArtistName(String name) throws PlaysException{
        return DaoFactory.artistDao.searchByArtistName(name);
    }
    public Artist searchById(int Id) throws PlaysException{
        return DaoFactory.artistDao.getById(Id);
    }
    public List<Artist> getAll() throws PlaysException{
        return DaoFactory.artistDao.getAll();
    }
    public Artist add(Artist item) throws PlaysException{
        return DaoFactory.artistDao.add(item);
    }
    public Artist getById(int id) throws PlaysException {
        return DaoFactory.artistDao.getById(id);
    }
}
