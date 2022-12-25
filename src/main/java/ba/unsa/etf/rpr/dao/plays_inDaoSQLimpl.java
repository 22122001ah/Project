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
    public plays_in add(plays_in playsin) throws PlaysException {
        return null;
    }

    @Override
    public List<Plays> searchByArtist(Artist artist) throws PlaysException {
        String query = "SELECT * FROM plays_in WHERE artist_id = ? ";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, artist.getId());
            ResultSet rs = stmt.executeQuery();
            Plays p=new Plays();
            ArrayList<Plays> PlaysLista = new ArrayList<>();
            while (rs.next()) {
                p=p.getById(row2object(rs).getPlays_id());
                PlaysLista.add(p);
            }
            rs.close();
            return PlaysLista;
        } catch (Exception e) {
            throw new PlaysException(e.getMessage(),e);
        }
}

    @Override
    public List<Artist> searchByPlay(Plays play) throws PlaysException {
        String query = "SELECT * FROM plays_in WHERE plays_id = ? ";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, play.getId());
            ResultSet rs = stmt.executeQuery();
            Artist p=new Artist();
            ArrayList<Artist> ArtistLista = new ArrayList<>();
            while (rs.next()) {
                int s=row2object(rs).getArtist_id();
                p=p.getById(s);
                ArtistLista.add(p);
            }
            rs.close();
            return ArtistLista;
        } catch (Exception e) {
            throw new PlaysException(e.getMessage(),e);
        }
    }
}
