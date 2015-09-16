import java.util.ArrayList;
import javafx.scene.shape.Circle;
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
    }
    public Dot() {
        dot_list = new ArrayList<>();
        set_length = -1;
    }
    public void addSet(double frontToBack, double sideToSide) {
        set_length++;
        dot_list.add(new dotEntry(frontToBack, sideToSide));
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
}
