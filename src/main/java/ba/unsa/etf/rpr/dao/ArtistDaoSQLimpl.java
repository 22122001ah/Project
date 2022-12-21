package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Writers;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ArtistDaoSQLimpl extends AbstractDao<Artist> implements ArtistDao {
    private Connection connection;
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
    public Artist row2object(ResultSet rs) throws Exception {
        return null;
    }

    @Override
    public Map<String, Object> object2row(Artist object) {
        return null;
    }

    @Override
    public Artist getById(int id) {
        String query = "SELECT * FROM Artist where artist_id=?";
        List<Artist> artists = new ArrayList<Artist>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Artist artist = new Artist();
                artist.setArtist_id(rs.getInt("artist_id"));
                artist.setArtist_name(rs.getString("artist_name"));
                rs.close();
                return artist;
            }

        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;

    }

    @Override
    public List<Artist> getAll() {
        String query = "SELECT * FROM Artist";
        List<Artist> artists = new ArrayList<Artist>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Artist artist = new Artist();
                artist.setArtist_id(rs.getInt("artist_id"));
                artist.setArtist_name(rs.getString("Name"));
                artists.add(artist);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return artists ;

    }
}
