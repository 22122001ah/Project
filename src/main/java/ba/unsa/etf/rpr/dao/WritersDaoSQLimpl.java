package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Writers;

import java.io.FileReader;
import java.sql.*;
import java.util.*;
import ba.unsa.etf.rpr.exceptions.PlaysException;
public class WritersDaoSQLimpl extends AbstractDao1<Writers> implements WritersDao {

    public WritersDaoSQLimpl(){
        super("Writer");
    }

    @Override
    public Writers row2object(ResultSet rs) throws PlaysException {
        try{
           Writers w=new Writers();
            w.setId(rs.getInt("writer_id"));
            w.setFirst_name(rs.getString("FirstName"));
            w.setLast_name(rs.getString("LastName"));
           return w;}
        catch (Exception e){
          throw new PlaysException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> object2row(Writers object) {

        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("writer_id",object.getId());
        item.put("first_name",object.getFirst_name());
        item.put("last_name",object.getLast_name());
        return item;
    }
    @Override
    public List<Writers> searchByWriterName(String name) throws PlaysException {
        return executeQuery("SELECT * FROM Writers WHERE concat(FirstName,LastName) = ?",new Object[]{name});
    }
    @Override
    public Writers searchById(int Id) throws PlaysException{
        return executeQueryUnique("SELECT * FROM Writers WHERE id = ?",new Object[]{Id});
    }
}
