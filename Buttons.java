import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
public class Buttons {
    public static Button select(Pane pane, Production production) {
        Button btn = new Button();
        btn.setText("Select");
        btn.setOnAction(w -> {
            production.setMode(Mode.SELECT);
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
            if (production.getCounts().length >= production.getSet()) {
                production.setSet(production.getSet() + 1);
            }
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
        });
        return btn;
    }
}
