package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.PlaysManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.*;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Adna Herak
 * JUnit tests
 */
public class TESTS {
    /**
     * Test to see if  method searchByPlayName will throw exception
     * if play name does not exist
     * @throws PlaysException
     */
    @Test
   public void searchByPlayNameTest() throws PlaysException {

        PlaysManager playsManager=new PlaysManager();
       assertThrows(PlaysException.class,()->playsManager.searchByPlayName("Neko ime"),"No such play");
    }

    /**
     * Test to see if  method searchByFullName will throw exception
     *  if a director with that name does not exist
     */
    @Test
    public void searchByDirectorsName(){

        PlaysManager playsManager=new PlaysManager();
        Directors d=new Directors();
        d.setId(4);
        d.setFirst_name("Neko Nekic");
        assertThrows(PlaysException.class,()->playsManager.searchByDirector(d),"There is no director with that name in out DB");

    }

    /**
     * Test to see if searchByWriter return right plays for given writer
     * @throws PlaysException
     * @throws ParseException
     */
    @Test
    public void writer() throws PlaysException, ParseException {

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

    /**
     * test to see if searchByArtist returns correct plays
     * @throws PlaysException
     * @throws ParseException
     */
    @Test
            public void Artists() throws PlaysException, ParseException {
                Artists artists=new Artists();
                artists.setId(10);
                artists.setArtist_name("Moamer Kasumović");
                    List<Plays> playsArrayList=new ArrayList<>();
                playsArrayList=DaoFactory.playsin_Dao().searchByArtist(artists);
    List<Plays> plays=new ArrayList<>();
    Plays play=new Plays();
                Writers writer=new Writers();
                writer.setId(6);
                writer.setFirst_name("Dubravko Mihanovic");
                play.setId(3);
                play.setWriter(writer);
                play.setPlay_name("Žaba");
                play.setGenre("drama");
                play.setMaxcap(233);
                play.setPrice(25);
                Date d=new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2022-12-22" );
                play.setDate(d);
                Directors directors=new Directors();
                directors= DaoFactory.directorsDao().getById(2);
                play.setDirector(directors);
                plays.add(play);

                assertEquals(playsArrayList,plays);
    }
    @Test
    public void PlaysByArtists() throws PlaysException, ParseException {
        Plays play=new Plays();
        Writers writer=new Writers();
        writer.setId(8);
        writer.setFirst_name("Aldo Nikolaj");
        play.setId(1);
        play.setWriter(writer);
        play.setPlay_name("Umri muški");
        play.setGenre("comedy");
        play.setMaxcap(0);
        play.setPrice(30);
        Date d=new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2022-12-22" );
        play.setDate(d);
        Directors directors=new Directors();
        directors= DaoFactory.directorsDao().getById(3);
        play.setDirector(directors);
        ArrayList<Artists> artists=new ArrayList<>();
        artists=(ArrayList<Artists>) DaoFactory.playsin_Dao().searchByPlay(play);
        ArrayList<Artists>artists1=new ArrayList<Artists>();
        artists1.add(DaoFactory.artistDao().getById(2));
        artists1.add(DaoFactory.artistDao().getById(4));
        artists1.add(DaoFactory.artistDao().getById(3));
        assertEquals(artists,artists1);
    }
}
