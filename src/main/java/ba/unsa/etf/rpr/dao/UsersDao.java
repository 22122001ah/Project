package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.exceptions.PlaysException;

public interface UsersDao extends Dao<Users>{
     Users searchByUsername(String user) throws PlaysException;

}
