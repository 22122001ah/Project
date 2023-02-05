package ba.unsa.etf.rpr.Controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


/**
 * Custom component for rendering table cell with two buttons (Edit and Delete)
 *
 * @param <T> - Bean class represented in the table cells
 */
public class ButtonTableCell<T> extends TableCell<T, T> {

    private Button edit;
    private Button delete;
    private Button view;

    /**
     * Default constructor
     * @param buttonOne - event handler for action on first button (Edit)
     * @param buttonTwo - event handler for action on second button (Delete)
     */
    public ButtonTableCell(EventHandler<ActionEvent> buttonOne, EventHandler<ActionEvent> buttonTwo, EventHandler<ActionEvent> buttonThree){
        edit = new Button("Edit");
        edit.setOnAction(buttonOne);
        delete = new Button("Delete");
        delete.setOnAction(buttonTwo);
        view=new Button("View");
        view.setOnAction(buttonThree);
    }

    @Override
    protected void updateItem(T o, boolean b) {
        super.updateItem(o, b);
        if (o != null) {
            HBox box = new HBox();
            box.setAlignment(Pos.CENTER);
            edit.setUserData(o);
            delete.setUserData(o);
            view.setUserData(o);
            box.getChildren().add(edit);
            box.getChildren().add(delete);
            box.getChildren().add(view);
            view.setBackground(Background.fill(Color.BEIGE));
            delete.setBackground(Background.fill(Color.BEIGE));
            edit.setBackground(Background.fill(Color.BEIGE));
            view.setBorder(Border.stroke(Color.WHITE));
            delete.setBorder(Border.stroke(Color.WHITE));
            edit.setBorder(Border.stroke(Color.WHITE));
            setGraphic(box);

        }
    }
}