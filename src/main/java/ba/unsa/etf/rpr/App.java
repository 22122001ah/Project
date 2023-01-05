package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.DirectorsManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.PlaysDao;
import ba.unsa.etf.rpr.dao.PlaysDaoSQLimpl;
import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.domain.Plays;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
    private static void testiranje(){
      try{
          System.out.println(DaoFactory.directorsDao().searchByDirectorName("Admir Glamocak"));
      }
      catch (Exception e)
      {
          System.out.println(e);
      }
    }
    public static void main( String[] args )
    {
        testiranje();
    }
}
