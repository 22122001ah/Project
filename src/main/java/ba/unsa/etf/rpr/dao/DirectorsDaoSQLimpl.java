package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Writers;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DirectorsDaoSQLimpl extends AbstractDao1<Directors> implements DirectorsDao {

    public DirectorsDaoSQLimpl(){
        super("Directors");
      /*  try {
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
    public Directors row2object(ResultSet rs) throws Exception {
        try{

            Directors d=new Directors();
            d.setDirector_id(rs.getInt("dir_id"));
          d.setLast_name(rs.getString("last_name"));
          d.setFirst_name(rs.getString("first_name"));
            return d;}
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Map<String, Object> object2row(Directors object) {
        return null;
    }

    @Override
    public Directors getById(int id) {
        String query = "SELECT * FROM Directors where director_id=?";
        List<Directors> directors = new ArrayList<Directors>();
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
    public List<Directors> getAll() {
        String query = "SELECT * FROM Directors";
        List<Directors> directors = new ArrayList<Directors>();
        try{
            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.

                directors.add(row2object(rs));
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return directors ;

    }

    @Override
    public Directors add(Directors director) throws Exception {
        return null;
    }
}
