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
    private Button boxSelect;
    private Button toggleSelect;

    public Buttons(Production production) {
        this.production = production;
        moveDot = makeMoveDot();
        addDot = makeAddDot();
        addSet = makeAddSet();
        nextSet = makeNextSet();
        previousSet = makePreviousSet();
        play = makePlay();
        playAll = makePlayAll();
        boxSelect = makeBoxSelect();
        toggleSelect = makeToggleSelect();;
        enableAll();
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
            production.getSelector().clear();
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
            production.getSelector().clear();

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
            enableAll();
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
                enableAll();
                if (production.getMode() == Mode.ADDDOT) {
                    production.setMode(Mode.MOVEDOT);
                }
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
                enableAll();
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
            production.play(this);
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
            production.playAll(this);
        });
        return btn;
    }

    public Button playAll() {
        return playAll;
    }

    private Button makeBoxSelect() {
        Button btn = new Button();
        btn.setText("Box Select");
        btn.setOnAction(w -> {
            production.setMode(Mode.BOXSELECT);
        });
        return btn;
    }

    public Button boxSelect() {
        return boxSelect;
    }

    private Button makeToggleSelect() {
        Button btn = new Button();
        btn.setText("Select/Deselect individual dot");
        btn.setOnAction(w -> {
            production.setMode(Mode.SELECT);
        });
        return btn;
    }

    public Button toggleSelect() {
        return toggleSelect;
    }
    
    /** 
     * Disables all buttons
     */
    public void disableAll() {
        playAll.setDisable(true);
        play.setDisable(true);
        addDot.setDisable(true);
        moveDot.setDisable(true);
        nextSet.setDisable(true);
        previousSet.setDisable(true);
        addSet.setDisable(true);
        toggleSelect.setDisable(true);
        boxSelect.setDisable(true);
    }

    /**
     * Enables all buttons which are valid in the production's current state,
     * and disables all others
     */
    public void enableAll() {
        disableAll();
        if (production.getMode().equals(Mode.PLAY)) {
            return;
        }
        if (production.bandSize() > 0 ) {
            addSet.setDisable(false);
            moveDot.setDisable(false);
            toggleSelect.setDisable(false);
            boxSelect.setDisable(false);
        }
        if (production.getCounts().length > 0) {
            playAll.setDisable(false);
        }
        if (production.getSet() > 0) {
            play.setDisable(false);
            previousSet.setDisable(false);
        } else {
            addDot.setDisable(false);
        }
        if (production.getSet() < production.getCounts().length) {
            nextSet.setDisable(false);
        }
    }
}
