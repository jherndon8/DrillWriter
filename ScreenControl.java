import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
/**
 * This class helps for controlling what goes on on the field
 * @author JT Herndon
 * @version 1.0
 */
public class ScreenControl {
    private Pane canvas;
    private Production production;
    private int sX;
    private int sY;
    private Buttons buttons;
    //tmp variables for dragging stuff
    /**
     * Main constructor
     * @param canvas the field Pane
     * @param production the current Production
     */
    public ScreenControl(Pane canvas, Buttons buttons) {
        this.canvas = canvas;
        this.production = buttons.getProduction();
        this.buttons = buttons;
        initControls();
    }
    /**
     * Initiates the controls
     */
    private void initControls() {
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    //System.out.println(e.getX() + " " + e.getY());
                    Mode mode = production.getMode();
                    switch(mode) {
                        case ADDDOT:
                            //System.out.println("Adding dot");
                            production.addDot(e.getY(), e.getX(), canvas);
                            break;
                        case MOVEDOT:
                            production.setCurrentDot(production
                                .selectClosestDot(production.getSet(),
                                    e.getY(), e.getX()));
                            production.setMode(Mode.MOVEDOT);
                            break;
                        case SELECT:
                        default:
                            break;
                    }
                    //System.out.println(production.getMode());
                    buttons.enableAll();
                }
            }
        );
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
            new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {
                    Mode mode = production.getMode();
                    switch(mode) {
                        case MOVEDOT:
                            production.getCurrentDot().change(production
                                .getSet(), e.getY(), e.getX());
                            production.getCurrentDot().move(production.getSet());
                    }
                }
            }
        );
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
            new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {
                    Mode mode = production.getMode();
                    switch(mode) {
                        case MOVEDOT:
                            production.getCurrentDot().change(production
                                .getSet(), e.getY(), e.getX());
                            production.getCurrentDot().move(production.getSet());
                    }
                }
            }
        );
    }
}
