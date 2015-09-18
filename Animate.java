import java.util.List;
import java.util.ArrayList;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.util.Duration;

public class Animate {
    public static void play(int set,  List<Dot> dotList, int count) {
        ArrayList<PathTransition> paths = new ArrayList<>();
        int i = 0;
        for (Dot dot : dotList) {
            Path path = new Path();
            path.getElements().addAll(
                new MoveTo(dot.getSideToSide(set - 1),
                    dot.getFrontToBack(set - 1)),
                new LineTo(dot.getSideToSide(set), dot.getFrontToBack(set)));
            PathTransition p = new PathTransition();
            p.setPath(path);
            p.setNode(dot.getCircle());
            p.setInterpolator(Interpolator.LINEAR);
            p.setDuration(Duration.millis(500 * count));
            //System.out.println(dot.movesOnSet(set));
            if (dot.movesOnSet(set)) {
                paths.add(p);
            }
        }
        for (PathTransition p: paths) {
            p.play();
        }
        
    }
}
