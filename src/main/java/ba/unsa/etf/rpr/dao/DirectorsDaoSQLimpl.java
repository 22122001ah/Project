package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Directors;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DirectorsDaoSQLimpl implements DirectorsDao {
    private Connection connection;
    public DirectorsDaoSQLimpl(){
        try {
            FileReader reader = new FileReader("");
            Properties p = new Properties();
            p.load(reader);
            String url = p.getProperty("url");
            String user = p.getProperty("username");
            String password = p.getProperty("password");
            this.connection = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7582716", "sql7582716", "yx92Ppzp5V");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public Directors getById(int id) {
        String query = "SELECT * FROM Directors where director_id=?";
        List<Directors> directors = new ArrayList<Directors>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Directors director = new Directors();
                director.setDirector_id(rs.getInt("director_id"));
                director.setFirst_name(rs.getString("FirstName"));
                director.setLast_name(rs.getString("LastName"));
                rs.close();
                return director;
            }

        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;

    }

    @Override
    public List<Directors> getAll() {
        String query = "SELECT * FROM Directors";
        List<Directors> directors = new ArrayList<Directors>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Directors director = new Directors();
                director.setDirector_id(rs.getInt("director_id"));
                director.setFirst_name(rs.getString("FirstName"));
                director.setLast_name(rs.getString("LastName"));
                directors.add(director);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return directors ;

    }
}
