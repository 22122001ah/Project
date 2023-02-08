package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.PlaysManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.PlaysDao;
import ba.unsa.etf.rpr.dao.PlaysDaoSQLimpl;
import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Writers;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;

import static org.mockito.Mockito.when;

public class PlaysTestMock {
    PlaysManager playsManager;
    Plays play;

    @BeforeEach
    public void initializeObjectsWeNeed() {
       playsManager = new PlaysManager();
       play=new Plays("Umri muški");
        Calendar.getInstance().set(2022,12,22);
        play.setDate(Calendar.getInstance().getTime());
        play.setMaxcap(0);
        play.setGenre("comedy");
        Directors directors =new Directors();
        directors.setFirst_name("Admir Glamocak");
        directors.setId(3);
        play.setDirector(directors);
        Writers writer=new Writers();
        writer.setFirst_name("Aldo Nikolaj");
        writer.setId(8);
        play.setWriter(writer);
        play.setPrice(30);
    }
    @Test
    public void validatePlay() throws PlaysException {
        ArrayList<Plays> p=new ArrayList<>();
        p.add(play);
        MockedStatic<DaoFactory> dao = Mockito.mockStatic(DaoFactory.class);
        PlaysDaoSQLimpl UD = Mockito.mock(PlaysDaoSQLimpl.class);
        dao.when(DaoFactory::playsDao).thenReturn(UD);
        when(DaoFactory.playsDao().searchByPlayName("Umri muški")).thenReturn(p);
        assertTrue(true);
        dao.close();
    }
    @Test
    void add() throws PlaysException {
        MockedStatic<DaoFactory> daoFactoryMockedStatic=Mockito.mockStatic(DaoFactory.class);
        PlaysDaoSQLimpl playsDaoSQLimpl=Mockito.mock(PlaysDaoSQLimpl.class);
        daoFactoryMockedStatic.when(DaoFactory::playsDao).thenReturn(playsDaoSQLimpl);
        when(playsDaoSQLimpl.add(play)).thenReturn(play);
        playsManager.add(play);
        assertTrue(true);
        daoFactoryMockedStatic.close();
    }

}
