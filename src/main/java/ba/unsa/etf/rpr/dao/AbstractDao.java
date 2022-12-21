package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

/**
 * Abstract class that implements core DAO CRUD methods for every entity
 */
public abstract class AbstractDao<T extends Idable> implements Dao<T>{
    protected Connection connection;
    private String tableName;

    public AbstractDao(String tableName) {
        try{
            this.tableName = tableName;
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("database.properties").openStream());
            String url = p.getProperty("url");
            String user = p.getProperty("username");
            String password = p.getProperty("password");
            this.connection=DriverManager.getConnection(url,user,password);

        }catch (Exception e){
            System.out.println("nemoguce uraditi konekciju na bazu");
            e.printStackTrace();

        }
    }

    public Connection getConnection(Connection connection){
        return this.connection;
    }

    public abstract T row2object(ResultSet rs) throws Exception;

    public abstract Map<String, Object> object2row(T object);

    public T getById(int id) throws Exception {
        String query = "SELECT * FROM "+this.tableName+" WHERE id = ?";
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
        String query = "SELECT * FROM "+ tableName;
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
    }}
