package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Writers;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.sql.*;
import java.util.Date;

import static java.sql.DriverManager.getConnection;

public class PlaysDaoSQLimpl extends AbstractDao1<Plays> implements PlaysDao{


    public PlaysDaoSQLimpl() throws SQLException, IOException {

        super("Plays");
    }
    @Override
    public List<Plays> getAll(){
        String query = "SELECT * FROM Plays";
        List<Plays> plays = new ArrayList<Plays>();
        try{
            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                plays.add(row2object(rs));
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return plays;
    }

    @Override
    public Plays add(Plays play) throws Exception {
        return null;
    }

    @Override
    public Plays row2object(ResultSet rs) throws Exception {
try{
            Plays play = new Plays();
            play.setPlay_id(rs.getInt("play_id"));
            play.setPlay_name(rs.getString("play_name"));
            play.setDate(rs.getDate("date"));
            play.setPrice(rs.getInt("price"));
            play.setDescription(rs.getString("Description"));
            play.setGenre(rs.getString("genre"));
       play.setWriter(DaoFactory.writersDao().getById(rs.getInt("writer_id")));
    play.setDirector(DaoFactory.directorsDao().getById(rs.getInt("dir_id")));
        return play;}
catch (Exception e){
    System.out.println(e);
}
return null;
    }

    @Override
    public Map<String, Object> object2row(Plays object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("play_name",object.getPlay_name());
        item.put("play_id",object.getPlay_id());
        item.put("genre",object.getGenre());
        item.put("Description",object.getDescription());
        item.put("price",object.getPrice());
        item.put("writer_id",object.getWriter());
        item.put("dir_id",object.getDirector());
        item.put("date",object.getDate());
        return item;
    }

    @Override
    public Plays getById(int id){
        String query = "SELECT * FROM Plays WHERE play_id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // result set is iterator.

              return row2object(rs);

            } else {
                return null; // if there is no elements in the result set return null
            }
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        String query = "SELECT * FROM Plays WHERE price = ? ";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, prices);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Plays> PlaysLista = new ArrayList<>();
            while (rs.next()) {
            PlaysLista.add(row2object(rs));
            }
            rs.close();
            return PlaysLista;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * searches for Plays in given price range
     * @param price1,price2
     * @return List of Plays in given price range
     */
    @Override
   public List<Plays> searchByPrices(int price1,int price2) throws Exception {
        List<Plays> plays = new ArrayList<>();
        try{

            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM Plays WHERE price BETWEEN ? AND ?;");
            stmt.setObject(1, price1);
            stmt.setObject(2, price2);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                plays.add(row2object(rs));
            }
            rs.close();
            return plays;
        }catch (SQLException e){
            throw new Exception(e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, writer.getWriter_id());
            ResultSet rs = stmt.executeQuery();
            ArrayList<Plays> PlaysLista = new ArrayList<>();
            while (rs.next()) {

                PlaysLista.add(row2object(rs));
            }
            rs.close();
            return PlaysLista;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
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
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, director.getDirector_id());
            ResultSet rs = stmt.executeQuery();
            ArrayList<Plays> PlaysLista = new ArrayList<>();
            while (rs.next()) {

                PlaysLista.add(row2object(rs));
            }
            rs.close();
            return PlaysLista;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
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
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setDate(1, (java.sql.Date) date);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Plays> PlaysLista = new ArrayList<>();
            while (rs.next()) {

                PlaysLista.add(row2object(rs));
            }
            rs.close();
            return PlaysLista;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
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
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1,play_name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // result set is iterator.

                return row2object(rs);
            } else {
                return null; // if there is no elements in the result set return null
            }
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public List<String>getAllGenres(){
        Plays p= null;
        try {
            p = new Plays();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String>genres=new ArrayList<String>();
        ArrayList<Plays>play=new ArrayList<>();
        play=(ArrayList<Plays>) p.getAll();
        genres.add(play.get(0).getGenre());
        for(int i=0;i<play.size();i++)
        { int j=0;
            for(j=0;j<genres.size();j++)
            {
                if(Objects.equals(genres.get(j),play.get(i).getGenre()))
                    break;
            }
            if(j==genres.size())
                genres.add(play.get(i).getGenre());
        }
        return genres;
    }
    @Override
    public List<Plays> searchByGenre(String genre){
        String query = "SELECT * FROM Plays WHERE genre LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, genre);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Plays> PlaysLista = new ArrayList<>();
            while (rs.next()) {

                PlaysLista.add(row2object(rs));
            }
            rs.close();
            return PlaysLista;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
