package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.DaoFactory;

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
