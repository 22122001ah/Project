package ba.unsa.etf.rpr.domain;

import ba.unsa.etf.rpr.dao.plays_inDaoSQLimpl;

import java.util.Objects;

public class plays_in extends plays_inDaoSQLimpl implements Idable {
    private int playsIn_id;
    private int Artist_id;
    private int Plays_id;

    public plays_in() {

    }

    public int getPlaysIn_id() {
        return playsIn_id;
    }

    public void setPlaysIn_id(int playsIn_id) {
        this.playsIn_id = playsIn_id;
    }

    public int getArtist_id() {
        return Artist_id;
    }

    public void setArtist_id(int artist_id) {
        Artist_id = artist_id;
    }

    public int getPlays_id() {
        return Plays_id;
    }

    public void setPlays_id(int plays_id) {
        Plays_id = plays_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        plays_in plays_in = (plays_in) o;
        return playsIn_id == plays_in.playsIn_id && Artist_id == plays_in.Artist_id && Plays_id == plays_in.Plays_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playsIn_id, Artist_id, Plays_id);
    }

    @Override
    public String toString() {
        return "plays_in{" +
                "playsIn_id=" + playsIn_id +
                ", Artist_id=" + Artist_id +
                ", Plays_id=" + Plays_id +
                '}';
    }


    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int anInt) {

    }
}
