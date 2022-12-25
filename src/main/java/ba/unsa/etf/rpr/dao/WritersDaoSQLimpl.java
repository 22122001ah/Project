package ba.unsa.etf.rpr.dao;

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
    public Writers getById(int id) throws PlaysException {
        String query = "SELECT * FROM Writers where Writer_id = ?";
        try{

            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setObject(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                return row2object(rs);
            }

        } catch (Exception e) {
            throw new PlaysException(e.getMessage(),e);
        }
        return null;

    }

    @Override
    public List<Writers> getAll() throws PlaysException {
        String query = "SELECT * FROM Writers";
        List<Writers> writers = new ArrayList<Writers>();
        try{
            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                writers.add(row2object(rs));
            }
            return  writers;
        } catch (Exception e) {
            throw new PlaysException(e.getMessage(),e);
        }
    }
    @Override
    public Writers add(Writers write) throws PlaysException {
        return null;
    }
}
