package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.UsersManager;
import ba.unsa.etf.rpr.domain.Users;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Objects;

public class LoginController {
    public Button loginbttn;
    public TextField fieldUsername;
    public PasswordField fieldPass;
    public Users u=new Users();
    public UsersManager usersManager=new UsersManager();

    public Users getU() {
        return u;
    }

    /**
     * Closes window if Login button is clicked
     * @param actionEvent
     */
    public void zatvoriProzorPropuhJe(ActionEvent actionEvent){
  Closing();
    }

    /**
     * Closes window if ENTER key is hit
     * @param keyEvent
     */
    public void ENTER(KeyEvent keyEvent){
        if(keyEvent.getCode()== KeyCode.ENTER) {
           Closing();
        }
    }

    /**
     * this method checks if the given username is valid(that is it checks if there is a user with that username in DB)
     * if yes, it checks if the password is correct
     * if all requirements are met, the user has successfully logged in and the window is closed
     */
    public void Closing(){
    Users k=new Users();
    u=null;
    try{
        k= usersManager.searchByUsername(fieldUsername.getText());
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
