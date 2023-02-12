package ba.unsa.etf.rpr.Controllers;

import javafx.event.ActionEvent;
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
public class ButtonTableCell2Actions<T> extends TableCell<T, T> {

    private Button buy;
    private Button view;

    /**
     * Default constructor
     * @param buttonOne
     * @param buttonTwo
     */
    public ButtonTableCell2Actions(EventHandler<ActionEvent> buttonOne, EventHandler<ActionEvent> buttonTwo){
        buy = new Button("Buy");
        buy.setOnAction(buttonOne);
        view=new Button("Description");
        view.setOnAction(buttonTwo);
    }

    @Override
    protected void updateItem(T o, boolean b) {
        super.updateItem(o, b);
        if (o != null) {
            HBox box = new HBox();
            box.setAlignment(Pos.CENTER);
            buy.setUserData(o);
            view.setUserData(o);
            box.getChildren().add(buy);
            box.getChildren().add(view);
            view.setBackground(Background.fill(Color.BEIGE));
            buy.setBackground(Background.fill(Color.BEIGE));
            view.setBorder(Border.stroke(Color.WHITE));
            buy.setBorder(Border.stroke(Color.WHITE));
            setGraphic(box);

        }
    }
}