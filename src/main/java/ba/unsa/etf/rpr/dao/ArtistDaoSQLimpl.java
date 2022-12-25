package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
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
      /*  try {
            FileReader reader = new FileReader("");
            Properties p = new Properties();
            p.load(reader);
            String url = p.getProperty("url");
            String user = p.getProperty("username");
            String password = p.getProperty("password");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
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
    public Artist getById(int id) throws PlaysException{
        try { String query = "SELECT * FROM Artist where artist_id=?";

            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // result set is iterator.
                return row2object(rs);
            }
            return null;
        }catch (Exception e){
            throw new PlaysException(e.getMessage(),e);
        }
    }

    @Override
    public List<Artist> getAll() throws PlaysException{
        String query = "SELECT * FROM Artist";
        List<Artist> artists = new ArrayList<Artist>();
        try{
            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.

                artists.add(row2object(rs));
            }
            return artists;
        }catch (Exception e){
            throw new PlaysException(e.getMessage(),e);
        }
    }

    @Override
    public Artist add(Artist A) throws PlaysException {
        return null;
    }
}
