package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class MainController  {
    RegisterController noviprozor2;
    LoginController noviprozor1;

    /**
     * opens new register window
     * @param actionEvent
     * @throws IOException
     */
    public void RegisterBttn(ActionEvent actionEvent) throws IOException {
        try{
            noviprozor2=new RegisterController();
OpenStage("/fxml/Register.fxml","Register",noviprozor2);
           }
        catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * opens new login window
     * @param actionEvent
     * @throws IOException
     */
    public void LoginBttn(ActionEvent actionEvent) throws IOException {
        try{
            noviprozor1=new LoginController();
            OpenStage("/fxml/Login.fxml","Login",noviprozor1);
           }
        catch(Exception e){
            System.out.println(e);
        }
    }
    /**
     * opening new window with  a list of plays
     * @param actionEvent
     * @throws IOException
     */
    public void showPlays(ActionEvent actionEvent) throws IOException{
        try{
            ShowPlaysController noviprozor=new ShowPlaysController();
            noviprozor.setM1(noviprozor2);
            noviprozor.setM2(noviprozor1);
            OpenStage("/fxml/showPlays.fxml","Plays",noviprozor);
            }
        catch(Exception e){
            System.out.println(e);
        }
    }
    /**
     * opening new window with the description of the selected play
     * @param actionEvent
     * @throws IOException
     */
    public void PlayDesription(ActionEvent actionEvent) throws IOException {
        try{
            Button numberButton = (Button) actionEvent.getTarget();
            Stage Secondstage=new Stage();
            FXMLLoader fl=new FXMLLoader(getClass().getResource("/fxml/Info.fxml"));
            Parent root =fl.load();
            InfoController noviprozor=fl.getController();
            noviprozor.setText(DaoFactory.playsDao().searchByPlayName(numberButton.getText()).toString());
            Secondstage.setTitle("Play description");
            Secondstage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
            Secondstage.setResizable(false);
            Secondstage.show();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * opens new window with contact information about Chamber Theatre 55
     * @param actionEvent
     * @throws IOException
     */
    public void Contact(ActionEvent actionEvent) throws IOException {
        try{
            ContactController np=new ContactController();
            OpenStage("/fxml/contact.fxml","Contact",np);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    /**
     * opening new window to buy tickets
     * firstly it checks if the person has logged in or is registered
     * if not it does not allow to buy tickets
     * @param actionEvent
     * @throws IOException
     * @throws PlaysException
     */
    public void buy(ActionEvent actionEvent) throws IOException, PlaysException {
       if(noviprozor2==null && noviprozor1==null)
       {
           new Alert(Alert.AlertType.NONE,"You need to have an account and be logged in in order to buy tickets.",ButtonType.OK).show();

       }
       else if(noviprozor1.getU()==null && noviprozor2.getU()==null)
       {
           new Alert(Alert.AlertType.NONE,"You need to have an account and be logged in in order to buy tickets.",ButtonType.OK).show();
       }

        else{
           Button numberButton = (Button) actionEvent.getTarget();
           Stage secondstage=new Stage();
           FXMLLoader fl=new FXMLLoader(getClass().getResource("/fxml/BuyTickets.fxml"));
           Parent root=fl.load();
           BuyTicketsController buyTicketsController=fl.getController();
           buyTicketsController.setPrice(DaoFactory.playsDao().searchByPlayName(numberButton.getText()).get(0).getPrice());
           buyTicketsController.setName(numberButton.getText());
           secondstage.setTitle("Buy tickets");
           secondstage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
           secondstage.setResizable(false);
           secondstage.show();}


    }
    /**
     * Method to add plays
     * firstly it checks for privileges
     * if the person is not part of the management team it does not allow to add plays
     * otherwise it opens a new window to add or edit plays
     * @param play_id
     */
    public void editPlay(Integer play_id){
        try{
if((noviprozor1!=null && noviprozor1.getU().getManagement()!=1 ))
{
    new Alert(Alert.AlertType.NONE,"You do not have management privileges needed to perform this action.",ButtonType.OK).show();

}
else if(noviprozor2!=null && noviprozor2.getU().getManagement()!=1)
{
    new Alert(Alert.AlertType.NONE,"You do not have management privileges needed to perform this action.",ButtonType.OK).show();

}else if(noviprozor2==null && noviprozor1==null)
{
    new Alert(Alert.AlertType.NONE,"You do not have management privileges needed to perform this action.",ButtonType.OK).show();

}
else{
    EditPlaysController e=new EditPlaysController();
    OpenStage("/fxml/EditPlays.fxml","Edit Play",e);

        }}catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }

    }

    public void search(ActionEvent actionEvent){
            SearchController s=new SearchController();
            try {
                OpenStage("/fxml/searchPlays.fxml","SearchPlay",s);
            }
            catch (IOException e){
                new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
            }

    }
    /**
     * Method to add plays
     * firstly it checks for privileges
     * if the person is not part of the management team it does not allow to add plays
     * otherwise it opens a new window to add plays
     * @param event
     */
    public void addPlay(ActionEvent event){
        if(noviprozor2!=null && noviprozor2.getU().getManagement()!=1)
            new Alert(Alert.AlertType.NONE,"You are not authorised to add plays",ButtonType.OK).show();
        else if(noviprozor1!=null && noviprozor1.getU().getManagement()!=1)
            new Alert(Alert.AlertType.NONE,"You are not authorised to add plays",ButtonType.OK).show();
        else
            editPlay(null);
    }

    /**
     * generic method that opends a new window
     * @param File
     * @param name
     * @param Controller
     * @throws IOException
     */
    public void OpenStage(String File,String name,Object Controller) throws IOException{

            FXMLLoader loader = new FXMLLoader(getClass().getResource(File));
            loader.setController(Controller);
            Stage Secondstage=new Stage();
            Secondstage.setTitle(name);
            Secondstage.setScene(new Scene(loader.load(),USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
            Secondstage.setResizable(false);
            Secondstage.show();


    }

}