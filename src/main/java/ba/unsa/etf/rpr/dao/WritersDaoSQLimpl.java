package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Writers;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

public class WritersDaoSQLimpl extends AbstractDao1<Writers> implements WritersDao {

    public WritersDaoSQLimpl(){
        super("Writer");
     /*   try {
            FileReader reader = new FileReader("src/main/resources/database.properties");
            Properties p = new Properties();
            p.load(reader);
            String url = p.getProperty("url");
            String user = p.getProperty("username");
            String password = p.getProperty("password");
            this.connection = DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public Writers row2object(ResultSet rs) throws Exception {
        try{
           Writers w=new Writers();
            w.setId(rs.getInt("writer_id"));
            w.setFirst_name(rs.getString("FirstName"));
            w.setLast_name(rs.getString("LastName"));
           return w;}
        catch (Exception e){
            System.out.println(e);
        }
        return null;
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
    public Writers getById(int id) {
        String query = "SELECT * FROM Writers where Writer_id = ?";
        try{

            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setObject(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                return row2object(rs);
            }

        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    @Override
    public List<Writers> getAll() {
        String query = "SELECT * FROM Writers";
        List<Writers> writers = new ArrayList<Writers>();
        try{
            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                writers.add(row2object(rs));
            }
            return  writers;
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null ;

    }

    @Override
    public Writers add(Writers write) throws Exception {
        return null;
    }
}
