import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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

        //Yard numbers
        for (int w = 10; w < 100; w += 10) {
            int z = 100 - w < w ? 100 - w: w;
            int y = HEIGHT - 81 + 15;
            int x = 90 + w * 9;
            String a = "" + z;
            a = a.substring(0, 1) + " " + a.substring(1);

            //Front-field
            Text text = new Text(x - 13, y, a);
            text.setFont(new Font(20));
            text.setStroke(Color.WHITE);
            /*if (z == 50) {
                text.setStroke(Color.YELLOW);
            }*/
            text.setFill(Color.WHITE);
            canvas.getChildren().add(text);

            //Back-field
            text = new Text(x - 13, 81, a);
            text.setFont(new Font(20));
            text.setStroke(Color.WHITE);
            text.setFill(Color.WHITE);
            text.setRotate(180);
            canvas.getChildren().add(text);
        }
    }

}
