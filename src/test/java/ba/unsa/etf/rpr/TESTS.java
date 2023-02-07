package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.PlaysManager;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TESTS {
    @Test
   public void searchByPlayNameTest() throws PlaysException {
       //Test to see if  method searchByPlayName will throw exception
       //if play name does not exist
        PlaysManager playsManager=new PlaysManager();
       assertThrows(PlaysException.class,()->playsManager.searchByPlayName("Neko ime"),"No such play");
    }
}
