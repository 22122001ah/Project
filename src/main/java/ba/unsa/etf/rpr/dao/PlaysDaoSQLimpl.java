package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Writers;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.*;
import java.util.Properties;
public class PlaysDaoSQLimpl implements PlaysDao{
    private Connection connection;
    public PlaysDaoSQLimpl(){
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
    public List<Plays> getAll(){
        return null;
    }
    @Override
    public Plays getById(int id){
return null;
    }
    @Override
    public Plays add(Plays P){
        return null;
    }
    @Override
    public Plays update(Plays P){
       return null;
    }
    @Override
    public void delete(int id){

    }
    /**
     * searches Plays based on prices
     * @param prices
     * @return List of Plays with wanted price
     */
    @Override
    public List<Plays> searchByPrice(int prices){
        String query = "SELECT * FROM quotes WHERE quote LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, String.valueOf(prices));
            ResultSet rs = stmt.executeQuery();
            ArrayList<Plays> quoteLista = new ArrayList<>();
            while (rs.next()) {
                Plays q = new Plays();
                q.setPlay_id(rs.getInt(1));
                q.setPlay_name(rs.getString(2));
                q.setPrice((rs.getInt(4)));
                q.setDate(rs.getDate(3));
                quoteLista.add(q);
            }
            return quoteLista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * searches for Plays in given price range
     * @param price1,price2
     * @return List of Plays in given price range
     */
    @Override
   public List<Plays> searchByPrices(int price1,int price2){
        return null;

    }

    /**
     * searches for Plays written by wanted writer
     * @param writer
     * @return List of Plays written by wanted writer
     */
    @Override
   public List<Plays> searchByWriter(Writers writer){
        return null;

    }

    /**
     * Searches for Plays directed by wanted director
     * @param director
     * @return List of Plays directed by wanted director
     */
    @Override
    public List<Plays> searchByDirector(Directors director){
        return null;

    }

    /**
     * searches for Plays staring a certain Actor
     * @param artist
     * @return List of Plays staring a certain Actor
     */
    @Override
    public List<Plays> searchByArtist(Artist artist){
        return null;

    }

    /**
     * searches for Plays that are playing on a certain date
     * @param date
     * @return List of Plays playing on a certain date
     */
    @Override
    public List<Plays> searchByDate(Date date){
        return null;

    }

    /**
     * searches Plays by name
     * @param play_name
     * @return Play with that name
     */
    @Override
   public Plays searchByPlayName(String play_name){
        return null;

    }
}