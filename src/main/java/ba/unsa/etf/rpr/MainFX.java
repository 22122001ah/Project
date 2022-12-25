package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    public void start(Stage stage) throws Exception {
        try{
            FXMLLoader fl=new FXMLLoader(getClass().getResource("/fxml/home-layout.fxml"));

            MainController controller = new MainController();

            fl.setController(controller);
            Parent root = null;
            root = fl.load();
            stage.setTitle("BuyTickets.com");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }

    catch(Exception E){
        System.out.println(E);}
    }

    public static void main(String[] args) {
        launch(args);
    }
}