package ba.unsa.etf.rpr.Controllers;

import javafx.event.ActionEvent;

import javax.lang.model.type.UnionType;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ContactController {
    public void MAPS(ActionEvent actionEvent){
        URL("https://www.google.com/maps?ll=43.858687,18.421259&z=18&t=m&hl=en&gl=BA&mapclient=embed&cid=1379306031019805872");
    }
    public void FACEBOOK(ActionEvent actionEvent){
        URL("https://www.facebook.com/KamerniTeatarOfficial/");
    }
    public void INSTAGRAM(ActionEvent actionEvent){
        URL("https://www.instagram.com/kamerni_teatar_55/");
    }

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
