package ba.unsa.etf.rpr.domain;

import ba.unsa.etf.rpr.dao.WritersDaoSQLimpl;

import java.util.Map;
import java.util.Objects;

public class Writers extends WritersDaoSQLimpl implements Idable {
    private int Id;
    private String first_name;
    private String last_name;

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }
    @Override
    public String toString(){
     return "Writer{ id="+Id+
     ",first name="+first_name+
     ",last name="+last_name;
    }
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || o.getClass()!=getClass()) return false;
        Writers w=(Writers) o;
        return w.Id==Id;
    }
    @Override
    public int hashCode(){
        return Objects.hash(Id,first_name,last_name);
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
