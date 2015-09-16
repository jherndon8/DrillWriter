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
            production.setMode(Mode.ADDSET);
        });
        return btn;
    }
}
