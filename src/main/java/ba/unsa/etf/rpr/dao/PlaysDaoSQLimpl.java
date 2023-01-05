package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Writers;


import java.util.*;
import java.sql.*;
import java.util.Date;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import static java.sql.DriverManager.getConnection;

public class PlaysDaoSQLimpl extends AbstractDao1<Plays> implements PlaysDao{


    public PlaysDaoSQLimpl(){

        super("Plays");
    }
    @Override
    public Plays row2object(ResultSet rs) throws PlaysException {
        try{
            Plays play = new Plays();
            play.setId(rs.getInt("play_id"));
            play.setPlay_name(rs.getString("play_name"));
            play.setDate(rs.getDate("date"));
            play.setPrice(rs.getInt("price"));
            play.setGenre(rs.getString("genre"));
            play.setWriter(DaoFactory.writersDao().getById(rs.getInt("writer_id")));
            play.setDirector(DaoFactory.directorsDao().getById(rs.getInt("dir_id")));
            return play;}
        catch (Exception e){
            throw new PlaysException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> object2row(Plays object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("play_name",object.getPlay_name());
        item.put("play_id",object.getId());
        item.put("genre",object.getGenre());
        item.put("price",object.getPrice());
        item.put("writer_id",object.getWriter().getId());
        item.put("dir_id",object.getDirector().getId());
        item.put("date",object.getDate());
        return item;
    }

    /**
     * searches Plays based on prices
     * @param prices
     * @return List of Plays with wanted price
     */
    @Override
    public List<Plays> searchByPrice(int prices) throws PlaysException{
        return executeQuery("SELECT * FROM Plays WHERE price = ? ",new Object[]{prices});
    }

    /**
     * searches for Plays in given price range
     * @param price1,price2
     * @return List of Plays in given price range
     */
    @Override
    public List<Plays> searchByPrices(int price1,int price2) throws PlaysException {
        return executeQuery("SELECT * FROM Plays WHERE price BETWEEN ? AND ?",new Object[]{price1,price2});
    }

    /**
     * searches for Plays written by wanted writer
     * @param writer
     * @return List of Plays written by wanted writer
     */
    @Override
    public List<Plays> searchByWriter(Writers writer) throws PlaysException{
        return executeQuery("SELECT * FROM Plays WHERE writer_id LIKE concat('%', ?, '%')",new Object[]{writer.getId()});
    }

    /**
     * Searches for Plays directed by wanted director
     * @param director
     * @return List of Plays directed by wanted director
     */
    @Override
    public List<Plays> searchByDirector(Directors director)throws PlaysException{
        return executeQuery("SELECT * FROM Plays WHERE director_id LIKE concat('%', ?, '%')",new Object[]{director.getId()});
    }

    /**
     * searches for Plays that are playing on a certain date
     * @param date
     * @return List of Plays playing on a certain date
     */
    @Override
    public List<Plays> searchByDate(Date date) throws PlaysException{
        return executeQuery("SELECT * FROM Plays WHERE date LIKE concat('%', ?, '%')",new Object[]{date});

    }

    /**
     * searches Plays by name
     * @param play_name
     * @return Play with that name
     */
    @Override
    public List<Plays> searchByPlayName(String play_name)throws PlaysException{
        return executeQuery("SELECT * FROM Plays WHERE play_name LIKE concat('%', ?, '%')",new Object[]{play_name});
    }
    @Override
    public List<String>getAllGenres() throws PlaysException{
        Plays p= null;
        try {
            p = new Plays();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ArrayList<String>genres=new ArrayList<String>();
        ArrayList<Plays>play=new ArrayList<>();
        try {
            play=(ArrayList<Plays>) p.getAll();
        }
        catch (Exception e){
            throw new PlaysException(e.getMessage(),e);
        }
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
    public List<Plays> searchByGenre(String genre) throws PlaysException{
        return executeQuery("SELECT * FROM Plays WHERE genre LIKE concat('%', ?, '%')",new Object[]{genre});
    }
    @Override
    public Plays randomPlay() throws PlaysException {
        return executeQueryUnique("SELECT * FROM Plays ORDER BY RAND() LIMIT 1",null);
    }

}
