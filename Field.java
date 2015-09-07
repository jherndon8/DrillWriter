import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
public class Field {
    public static final int WIDTH = 1080;
    public static final int HEIGHT = 480;
    public static Canvas canvas;
    public static GraphicsContext gc;
    public Field() {
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        Draw();
    }
    private static void Draw() {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.WHITE);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        gc.setLineWidth(1);

        //5-Yard Lines
        for (int y = 90; y <= WIDTH - 90; y += 45) {
            gc.strokeLine(y, 0, y, HEIGHT-1);
            gc.strokeLine(y - 3, 180, y + 3, 180);
            gc.strokeLine(y - 3, HEIGHT - 180, y + 3, HEIGHT - 180);
        }

        //Piano Teeth
        for (int y = 90; y <= WIDTH - 90; y += 9) {
            gc.strokeLine(y, 1, y, 7);
            gc.strokeLine(y, HEIGHT - 1, y, HEIGHT - 7);
            gc.strokeLine(y, 180, y, 173);
            gc.strokeLine(y, HEIGHT - 180, y, HEIGHT - 173);
        }
        for (int y = 1; y < 10; y++) {
            yl = y < 10 - y ? y : 10 - y;
        }

    }

}
