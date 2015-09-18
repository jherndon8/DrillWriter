import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
public class Buttons {
    public static Button moveDot(Pane pane, Production production) {
        Button btn = new Button();
        btn.setText("Move Single Dot");
        btn.setOnAction(w -> {
            production.setMode(Mode.MOVEDOT);
        });
        return btn;
    }
    public static Button addDot(Pane pane, Production production) {
        Button btn = new Button();
        btn.setText("Add new Dot");
        btn.setOnAction(w -> {
            production.setMode(Mode.ADDDOT);
        });
        return btn;
    }
    public static Button addSet(Pane pane, Production production) {
        Button btn = new Button();
        btn.setText("Add new Set");
        btn.setOnAction(w -> {
            production.addSet(16);
        });
        return btn;
    }
    public static Button nextSet(Pane pane, Production production) {
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
    public static Button previousSet(Pane pane, Production production) {
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
    public static Button play(Pane pane, Production production) {
        Button btn = new Button();
        btn.setText("Play Set");
        btn.setOnAction(w -> {
            production.play();
        });
        return btn;
    }
    public static Button playAll(Pane pane, Production production) {
        Button btn = new Button();
        btn.setText("Play All");
        btn.setOnAction(w -> {
            production.playAll();
        });
        return btn;
    }
}
