package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.exceptions.PlaysException;

import java.util.List;

public class DirectorsManager {
    public List<Directors> searchByDirectorName(String name) throws PlaysException{
        return DaoFactory.directorsDao.searchByDirectorName(name);
    }
    public Directors searchById(int Id) throws PlaysException{
        return DaoFactory.directorsDao.getById(Id);
    }
}
