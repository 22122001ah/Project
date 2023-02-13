package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.UsersManager;
import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Objects;

public class LoginController {
    public Button loginbttn;
    public Label l;
    public Button loginbttn1;
    public TextField fieldUsername;
    public PasswordField fieldPass;
    public Users u=new Users();
    public Button reg;
    public UsersManager usersManager=new UsersManager();

    public void setLoginbttn(Button loginbttn1,Button reg1,Label l) {
        this.loginbttn1 = loginbttn1;
        this.reg=reg1;
        this.l=l;
    }

    public Users getU() {
        return u;
    }

    /**
     * Closes window if Login button is clicked
     * @param actionEvent
     */
    public void zatvoriProzorPropuhJe(ActionEvent actionEvent) throws PlaysException {

  Closing();
  loginbttn1.setVisible(false);
  reg.setVisible(false);
  l.setText(usersManager.searchByUsername(fieldUsername.getText()).getFirst_name());
    }

    /**
     * Closes window if ENTER key is hit
     * @param keyEvent
     */
    public void ENTER(KeyEvent keyEvent) throws PlaysException {
        if(keyEvent.getCode()== KeyCode.ENTER) {
           Closing();
           loginbttn1.setVisible(false);
           reg.setVisible(false);

            l.setText("Cao,"+usersManager.searchByUsername(fieldUsername.getText()).getFirst_name());
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
