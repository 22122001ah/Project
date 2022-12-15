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
            FileReader reader = new FileReader("src/main/resources/database.properties");
            Properties p = new Properties();
            p.load(reader);
            String url = p.getProperty("url");
            String user = p.getProperty("username");
            String password = p.getProperty("password");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Plays> getAll(){
        String query = "SELECT * FROM Plays";
        List<Plays> plays = new ArrayList<Plays>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Plays play = new Plays();
                play.setPlay_id(rs.getInt("play_id"));
                play.setPlay_name(rs.getString("play_name"));
                play.setDate(rs.getDate("date"));
                play.setPrice(rs.getInt("price"));
                play.setPick_up_location(rs.getString("pick_up_location"));
                play.setGenre(rs.getString("genre"));
                plays.add(play);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return plays;
    }
    @Override
    public Plays getById(int id){
        String query = "SELECT * FROM Plays WHERE play_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // result set is iterator.
                Plays play = new Plays();
                play.setPlay_id(rs.getInt("play_id"));
                play.setPlay_name(rs.getString("play_name"));
                play.setDate(rs.getDate("date"));
                play.setPrice(rs.getInt("price"));
                play.setPick_up_location(rs.getString("pick_up_location"));
                play.setGenre(rs.getString("genre"));
                rs.close();
                return play;
            } else {
                return null; // if there is no elements in the result set return null
            }
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

    /**
     * searches Plays based on prices
     * @param prices
     * @return List of Plays with wanted price
     */
    @Override
    public List<Plays> searchByPrice(int prices){
        String query = "SELECT * FROM Plays WHERE price LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, prices);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Plays> PlaysLista = new ArrayList<>();
            while (rs.next()) {
                Plays q = new Plays();
                q.setPlay_id(rs.getInt("play_id"));
                q.setPlay_name(rs.getString("play_name"));
                q.setPrice((rs.getInt("price")));
                q.setDate(rs.getDate("date"));
                q.setPick_up_location(rs.getString("pick_up_location"));
                q.setGenre(rs.getString("genre"));
                PlaysLista.add(q);
            }
            return PlaysLista;
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
        String query = "SELECT * FROM Plays WHERE writer LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, writer.getWriter_id());
            ResultSet rs = stmt.executeQuery();
            ArrayList<Plays> PlaysLista = new ArrayList<>();
            while (rs.next()) {
                Plays q = new Plays();
                q.setPlay_id(rs.getInt("play_id"));
                q.setPlay_name(rs.getString("play_name"));
                q.setPrice((rs.getInt("price")));
                q.setDate(rs.getDate("date"));
                q.setPick_up_location(rs.getString("pick_up_location"));
                q.setGenre(rs.getString("genre"));
                PlaysLista.add(q);
            }
            return PlaysLista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Searches for Plays directed by wanted director
     * @param director
     * @return List of Plays directed by wanted director
     */
    @Override
    public List<Plays> searchByDirector(Directors director){
        String query = "SELECT * FROM Plays WHERE director LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, director.getDirector_id());
            ResultSet rs = stmt.executeQuery();
            ArrayList<Plays> PlaysLista = new ArrayList<>();
            while (rs.next()) {
                Plays q = new Plays();
                q.setPlay_id(rs.getInt("play_id"));
                q.setPlay_name(rs.getString("play_name"));
                q.setPrice((rs.getInt("price")));
                q.setDate(rs.getDate("date"));
                q.setPick_up_location(rs.getString("pick_up_location"));
                q.setGenre(rs.getString("genre"));
                PlaysLista.add(q);
            }
            return PlaysLista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * searches for Plays that are playing on a certain date
     * @param date
     * @return List of Plays playing on a certain date
     */
    @Override
    public List<Plays> searchByDate(Date date){
        String query = "SELECT * FROM Plays WHERE date LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setDate(1, (java.sql.Date) date);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Plays> PlaysLista = new ArrayList<>();
            while (rs.next()) {
                Plays q = new Plays();
                q.setPlay_id(rs.getInt("play_id"));
                q.setPlay_name(rs.getString("play_name"));
                q.setPrice((rs.getInt("price")));
                q.setDate(rs.getDate("date"));
                q.setPick_up_location(rs.getString("pick_up_location"));
                q.setGenre(rs.getString("genre"));
                PlaysLista.add(q);
            }
            return PlaysLista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * searches Plays by name
     * @param play_name
     * @return Play with that name
     */
    @Override
   public Plays searchByPlayName(String play_name){
        String query = "SELECT * FROM Plays WHERE play_name = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1,play_name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // result set is iterator.
                Plays play = new Plays();
                play.setPlay_id(rs.getInt("play_id"));
                play.setPlay_name(rs.getString("play_name"));
                play.setDate(rs.getDate("date"));
                play.setPrice(rs.getInt("price"));
                play.setPick_up_location(rs.getString("pick_up_location"));
                play.setGenre(rs.getString("genre"));
                rs.close();
                return play;
            } else {
                return null; // if there is no elements in the result set return null
            }
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        }
        return null;
    }
}
