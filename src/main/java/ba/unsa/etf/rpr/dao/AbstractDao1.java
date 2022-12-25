package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.PlaysException;

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
@Override
    public T getById(int id) throws Exception {
        String query = "SELECT * FROM "+this.Table+" WHERE id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
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
    public Connection getConnection(){
        return this.connection;
    }
    @Override
    public List<T> getAll() throws Exception {
        String query = "SELECT * FROM "+ Table;
        List<T> results = new ArrayList<T>();
        try{
            PreparedStatement stmt = getConnection().prepareStatement(query);
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
    @Override
    public T add(T item) throws Exception{
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(Table);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            // bind params. IMPORTANT treeMap is used to keep columns sorted so params are bind correctly
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back */

            return item;
        }catch (Exception e){
            System.out.println(e);
        }
        return item;
    }

    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip insertion of id due autoincrement
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<String,String>(columns.toString(), questions.toString());
    }
    public List<T> executeQuery(String query, Object[] params) throws  PlaysException {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            if (params != null){
                for(int i = 1; i <= params.length; i++){
                    stmt.setObject(i, params[i-1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<T> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(row2object(rs));
            }
            return resultList;
        } catch (Exception e) {
            throw new PlaysException(e.getMessage(), e);
        }
    }
}
