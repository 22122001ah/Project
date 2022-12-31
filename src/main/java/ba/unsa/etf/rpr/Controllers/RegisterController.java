package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.dao.UserDaoSQLimpl;
import ba.unsa.etf.rpr.domain.Users;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RegisterController {
    public Button okBttn;
    public Label labels;
    public TextField fieldUsername,fieldFirstname,fieldLastname;
    public PasswordField fieldPass;
    public DatePicker date;
    private Users u;
    private UserDaoSQLimpl dao=new UserDaoSQLimpl();
    public void zatvoriProzorPropuhJe(ActionEvent actionEvent) {
        u=new Users();
        if (fieldUsername.getText().isEmpty()  || fieldFirstname.getText().isEmpty() || fieldLastname.getText().isEmpty() || fieldPass.getText().isEmpty()) {

            new Alert(Alert.AlertType.NONE,"Invalid registration",ButtonType.OK).show();
        }

        Users   k=new Users();
       try {
           k=k.searchByUsername(fieldUsername.getText());

        if (k!=null) {
            new Alert(Alert.AlertType.NONE,"Username already taken",ButtonType.OK).show();
        }
        u.setFirst_name(fieldFirstname.getText());
        u.setLast_name(fieldLastname.getText());
        u.setPassword(fieldPass.getText());
        u.setUsername(fieldUsername.getText());

        try {
            dao.add(u);
        } catch (Exception e) {
            System.out.println("Problem with adding a new user in the database");
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) okBttn.getScene().getWindow();
        stage.close();

    }
       catch (Exception e){
           System.out.println(e);}
    }
}
