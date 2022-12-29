package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ArtistsDaoSQLimpl extends AbstractDao1<Artists> implements ArtistsDao {
    public ArtistsDaoSQLimpl(){
        super("Artists");
    }

    @Override
    public Artists row2object(ResultSet rs) throws PlaysException {
       try{ Artists artists = new Artists();
        artists.setId(rs.getInt("artist_id"));
        artists.setArtist_name(rs.getString("artist_name"));

        return artists;}
       catch(Exception e){
           throw new PlaysException(e.getMessage(),e);
       }
    }

    @Override
    public Map<String, Object> object2row(Artists object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("artist_name",object.getArtist_name());
        item.put("artist_id",object.getId());
        return item;
    }
    @Override
    public List<Artists> searchByArtistName(String name) throws PlaysException {
        return executeQuery("SELECT * FROM Artist WHERE artist_name = ?",new Object[]{name});
    }
    @Override
    public Artists searchById(int Id) throws PlaysException{
        return getById(Id);
    }
}
