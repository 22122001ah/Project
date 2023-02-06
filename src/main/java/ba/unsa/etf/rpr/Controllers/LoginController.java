package ba.unsa.etf.rpr.Controllers;
import ba.unsa.etf.rpr.domain.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import javafx.scene.input.KeyEvent;
import java.util.Objects;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

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
    public void ENTER(KeyEvent keyEvent){
        if(keyEvent.getCode()== KeyCode.ENTER) {
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

}
