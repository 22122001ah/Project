package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.domain.Writers;
import ba.unsa.etf.rpr.exceptions.PlaysException;

import java.util.Date;
import java.util.List;

public class PlaysManager {
    public List<Plays> getAll() throws PlaysException {
    return DaoFactory.playsDao.getAll();
}

    public List<Plays> searchByPrice(int price) throws PlaysException {
        return DaoFactory.playsDao.searchByPrice(price);
    }
    public Plays getById(int Id) throws PlaysException{
        return DaoFactory.playsDao.getById(Id);
    }
    public List<Plays> searchByPrices(int price1,int price2) throws PlaysException{
        return DaoFactory.playsDao.searchByPrices(price1,price2);
    }
    public List<Plays> searchByWriter(Writers writer) throws PlaysException{
        return DaoFactory.playsDao.searchByWriter(writer);
    }
    public List<Plays> searchByDirector(Directors director)throws PlaysException {
    return DaoFactory.playsDao.searchByDirector(director);
    }
    public List<Plays> searchByDate(Date date) throws PlaysException {
    return DaoFactory.playsDao.searchByDate(date);
    }
    public Plays searchByPlayName(String play_name)throws PlaysException {
        return DaoFactory.playsDao.searchByPlayName(play_name);
    }
    public List<String>getAllGenres() throws PlaysException {
    return DaoFactory.playsDao.getAllGenres();
    }
    public List<Plays> searchByGenre(String genre) throws PlaysException {
        return DaoFactory.playsDao.searchByGenre(genre);
    }
}
