package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.plays_in;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class plays_inDaoSQLimpl extends AbstractDao<plays_in> implements plays_inDao{
    public plays_inDaoSQLimpl() {
        super("plays_in");
    }

    @Override
    public plays_in row2object(ResultSet rs) throws Exception {
        return null;
    }

    @Override
    public Map<String, Object> object2row(plays_in object) {
        return null;
    }

    @Override
    public plays_in getById(int id) {
        return null;
    }

    @Override
    public List<plays_in> getAll() {
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
