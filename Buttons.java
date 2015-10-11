import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
/**
 * a class that produces the buttons for the drillwriter gui
 * @author jt herndon
 * @version 1.0
 */
public class Buttons {

    private Production production;
    private Button moveDot;
    private Button addDot;
    private Button addSet;
    private Button nextSet;
    private Button previousSet;
    private Button play;
    private Button playAll;

    public Buttons(Production production) {
        this.production = production;
        moveDot = makeMoveDot();
        addDot = makeAddDot();
        addSet = makeAddSet();
        nextSet = makeNextSet();
        previousSet = makePreviousSet();
        play = makePlay();
        playAll = makePlayAll();
        if (production.getSet() == 0) {
            previousSet.setDisable(true);
        }
        if (production.getSet() == production.getCounts().length) {
            nextSet.setDisable(true);
        }
        if (production.bandSize() == 0) {
            addSet.setDisable(true);
        }
    }

    public Production getProduction() {
        return production;
    }

    /**
     * a button that sets the mode to moving a single dot
     * @param production the production associated with this button
     * @return a button that sets the production mode to movedot
     */
    private Button makeMoveDot() {
        Button btn = new Button();
        btn.setText("move single dot");
        btn.setOnAction(w -> {
            production.setMode(Mode.MOVEDOT);
        });
        return btn;
    }

    public Button moveDot() {
        return moveDot;
    }

    /**
     * a button that sets the mode to adding a single dot
     * @param production the production associated with this button
     * @return a button that sets the production mode to adddot
     */
    private Button makeAddDot() {
        Button btn = new Button();
        btn.setText("add new dot");
        btn.setOnAction(w -> {
            production.setMode(Mode.ADDDOT);
        });
        return btn;
    }

    public Button addDot() {
        return addDot;
    }

    /**
     * a button that sets the mode to adding a single set
     * @param production the production associated with this button
     * @return a button that adds a set with 16 counts
     */
    private Button makeAddSet() {
        Button btn = new Button();
        btn.setText("add new set");
        btn.setOnAction(w -> {
            production.addSet(16);
            production.setMode(Mode.MOVEDOT);
            nextSet.setDisable(true);
            previousSet.setDisable(false);
            addDot.setDisable(true);
        });
        return btn;
    }

    public Button addSet() {
        return addSet;
    }

    /**
     * A button that moves the dots to their next set
     * @param production the production associated with this button
     * @return a button that moves the dots to their next set
     */
    private Button makeNextSet() {
        Button btn = new Button();
        btn.setText("Next Set");
        btn.setOnAction(w -> {
            if (production.getCounts().length > production.getSet()) {
                production.setSet(production.getSet() + 1);
                previousSet.setDisable(false);
                addDot.setDisable(true);
            }
            if (production.getCounts().length == production.getSet()) {
                nextSet.setDisable(true);
            }
            //System.out.println(production.getSet());
        });
        return btn;
    }

    public Button nextSet() {
        return nextSet;
    }

    /**
     * A button that moves the dots to their previous set
     * @param production the production associated with this button
     * @return a button that moves the dots to their previous set
     */
    private Button makePreviousSet() {
        Button btn = new Button();
        btn.setText("Previous Set");
        btn.setOnAction(w -> {
            if (production.getSet() > 0) {
                production.setSet(production.getSet() - 1);
                if (production.getSet() == 0) {
                    addDot.setDisable(false);
                    previousSet.setDisable(true);
                }
                nextSet.setDisable(false);
            }
            //System.out.println(production.getSet());
        });
        return btn;
    }

    public Button previousSet() {
        return previousSet;
    }
    /**
     * A button that animates the current set
     * @param production the production associated with this button
     * @return a button that animates the current set
     */
    private Button makePlay() {
        Button btn = new Button();
        btn.setText("Play Set");
        btn.setOnAction(w -> {
            production.play();
        });
        return btn;
    }

    public Button play() {
        return play;
    }

    /**
     * A button that animates the entire production
     * @param production the production associated with this button
     * @return a button that animates the entire production
     */
    private Button makePlayAll() {
        Button btn = new Button();
        btn.setText("Play All");
        btn.setOnAction(w -> {
            production.playAll();
        });
        return btn;
    }

    public Button playAll() {
        return playAll;
    }
}
