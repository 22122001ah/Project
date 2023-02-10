package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Writers;


import java.util.*;
import java.sql.*;
import java.util.Date;
import ba.unsa.etf.rpr.exceptions.PlaysException;

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
            play.setWriter(DaoFactory.writersDao().getById(rs.getInt("Writer_id")));
            play.setDirector(DaoFactory.directorsDao().getById(rs.getInt("dir_id")));
            play.setMaxcap(rs.getInt("maxcap"));
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
        item.put("Writer_id",object.getWriter().getId());
        item.put("dir_id",object.getDirector().getId());
        item.put("date",object.getDate());
        item.put("maxcap",object.getMaxcap());
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
    @Override
    public  boolean isInDB(List<Plays> plays) throws PlaysException {
        List<Plays>plays1=DaoFactory.playsDao().searchByPlayName(plays.get(0).getPlay_name());
        if(plays1.size()==0)
            return false;
        return true;
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
        return executeQuery("SELECT * FROM Plays WHERE Writer_id = ?",new Object[]{writer.getId()});
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
@Override
public List<Plays> searchByPlaynameandPriceandGenre(String play_name,int price,String genre) throws PlaysException {
        return executeQuery("SELECT * FROM Plays WHERE  play_name LIKE concat('%', ?, '%') AND price BETWEEN ? AND ? AND  genre LIKE concat('%', ?, '%') ",new Object[]{play_name,0,price,genre});
    }
    @Override
    public List<Plays> searchByPlaynameandGenre(String play_name,String genre) throws PlaysException {
        return executeQuery("SELECT * FROM Plays WHERE  play_name LIKE concat('%', ?, '%')  AND  genre LIKE concat('%', ?, '%') ",new Object[]{play_name,genre});
    }
    @Override
    public List<Plays> searchByPlaynameandPrice(String play_name,int price) throws PlaysException {
        return executeQuery("SELECT * FROM Plays WHERE  play_name LIKE concat('%', ?, '%') AND price BETWEEN ? AND ? ",new Object[]{play_name,0,price});
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
