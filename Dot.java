import java.util.ArrayList;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
/**
 * This is a class for a dot, or a marching member.
 * It contains the information for the front to back and side to side
 * values for each set.
 * @author JT Herndon
 * @version 1.0
 */
public class Dot {
    private int set_length;
    private Circle circle;
    private ArrayList<dotEntry> dot_list;
    private class dotEntry {
        public double front_to_back;
        public double side_to_side;
        public dotEntry(double front_to_back, double side_to_side) {
            this.front_to_back = front_to_back;
            this.side_to_side = side_to_side;
        }
        public boolean equals(Object other) {
            if (other == null || !(other instanceof dotEntry)) return false;
            dotEntry that = (dotEntry) other;
            return this.front_to_back == that.front_to_back
                && this.side_to_side == that.side_to_side;
        }
    }
    public Dot() {
        dot_list = new ArrayList<>();
        set_length = -1;
        circle = new Circle(4);
    }
    public void addSet(double frontToBack, double sideToSide) {
        set_length++;
        dot_list.add(new dotEntry(frontToBack, sideToSide));
    }
    public void change(int set, double frontToBack, double sideToSide) {
        dot_list.set(set, new dotEntry(frontToBack, sideToSide));
    }
    public double getFrontToBack(int set) {
        return dot_list.get(set).front_to_back;
    }
    public double getSideToSide(int set) {
        return dot_list.get(set).side_to_side;
    }
    public int getSetLength() {
        return set_length;
    }
    public void move(int set) {
        circle.setCenterX(getSideToSide(set));
        circle.setCenterY(getFrontToBack(set));
    }
    public Circle getCircle() {
        return circle;
    }
    public boolean movesOnSet(int set) {
        if (set <= 0 || set >= dot_list.size()) return false;
        return !dot_list.get(set).equals(dot_list.get(set - 1));
    }
}
