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
public class ButtonFactory2Actions<T> implements Callback<TableColumn<T, T>, TableCell<T, T>> {

    private final EventHandler<ActionEvent> buttonOne;

    private final EventHandler<ActionEvent> buttonTwo;

    /**
     *
     * @param buttonOne
     * @param buttonTwo
     */
    public ButtonFactory2Actions(EventHandler<ActionEvent> buttonOne, EventHandler<ActionEvent> buttonTwo){
        this.buttonOne = buttonOne;
        this.buttonTwo = buttonTwo;
    }

    @Override
    public TableCell<T, T> call(TableColumn<T, T> quoteObjectTableColumn) {
        return new ButtonTableCell2Actions<>(buttonOne, buttonTwo);
    }
}