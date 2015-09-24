import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
/**
 * a class that produces the buttons for the drillwriter gui
 * @author jt herndon
 * @version 1.0
 */
public class Buttons {
    /**
     * a button that sets the mode to moving a single dot
     * @param production the production associated with this button
     * @return a button that sets the production mode to movedot
     */
    public static Button moveDot(Production production) {
        Button btn = new Button();
        btn.setText("move single dot");
        btn.setOnAction(w -> {
            production.setMode(Mode.MOVEDOT);
        });
        return btn;
    }
    /**
     * a button that sets the mode to adding a single dot
     * @param production the production associated with this button
     * @return a button that sets the production mode to adddot
     */
    public static Button addDot(Production production) {
        Button btn = new Button();
        btn.setText("add new dot");
        btn.setOnAction(w -> {
            production.setMode(Mode.ADDDOT);
        });
        return btn;
    }
    /**
     * a button that sets the mode to adding a single set
     * @param production the production associated with this button
     * @return a button that adds a set with 16 counts
     */
    public static Button addSet(Production production) {
        Button btn = new Button();
        btn.setText("add new set");
        btn.setOnAction(w -> {
            production.addSet(16);
        });
        return btn;
    }
    /**
     * A button that moves the dots to their next set
     * @param production the production associated with this button
     * @return a button that moves the dots to their next set
     */
    public static Button nextSet(Production production) {
        Button btn = new Button();
        btn.setText("Next Set");
        btn.setOnAction(w -> {
            if (production.getCounts().length > production.getSet()) {
                production.setSet(production.getSet() + 1);
            }
            //System.out.println(production.getSet());
        });
        return btn;
    }
    /**
     * A button that moves the dots to their previous set
     * @param production the production associated with this button
     * @return a button that moves the dots to their previous set
     */
    public static Button previousSet(Production production) {
        Button btn = new Button();
        btn.setText("Previous Set");
        btn.setOnAction(w -> {
            if (production.getSet() > 0) {
                production.setSet(production.getSet() - 1);
            }
            //System.out.println(production.getSet());
        });
        return btn;
    }
    /**
     * A button that animates the current set
     * @param production the production associated with this button
     * @return a button that animates the current set
     */
    public static Button play(Production production) {
        Button btn = new Button();
        btn.setText("Play Set");
        btn.setOnAction(w -> {
            production.play();
        });
        return btn;
    }
    /**
     * A button that animates the entire production
     * @param production the production associated with this button
     * @return a button that animates the entire production
     */
    public static Button playAll(Production production) {
        Button btn = new Button();
        btn.setText("Play All");
        btn.setOnAction(w -> {
            production.playAll();
        });
        return btn;
    }
}
