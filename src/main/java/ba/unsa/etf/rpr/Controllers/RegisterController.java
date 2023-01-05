package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.UserDaoSQLimpl;
import ba.unsa.etf.rpr.domain.Users;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Date;

public class RegisterController {
    public Button okBttn;
    public TextField fieldUsername,fieldFirstname,fieldLastname,L;
    public PasswordField fieldPass;
    public DatePicker date;
public CheckBox F=new CheckBox();
public CheckBox M=new CheckBox();

    public Users getU() {
        return u;
    }

    public Users u;

    public void zatvoriProzorPropuhJe(ActionEvent actionEvent) {
        u=new Users();
        if (fieldUsername.getText().isEmpty()  || fieldFirstname.getText().isEmpty() || fieldLastname.getText().isEmpty() || fieldPass.getText().isEmpty()) {

            new Alert(Alert.AlertType.NONE,"Invalid registration",ButtonType.OK).show();
        }

        Users   k=new Users();
       try {
           k=k.searchByUsername(fieldUsername.getText());


            new Alert(Alert.AlertType.NONE,"Username already taken",ButtonType.OK).show();


    }
       catch (Exception e){
           if(M.isSelected() && F.isSelected())
           new Alert(Alert.AlertType.NONE,"You can only choose one",ButtonType.OK).show();
           u.setFirst_name(fieldFirstname.getText());
           u.setLast_name(fieldLastname.getText());
           u.setPassword(fieldPass.getText());
           u.setUsername(fieldUsername.getText());
           u.setDate_of_birth(Date.valueOf(date.getValue()));
          u.setLocation(L.getText());

             if(F.isSelected())
              u.setGender("F");
          else if(M.isSelected())
              u.setGender("M");

           try {
               DaoFactory.usersDao().add(u);
           } catch (Exception e1) {
               System.out.println("Problem with adding a new user in the database");
               throw new RuntimeException(e1);
           }
           Stage stage = (Stage) okBttn.getScene().getWindow();
           stage.close();}
    }
}
