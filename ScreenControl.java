import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    private double sX;
    private double sY;
    private Rectangle selectRect;
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
        selectRect = new Rectangle();
        selectRect.setVisible(false);
        selectRect.setFill(Color.CYAN);
        selectRect.setStroke(Color.BLUE);
        selectRect.setOpacity(0.3);
        canvas.getChildren().add(selectRect);
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
                                .selectClosestDot(e.getY(), e.getX()));
                            production.setMode(Mode.MOVEDOT);
                            break;
                        case BOXSELECT:
                            sX = e.getX();
                            sY = e.getY();
                            selectRect.setVisible(true);
                            selectRect.setWidth(0);
                            selectRect.setHeight(0);
                            break;
                        case SELECT:
                            production.getSelector().toggleSelect(production
                                .selectClosestDot(e.getY(), e.getX()));
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
                            break;
                        case BOXSELECT:
                            selectRect.setX(e.getX() < sX ? e.getX() : sX);
                            selectRect.setY(e.getY() < sY ? e.getY() : sY);
                            selectRect.setHeight(e.getY() < sY ?
                                sY - e.getY() : e.getY() - sY);
                            selectRect.setWidth(e.getX() < sX ?
                                sX - e.getX() : e.getX() - sX);
                            production.getSelector().boxSelect(sX, e.getX(), sY,
                                e.getY());
                            break;
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
                            break;
                        case BOXSELECT:
                            selectRect.setVisible(false);
                            break;
                    }
                }
            }
        );
    }
}
