package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.domain.plays_in;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class plays_inDaoSQLimpl extends AbstractDao1<plays_in> implements plays_inDao{
    int id;
    public plays_inDaoSQLimpl() {
        super("plays_in");
    }

    @Override
    public plays_in row2object(ResultSet rs) throws Exception {
        try {
            plays_in plays_in = new plays_in();
            plays_in.setId(rs.getInt("plays_in"));
            plays_in.setArtist_id(rs.getInt("username"));
            plays_in.setPlays_id(rs.getInt("password"));
            return plays_in;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Map<String, Object> object2row(plays_in object) {
        return null;
    }

    @Override
    public plays_in add(plays_in playsin) throws Exception {
        return null;
    }

    @Override
    public List<Plays> searchByArtist(Artist artist) {
        return null;
    }

    @Override
    public List<Artist> searchByPlay(Plays play) {
        return null;
    }
}
