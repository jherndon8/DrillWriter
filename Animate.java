import java.util.List;
import java.util.ArrayList;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.util.Duration;

public class Animate {
    public static void play(int startSet, int endSet,  List<Dot> dotList,
        int[] count, SequentialTransition[] seqTrans) {
        if (startSet >= endSet || startSet < 0) return;
        ArrayList<Transition> paths = new ArrayList<>();
        int i = 0;
        for (Dot dot : dotList) {
            Path path = new Path();
            path.getElements().addAll(
                new MoveTo(dot.getSideToSide(startSet),
                    dot.getFrontToBack(startSet)),
                new LineTo(dot.getSideToSide(startSet + 1),
                    dot.getFrontToBack(startSet + 1)));
            PathTransition p = new PathTransition();
            p.setPath(path);
            p.setNode(dot.getCircle());
            p.setInterpolator(Interpolator.LINEAR);
            p.setDuration(Duration.millis(500 * count[startSet]));
            //System.out.println(dot.movesOnSet(startSet+1));
            if (dot.movesOnSet(startSet + 1)) {
                paths.add(p);
            } else {
                paths.add(new PauseTransition(Duration.millis(
                    500 * count[startSet])));
            }
        }
        i = 0;
        for (Transition p: paths) {
            seqTrans[i++].getChildren().add(p);
            //System.out.println("Added");
        }
        play(startSet + 1, endSet, dotList, count, seqTrans);
    }
    public static void play(int startSet, int endSet,  List<Dot> dotList,
        int[] count) {
        SequentialTransition[] a = new SequentialTransition[dotList.size()];
        for (int b = 0; b < dotList.size(); b++) {
            a[b] = new SequentialTransition();
        }
        play(startSet, endSet, dotList, count, a);
        for (SequentialTransition c : a) {
            c.play();
        }
    }
    public static void play(int set, List<Dot> dotList, int count) {
        int[] c = new int[set];
        c[set - 1] = count;
        play(set - 1, set, dotList, c);
    }
}
