package ba.unsa.etf.rpr.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * Double button cell factory for creation of buttons for each cell in the table
 * @param <T>
 */
public class ButtonFactory<T> implements Callback<TableColumn<T, T>, TableCell<T, T>> {

    private final EventHandler<ActionEvent> buttonOne;

    private final EventHandler<ActionEvent> buttonTwo;
    private final EventHandler<ActionEvent> buttonThree;

    /**
     *
     * @param buttonOne - event handler for first button (Edit)
     * @param buttonTwo - event handler for second button (Delete)
     */
    public ButtonFactory(EventHandler<ActionEvent> buttonOne, EventHandler<ActionEvent> buttonTwo,EventHandler<ActionEvent> buttonThree){
        this.buttonOne = buttonOne;
        this.buttonTwo = buttonTwo;
        this.buttonThree=buttonThree;
    }

    @Override
    public TableCell<T, T> call(TableColumn<T, T> quoteObjectTableColumn) {
        return new ButtonTableCell<>(buttonOne, buttonTwo,buttonThree);
    }
}