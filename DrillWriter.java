import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class DrillWriter extends Application {
    @Override
    public void start(Stage stage) {
        Field field = new Field();
        Canvas canvas = field.canvas;
        VBox root = new VBox(canvas);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Drill Writer");
        stage.show();
    }
}
