import java.util.ArrayList;
import javafx.scene.paint.Color;
/**
 * This class is to help select, unselect, and manipulate multiple dots
 * @author JT Herndon
 * @version 1.0
 */
public class SelectDots {
    private ArrayList<Dot> selected;
    private Production production;
    private ArrayList<Dot> dotList;
    private Dot prevSpots;
    public SelectDots(Production production, ArrayList<Dot> dotList) {
        selected = new ArrayList<>();
        this.production = production;
        this.dotList = dotList;
    }

    public boolean select(Dot dot) {
        if (!selected.contains(dot)) {
            selected.add(dot);
            dot.getCircle().setFill(Color.CYAN);
            return true;
        }
        return false;
    }

    public void initMove() {
        prevSpots = new Dot();
        int set = production.getSet();
        for (int i = 0; i < selected.size(); i++) {
            Dot dot = selected.get(i);
            prevSpots.addSet(dot.getFrontToBack(set), dot.getSideToSide(set));
        }
    }

    public void move(double startX, double startY, double newX, double newY) {
        int set = production.getSet();
        for (int i = 0; i < selected.size(); i++) {
            Dot dot = selected.get(i);
            double oldX = prevSpots.getSideToSide(i);
            double oldY = prevSpots.getFrontToBack(i);
            dot.change(set, oldY + newY - startY , oldX + newX - startX);
            dot.move(set);
        }
    }

    public boolean deselect(Dot dot) {
        dot.getCircle().setFill(Color.BLUE);
        return selected.remove(dot);
    }

    public void toggleSelect(Dot dot) {
        if (!deselect(dot)) {
            select(dot);
        }
    }

    public void clear() {
        for (Dot dot : selected) {
            dot.getCircle().setFill(Color.BLUE);
        }
        selected.clear();
    }

    public void boxSelect(double x1, double x2, double y1, double y2) {
        double minX = x1 < x2 ? x1 : x2;
        double maxX = x1 > x2 ? x1 : x2;
        double minY = y1 < y2 ? y1 : y2;
        double maxY = y1 > y2 ? y1 : y2;
        int set = production.getSet();
        for (Dot dot : dotList) {
            double y = dot.getFrontToBack(set);
            double x = dot.getSideToSide(set);
            if (minX < x && minY < y && maxX > x && maxY > y) {
                select(dot);
            } else {
                deselect(dot);
            }
        }
    }

    public int selectSize() {
        return selected.size();
    }
}
