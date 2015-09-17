import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
public class ScreenControl {
    private Pane canvas;
    private Production production;
    private int sX;
    private int sY;
    //tmp variables for dragging stuff
    public ScreenControl(Pane canvas, Production production) {
        this.canvas = canvas;
        this.production = production;
        initControls();
    }

    private void initControls() {
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    //System.out.println(e.getX() + " " + e.getY());
                    Mode mode = production.getMode();
                    switch(mode) {
                        case ADDDOT:
                            System.out.println("Adding dot");
                            production.addDot(e.getY(), e.getX(), canvas);
                            break;
                        case SELECT:
                            production.setCurrentDot(production
                                .selectClosestDot(production.getSet(),
                                    e.getY(), e.getX()));
                            production.setMode(Mode.MOVEDOT);
                            break;
                        case MOVEDOT:
                        default:
                            break;
                    }
                    System.out.println(production.getMode());
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
                                .getCounts().length, e.getY(), e.getX());
                            production.getCurrentDot().move(production.getSet());
                    }
                }
            }
        );
    }
}
