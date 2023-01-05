package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Writers;

import java.sql.*;
import java.util.*;
import ba.unsa.etf.rpr.exceptions.PlaysException;
public class WritersDaoSQLimpl extends AbstractDao1<Writers> implements WritersDao {

    public WritersDaoSQLimpl(){
        super("Writers");
    }

    @Override
    public Writers row2object(ResultSet rs) throws PlaysException {
        try{
            Writers w=new Writers();
            w.setId(rs.getInt("writer_id"));
            w.setFirst_name(rs.getString("FirstName"));
            return w;}
        catch (Exception e){
            throw new PlaysException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> object2row(Writers object) {

        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("writer_id",object.getId());
        item.put("FirstName",object.getFirst_name());
        return item;
    }
    @Override
    public Writers searchByWriterName(String name) throws PlaysException {
        return executeQueryUnique("SELECT * FROM Writers WHERE FirstName = ?",new Object[]{name});
    }
    @Override
    public Writers searchById(int Id) throws PlaysException{
        return getById(Id);
    }
}
