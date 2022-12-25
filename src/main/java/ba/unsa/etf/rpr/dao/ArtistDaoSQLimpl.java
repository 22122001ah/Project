package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ArtistDaoSQLimpl  extends AbstractDao1<Artist> implements ArtistDao {
    public ArtistDaoSQLimpl(){
        super("Artist");
    }

    @Override
    public Artist row2object(ResultSet rs) throws PlaysException {
       try{ Artist artist = new Artist();
        artist.setId(rs.getInt("artist_id"));
        artist.setArtist_name(rs.getString("artist_name"));

        return artist;}
       catch(Exception e){
           throw new PlaysException(e.getMessage(),e);
       }
    }

    @Override
    public Map<String, Object> object2row(Artist object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("artist_name",object.getArtist_name());
        item.put("artist_id",object.getId());
        return item;
    }
    @Override
    public List<Artist> searchByArtistName(String name) throws PlaysException {
        return executeQuery("SELECT * FROM Artist WHERE artist_name = ?",new Object[]{name});
    }
    @Override
    public Artist searchById(int Id) throws PlaysException{
        return executeQueryUnique("SELECT * FROM Artist WHERE id = ?",new Object[]{Id});
    }
}
