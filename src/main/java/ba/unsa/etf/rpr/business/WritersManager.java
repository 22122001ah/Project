package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Writers;
import ba.unsa.etf.rpr.exceptions.PlaysException;

import java.util.List;

public class WritersManager {
    public List<Writers> searchByWriterName(String name) throws PlaysException{
        return DaoFactory.writersDao.searchByWriterName(name);
    }
    public Writers searchById(int Id) throws PlaysException{
        return DaoFactory.writersDao.getById(Id);
    }
}
