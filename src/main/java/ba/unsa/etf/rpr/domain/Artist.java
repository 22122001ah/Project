package ba.unsa.etf.rpr.domain;
import ba.unsa.etf.rpr.dao.ArtistDaoSQLimpl;

import java.util.Objects;


/**
 * List of possible actors for plays
 * @author Adna Herak
 */
public class Artist extends ArtistDaoSQLimpl {
    private int artist_id;
    private String artist_name;

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public String getArtist_name() {
        return artist_name;
    }
    @Override
    public String toString(){
        return "Artist{ id="+artist_id+
                ",name="+artist_name;
    }
    @Override
    public int hashCode(){
        return Objects.hash(artist_id,artist_name);
    }
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || o.getClass()!=getClass())
            return false;
        Artist A=(Artist) o;
        return A.artist_id==artist_id;
    }
}
