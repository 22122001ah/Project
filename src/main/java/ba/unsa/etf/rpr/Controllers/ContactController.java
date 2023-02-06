package ba.unsa.etf.rpr.Controllers;

import javafx.event.ActionEvent;

import javax.lang.model.type.UnionType;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ContactController {
    /**
     * method to open maps(the map of Chamber Theatre 55)
     * @param actionEvent
     */
    public void MAPS(ActionEvent actionEvent){
        URL("https://www.google.com/maps?ll=43.858687,18.421259&z=18&t=m&hl=en&gl=BA&mapclient=embed&cid=1379306031019805872");
    }
    /**
     * method to open Facebook(the facebook page of Chamber Theatre 55)
     * @param actionEvent
     */
    public void FACEBOOK(ActionEvent actionEvent){

        URL("https://www.facebook.com/KamerniTeatarOfficial/");
    }
    /**
     * method to open Instagram(the instagram page of Chamber Theatre 55)
     * @param actionEvent
     */
    public void INSTAGRAM(ActionEvent actionEvent){
        URL("https://www.instagram.com/kamerni_teatar_55/");
    }

    /**
     * opens URL link sent as parameter on devices default browser
     * @param url
     */
    public void URL(String url){
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
    }
}
