package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.plays_in;

import java.util.List;

public class plays_inDaoSQLimpl implements plays_inDao{
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
