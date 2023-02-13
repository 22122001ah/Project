package ba.unsa.etf.rpr.Controllers;


import ba.unsa.etf.rpr.business.PlaysManager;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class MainController  {
    public javafx.scene.control.Label l;
    public Button bt;
    RegisterController noviprozor2;
    LoginController noviprozor1;
   PlaysManager playsManager=new PlaysManager();
public Button login;
public Button register;



    /**
     * opens new register window
     * @param actionEvent
     * @throws IOException
     */
    public void RegisterBttn(ActionEvent actionEvent) throws IOException {
        try{
            noviprozor2=new RegisterController();
OpenStage("/fxml/Register.fxml","Register",noviprozor2);
Button l= (Button) actionEvent.getTarget();
if(noviprozor1!=null)
    l.setVisible(false);
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
            noviprozor1.setLoginbttn((Button) actionEvent.getTarget(),register,l);

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
            noviprozor.setText(playsManager.searchByPlayName(numberButton.getText()).toString());
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
           if(playsManager.searchByPlayName(numberButton.getText()).get(0).getMaxcap()-playsManager.searchByPlayName(numberButton.getText()).get(0).getSoldtickets()==0)
bt.setText("SOLD OUT");

           else {
               Stage secondstage = new Stage();
               FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/BuyTickets.fxml"));
               Parent root = fl.load();
               BuyTicketsController buyTicketsController = fl.getController();
               buyTicketsController.setPrice(playsManager.searchByPlayName(numberButton.getText()).get(0).getPrice());
               buyTicketsController.setName(numberButton.getText());
               secondstage.setTitle("Buy tickets");
               secondstage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
               secondstage.setResizable(false);
               secondstage.show();
           }}


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

    public void search(ActionEvent actionEvent) throws PlaysException {
            SearchController s=new SearchController(noviprozor1,noviprozor2);
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

    public void SaveReport( FileOutputStream out) throws IOException, PlaysException {

        Workbook workbook = new XSSFWorkbook();
        //Create a blank sheet
        Sheet sheet = workbook.createSheet("Plays report");

        String[] columnHeads={"Play name","Genre","Date","Director","Writer","Capacity","Sold","Price","Profit"};
       Font headerFont=  workbook.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.BLACK.index);
        headerFont.setBold(true);
        CellStyle headerStyle=workbook.createCellStyle();
        headerStyle.setFont( headerFont);

        Row headerRow=sheet.createRow(0);

        for(int i=0;i<columnHeads.length;i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnHeads[i]);
            cell.setCellStyle(headerStyle);
        }
        sheet.createFreezePane(0, 1);
        //Fill data
        ArrayList<Plays> a = createData();
        CreationHelper creationHelper= workbook.getCreationHelper();
        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd.mm.yyyy"));
        int rownum =1;
        for(Plays i : a) {
            Row row = sheet.createRow(rownum++);
            row.createCell(0).setCellValue(i.getPlay_name());
            row.createCell(1).setCellValue(i.getGenre());
            row.createCell(7).setCellValue(i.getPrice());
            row.createCell(3).setCellValue(i.getDirector().toString());
            row.createCell(4).setCellValue(i.getWriter().toString());
            row.createCell(5).setCellValue(i.getMaxcap());
            row.createCell(6).setCellValue(i.getSoldtickets());
            Cell dateCell = row.createCell(2);
            dateCell.setCellValue(i.getDate());
            dateCell.setCellStyle(dateStyle);
            row.createCell(8).setCellValue(i.getSoldtickets()*i.getPrice());
        }
        Row sumRow = sheet.createRow(rownum);
        Cell sumRowTitle = sumRow.createCell(0);
        sumRowTitle.setCellValue("Total");
        sumRowTitle.setCellStyle(headerStyle);

        String strFormula = "SUM(I2:I"+rownum+")";
        Cell sumcell = sumRow.createCell(8);
        sumcell.setCellFormula(strFormula);
        sumcell.setCellValue(true);
        sumcell.setCellStyle(headerStyle);


        //Autosize columns
        for(int i=0;i<columnHeads.length;i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(out);
        out.close();
    }


    private  ArrayList<Plays> createData() throws PlaysException {
        return   (ArrayList<Plays>) playsManager.getAll();
    }


    public void OpenReport(ActionEvent actionEvent) throws IOException, PlaysException {
        if(noviprozor2!=null && noviprozor2.getU().getManagement()!=1)
            new Alert(Alert.AlertType.NONE,"You are not authorised to see reports",ButtonType.OK).show();
        else if(noviprozor1!=null && noviprozor1.getU().getManagement()!=1)
            new Alert(Alert.AlertType.NONE,"You are not authorised to see reports",ButtonType.OK).show();
        else if(noviprozor2==null && noviprozor1==null)
        {
            new Alert(Alert.AlertType.NONE,"You do not have management privileges needed to perform this action.",ButtonType.OK).show();

        }
        else {

            FileOutputStream out = new FileOutputStream(new File("Report.xslx"));
            SaveReport(out);
            File file = new File("Report.xslx");
            try {

                if (!Desktop.isDesktopSupported()) {

                    System.out.println("not supported");
                    return;
                }
                Desktop desktop = Desktop.getDesktop();
                if (file.exists())
                    desktop.open(file);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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