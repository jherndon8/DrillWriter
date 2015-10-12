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
/**
 * Main class for handling animations
 * @author JT Herndon
 * @version 1.0
 */
public class Animate {
    /**
     * A helper method that adds animations to an array of SequentialTransitions
     * @param startSet the starting set
     * @param endSet the ending set
     * @param dotList a java.util.List of Dots which will be animated
     * @param count an int array of the count structure
     * @param seqTrans an array of SequentialTransitions for each dot
     */
    private static void play(int startSet, int endSet,  List<Dot> dotList,
        int[] count, SequentialTransition[] seqTrans) {
        if (startSet >= endSet || startSet < 0) {
            return;
        }
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
    /**
     * Animates dots in dotList from startSet to endSet, based on count
     * structure in count
     * @param startSet the starting set
     * @param endSet the ending set
     * @param dotList a java.util.List of Dots
     * @count an int array of the count structure
     */
    public static void play(int startSet, int endSet,  List<Dot> dotList,
        int[] count, Buttons buttons) {
        SequentialTransition[] a = new SequentialTransition[dotList.size()];
        for (int b = 0; b < dotList.size(); b++) {
            a[b] = new SequentialTransition();
        }
        buttons.disableAll();
        Mode prevMode;
        if (buttons.getProduction().getMode().equals(Mode.ADDDOT)) {
            prevMode = Mode.MOVEDOT;
        } else {
            prevMode = buttons.getProduction().getMode();
        }
        buttons.getProduction().setMode(Mode.PLAY);
        play(startSet, endSet, dotList, count, a);
        a[0].setOnFinished(e -> {
            buttons.getProduction().setMode(prevMode);
            buttons.enableAll();
        });
        for (SequentialTransition c : a) {
            c.play();
        }
    }
    /**
     * A method that plays one set animation, starting with the previous set and
     * ending on the set passed in as the argument
     * @param set the set to be played
     * @param dotList a java.util.List of Dots
     * @param count an int of how many counts in the move
     */
    public static void play(int set, List<Dot> dotList, int count,
        Buttons buttons) {
        int[] c = new int[set];
        c[set - 1] = count;
        play(set - 1, set, dotList, c, buttons);
    }
}
