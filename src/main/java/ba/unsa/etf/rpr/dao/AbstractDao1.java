package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;

import ba.unsa.etf.rpr.domain.Idable;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public abstract class AbstractDao1 <T extends Idable> implements Dao<T> {
    protected Connection connection;
    private String Table;
    public AbstractDao1(String tableName){
       this.Table=tableName;
       try{
           Properties p=new Properties();
           p.load(ClassLoader.getSystemResource("database.properties").openStream());
           String url=p.getProperty("url");
           String user=p.getProperty("username");
           String pass=p.getProperty("password");
           this.connection= DriverManager.getConnection(url,user,pass);
       } catch (IOException e) {
           throw new RuntimeException(e);
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

    public Connection getConnection(Connection connection){
        return this.connection;
    }

    public abstract T row2object(ResultSet rs) throws Exception;

    public abstract Map<String, Object> object2row(T object);

    public T getById(int id) throws Exception {
        String query = "SELECT * FROM "+this.Table+" WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // result set is iterator.
                T result = row2object(rs);
                rs.close();
                return result;
            } else {
                throw new Exception("Object not found");
            }
        } catch (SQLException e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public List<T> getAll() throws Exception {
        String query = "SELECT * FROM "+ Table;
        List<T> results = new ArrayList<T>();
        try{
            PreparedStatement stmt = getConnection(connection).prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                T object = row2object(rs);
                results.add(object);
            }
            rs.close();
            return results;
        }catch (SQLException e){
            throw new Exception(e.getMessage(), e);
        }
    }
}
