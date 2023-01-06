package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.MainFX;
import ba.unsa.etf.rpr.dao.UserDaoSQLimpl;
import ba.unsa.etf.rpr.domain.Users;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Objects;

public class LoginController {
    public Button loginbttn;
    public TextField fieldUsername;
    public PasswordField fieldPass;
    public Users u=new Users();

    public Users getU() {
        return u;
    }

    public void zatvoriProzorPropuhJe(ActionEvent actionEvent){
        Users k=new Users();
        u=null;
        try{
            k=k.searchByUsername(fieldUsername.getText());
            if (k!=null) {
                if(!Objects.equals(k.getPassword(), fieldPass.getText()))
                {
                    new Alert(Alert.AlertType.NONE,"incorrect password", ButtonType.OK).show();
                }
                u=k;
                Stage s=(Stage) loginbttn.getScene().getWindow();
                s.close();}
        }
        catch (Exception e){
            {

                new Alert(Alert.AlertType.NONE,"invalid username", ButtonType.OK).show();
            }
        }
    }
}
