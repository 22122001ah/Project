package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.PlaysDao;
import ba.unsa.etf.rpr.dao.PlaysDaoSQLimpl;
import ba.unsa.etf.rpr.domain.Plays;

/**
 * Hello world!
 *
 */
public class App
{
    private static void testiranje(){
      try{  Plays play=new Plays();
        System.out.println(play.searchByPrice(10));
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
