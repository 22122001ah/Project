package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.plays_ins;
import ba.unsa.etf.rpr.exceptions.PlaysException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class plays_inDaoSQLimpl extends AbstractDao1<plays_ins> implements plays_inDao{
    public plays_inDaoSQLimpl() {
        super("plays_ins");
    }

    @Override
    public plays_ins row2object(ResultSet rs) throws PlaysException {
        try {
            plays_ins plays_in = new plays_ins();
            plays_in.setId(rs.getInt("plays_in_id"));
            plays_in.setArtist_id(rs.getInt("Artist_id"));
            plays_in.setPlays_id(rs.getInt("play_id"));
            return plays_in;
        } catch (Exception e) {
            throw new PlaysException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> object2row(plays_ins object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("plays_in_id",object.getId());
        item.put("play_id",object.getPlays_id());
        item.put("Artist_id",object.getArtist_id());
        return item;
    }
    @Override
    public List<Plays> searchByArtist(Artists artists) throws PlaysException {
        List<plays_ins> p=executeQuery("SELECT * FROM plays_ins WHERE Artist_id = ? ",new Object[]{artists.getId()});
        try {
            Plays play=new Plays();
            ArrayList<Plays> PlaysLista = new ArrayList<>();
            for(int i=0;i<p.size();i++)
            {
                PlaysLista.add(play.getById(p.get(i).getPlays_id()));
            }
            return PlaysLista;
        } catch (Exception e) {
            throw new PlaysException(e.getMessage(),e);
        }
    }

    @Override
    public List<Artists> searchByPlay(Plays play) throws PlaysException {
        List<plays_ins>p = executeQuery("SELECT * FROM plays_ins WHERE play_id = ? ",new Object[]{play.getId()});
        try {
            List<Artists> artistsLista = new ArrayList<>();
            for(int i=0;i<p.size();i++){
                artistsLista.add(DaoFactory.artistDao().getById(p.get(i).getArtist_id()));
            }
            return artistsLista;
        } catch (Exception e) {
            throw new PlaysException(e.getMessage(),e);
        }
    }
    @Override
    public List<plays_ins> searchArtists(Artists artists) throws PlaysException {
        return executeQuery("SELECT * FROM plays_ins WHERE Artist_id = ? ",new Object[]{artists.getId()});
    }
    @Override
    public List<plays_ins> searchPlays(Plays play) throws PlaysException {
      return  executeQuery("SELECT * FROM plays_ins WHERE play_id = ? ",new Object[]{play.getId()});
    }
}
