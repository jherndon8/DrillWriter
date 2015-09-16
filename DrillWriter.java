import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class DrillWriter extends Application {
    private Production production;
    @Override
    public void start(Stage stage) {
        Production production = new Production();
        int set = 0;
        Field field = new Field();
        Pane canvas = field.canvas;
        ScreenControl control = new ScreenControl(canvas, production);
        HBox buttons = new HBox(Buttons.select(canvas, production),
            Buttons.addDot(canvas, production),
            Buttons.addSet(canvas, production));
        VBox root = new VBox(canvas, buttons);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Drill Writer");
        stage.show();
    }
    public Production getProduction() {
        return production;
    }

}
