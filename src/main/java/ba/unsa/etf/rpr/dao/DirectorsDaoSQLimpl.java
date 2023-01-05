package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.exceptions.PlaysException;

import java.sql.*;
import java.util.*;

public class DirectorsDaoSQLimpl extends AbstractDao1<Directors> implements DirectorsDao {

    public DirectorsDaoSQLimpl(){
        super("Directors");
    }

    @Override
    public Directors row2object(ResultSet rs) throws PlaysException {
        try{

            Directors d=new Directors();
            d.setId(rs.getInt("director_id"));
            d.setFirst_name(rs.getString("first_name"));
            return d;}
        catch (Exception e){
            throw new PlaysException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> object2row(Directors object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("director_id",object.getId());
        item.put("first_name",object.getFirst_name());
        return item;
    }
    @Override
    public Directors searchByDirectorName(String name) throws PlaysException {

        return executeQueryUnique("SELECT * FROM Directors WHERE first_name = ?",new Object[]{name});
    }
    @Override
    public Directors searchById(int Id) throws PlaysException{
        return getById(Id);
    }
}
