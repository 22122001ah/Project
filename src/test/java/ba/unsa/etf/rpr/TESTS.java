package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.PlaysManager;
import ba.unsa.etf.rpr.domain.Directors;
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
}
