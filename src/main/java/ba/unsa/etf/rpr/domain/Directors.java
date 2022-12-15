package ba.unsa.etf.rpr.domain;

import ba.unsa.etf.rpr.dao.DirectorsDaoSQLimpl;

import java.util.Objects;

public class Directors extends DirectorsDaoSQLimpl {
    private int director_id;
    private String first_name;
    private String last_name;

    public void setDirector_id(int director_id) {
        this.director_id = director_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getDirector_id() {
        return director_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }
    @Override
    public String toString(){
        return "Director{ id="+director_id+
                ",first name="+first_name+
                ",last name="+last_name;
    }
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || o.getClass()!=getClass()) return false;
        Directors w=(Directors) o;
        return w.director_id==director_id;
    }
    @Override
    public int hashCode(){
        return Objects.hash(director_id,first_name,last_name);
    }
}
