package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.exceptions.PlaysException;

public class UsersManager {
    public void validateUsername(String name) throws PlaysException {

    }
    public Users searchByUsername(String user) throws PlaysException{
        return DaoFactory.usersDao.searchByUsername(user);
    }

}
