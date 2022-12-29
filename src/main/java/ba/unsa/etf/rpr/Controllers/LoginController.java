package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.dao.UserDaoSQLimpl;
import ba.unsa.etf.rpr.domain.Users;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

public class LoginController {
   public Button loginbttn;
public TextField fieldUsername;
public PasswordField fieldPass;
Users u=new Users();
    public void zatvoriProzorPropuhJe(ActionEvent actionEvent){
        Users k=new Users();
        try{
        k=k.searchByUsername(fieldUsername.getText());
        if (k!=null) {
            if(!Objects.equals(k.getPassword(), fieldPass.getText()))
return;
            Stage s=(Stage) loginbttn.getScene().getWindow();
            s.close();}
        else return;

    }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
