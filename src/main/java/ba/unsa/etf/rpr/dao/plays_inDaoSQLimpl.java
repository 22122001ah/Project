package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.domain.plays_in;
import ba.unsa.etf.rpr.exceptions.PlaysException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class plays_inDaoSQLimpl extends AbstractDao1<plays_in> implements plays_inDao{
    public plays_inDaoSQLimpl() {
        super("plays_in");
    }

    @Override
    public plays_in row2object(ResultSet rs) throws PlaysException {
        try {
            plays_in plays_in = new plays_in();
            plays_in.setId(rs.getInt("plays_in"));
            plays_in.setArtist_id(rs.getInt("username"));
            plays_in.setPlays_id(rs.getInt("password"));
            return plays_in;
        } catch (Exception e) {
            throw new PlaysException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> object2row(plays_in object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("play_id",object.getPlays_id());
        item.put("playsIn_id",object.getId());
        item.put("artist_id",object.getArtist_id());
        return item;
    }
    @Override
    public List<Plays> searchByArtist(Artist artist) throws PlaysException {
        List<plays_in> p=executeQuery("SELECT * FROM plays_in WHERE artist_id = ? ",new Object[]{artist.getId()});
        try {
            Plays play=new Plays();
            ArrayList<Plays> PlaysLista = new ArrayList<>();
           for(int i=0;i<p.size();i++)
           {
               PlaysLista.add(play.getById(p.get(i).getId()));
           }
            return PlaysLista;
        } catch (Exception e) {
            throw new PlaysException(e.getMessage(),e);
        }
}

    @Override
    public List<Artist> searchByPlay(Plays play) throws PlaysException {
        List<plays_in>p = executeQuery("SELECT * FROM plays_in WHERE plays_id = ? ",new Object[]{play.getId()});
        try {

            Artist artist=new Artist();
            ArrayList<Artist> ArtistLista = new ArrayList<>();
         for(int i=0;i<p.size();i++){
             ArtistLista.add(artist.getById(p.get(i).getId()));
         }
            return ArtistLista;
        } catch (Exception e) {
            throw new PlaysException(e.getMessage(),e);
        }
    }
}
