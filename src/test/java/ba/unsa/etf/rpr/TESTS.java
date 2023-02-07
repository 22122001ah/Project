package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.DirectorsManager;
import ba.unsa.etf.rpr.business.PlaysManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Writers;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class TESTS {
    @Test
   public void searchByPlayNameTest() throws PlaysException {
       //Test to see if  method searchByPlayName will throw exception
       //if play name does not exist
        PlaysManager playsManager=new PlaysManager();
       assertThrows(PlaysException.class,()->playsManager.searchByPlayName("Neko ime"),"No such play");
    }
    @Test
    public void searchByDirectorsName(){
        //Test to see if  method searchByFullName will throw exception
        //if a director with that name does not exist
        PlaysManager playsManager=new PlaysManager();
        Directors d=new Directors();
        d.setId(4);
        d.setFirst_name("Neko Nekic");
        assertThrows(PlaysException.class,()->playsManager.searchByDirector(d),"There is no director with that name in out DB");

    }
    @Test
    public void writer() throws PlaysException, ParseException {
        //Test to see if searchByWriter return right plays for given writer
        Writers writer=new Writers();
        writer.setId(1);
        writer.setFirst_name("Abdulah Sidran");
        ArrayList<Plays> plays = new ArrayList<>();
        Plays play=new Plays();
        play.setId(2);
        play.setWriter(writer);
        play.setPlay_name("Sjecaš li se Doli Bel");
        play.setGenre("drama");
        play.setMaxcap(240);
        play.setPrice(30);

        Date d=new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2022-10-24" );
        play.setDate(d);
        Directors directors=new Directors();
        directors= DaoFactory.directorsDao().getById(4);
        play.setDirector(directors);
        plays.add(play);
        assertTrue(plays.equals(DaoFactory.playsDao().searchByWriter(writer)),"Problem with searchByWriter method");
            }
}
