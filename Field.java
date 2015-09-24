import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import java.util.ArrayList;
/**
 * This class helps generate the field
 * @author JT Herndon
 * @version 1.0
 */
public class Field {
    public static final int WIDTH = 1080;
    public static final int HEIGHT = 480;
    public static Pane canvas;
    /**
     * No-arg constructor
     */
    public Field() {
        canvas = new Pane();
        canvas.setPrefSize(WIDTH, HEIGHT);
        canvas.setStyle("-fx-background-color: green;");
        Draw();
    }
    /**
     * Draws the field
     */
    private static void Draw() {

        ArrayList<Line> lines = new ArrayList<>();
        //5-Yard Lines
        for (int y = 90; y <= WIDTH - 90; y += 45) {
            lines.add(new Line(y, 0, y, HEIGHT-1));
            lines.add(new Line(y - 3, 180, y + 3, 180));
            lines.add(new Line(y - 3, HEIGHT - 180, y + 3, HEIGHT - 180));
        }

        //Piano Teeth
        for (int y = 90; y <= WIDTH - 90; y += 9) {
            lines.add(new Line(y, 1, y, 7));
            lines.add(new Line(y, HEIGHT - 1, y, HEIGHT - 7));
            lines.add(new Line(y, 180, y, 173));
            lines.add(new Line(y, HEIGHT - 180, y, HEIGHT - 173));
        }
        for (Line line : lines) {
            line.setStrokeWidth(1);
            line.setStroke(Color.WHITE);
            canvas.getChildren().add(line);
        }
        /*
        for (int y = 1; y < 10; y++) {
            yl = y < 10 - y ? y : 10 - y;
        }
        */
    }

}
