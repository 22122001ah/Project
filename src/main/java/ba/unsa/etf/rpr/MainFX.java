package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    public void start(Stage stage) throws Exception {
        try{
            FXMLLoader fl=new FXMLLoader(getClass().getResource("/fxml/home-layout.fxml"));
            ControllerLogin controller = new ControllerLogin();
            fl.setController(controller);
            Parent root = null;
            root = fl.load();
            stage.setTitle("Hello, World!");
            stage.setScene(new Scene(root, 300, 275));
            stage.show();
        }

    catch(Exception E){
        System.out.println(E);}
    }
    public static void main(String[] args) {
        launch(args);
    }
}