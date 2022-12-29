package ba.unsa.etf.rpr.domain;
import ba.unsa.etf.rpr.dao.ArtistsDaoSQLimpl;

import java.util.Objects;


/**
 * List of possible actors for plays
 * @author Adna Herak
 */
public class Artists extends ArtistsDaoSQLimpl implements Idable {
    private int Id;
    private String artist_name;



    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }



    public String getArtist_name() {
        return artist_name;
    }
    @Override
    public String toString(){
        return "Artist{ id="+Id+
                ",name="+artist_name;
    }
    @Override
    public int hashCode(){
        return Objects.hash(Id,artist_name);
    }
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || o.getClass()!=getClass())
            return false;
        Artists A=(Artists) o;
        return A.Id==Id;
    }

    @Override
    public int getId() {
        return Id;
    }

    @Override
    public void setId(int anInt) {
this.Id=anInt;
    }
}
