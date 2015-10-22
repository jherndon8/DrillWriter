import java.util.ArrayList;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
    /**
     * A private class that contains a single pair of coordinates for a dot
     * @author JT Herndon
     * @version 1.0
     */
    private class dotEntry {
        public double front_to_back;
        public double side_to_side;
        /**
         * Constructor for a dotEntry
         * @param front_to_back the front-to-back coordinate on the main pane
         * @param side_to_side the side-to-side coordinate on the main pane
         */
        public dotEntry(double front_to_back, double side_to_side) {
            this.front_to_back = front_to_back;
            this.side_to_side = side_to_side;
        }
        @Override
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
        circle.setFill(Color.BLUE);
    }
    /**
     * Adds a set with new coordinates
     * @param frontToBack the new front-to-back coordinate
     * @param sideToSide the new side-to-side coordinate
     */
    public void addSet(double frontToBack, double sideToSide) {
        set_length++;
        dot_list.add(new dotEntry(frontToBack, sideToSide));
    }
    /**
     * Changes the coordinates of the given set number to the new pair of input
     * coordinates
     * @param set the set to be changed
     * @param frontToBack the new front-to-back
     * @param sideToSide the new side-to-side
     */
    public void change(int set, double frontToBack, double sideToSide) {
        dot_list.set(set, new dotEntry(frontToBack, sideToSide));
    }
    /**
     * Gives the front to back of the dot for a given set
     * @return front to back of the dot at the input set number
     */
    public double getFrontToBack(int set) {
        return dot_list.get(set).front_to_back;
    }
    /**
     * Gives the side to side of the dot for a given set
     * @return side-to-side of the dot at the input set number
     */
    public double getSideToSide(int set) {
        return dot_list.get(set).side_to_side;
    }
    /**
     * Gives the number of sets this dot contains
     * @return the nuumber of sets this dot contains
     */
    public int getSetLength() {
        return set_length;
    }
    /**
     * Moves the circle object to the coordinates of the given set
     * @param set the set for the dot to move to
     */
    public void move(int set) {
        circle.setCenterX(getSideToSide(set));
        circle.setCenterY(getFrontToBack(set));
    }
    /**
     * Gives the javafx.scene.shape.Circle object associated with this dot
     * @return the Circle associated with this dot
     */
    public Circle getCircle() {
        return circle;
    }
    /**
     * Returns true if the dot has different coordinates between the given set
     * and the set before it
     * @param set
     * @return true if dot moves between set - 1 and set
     */
    public boolean movesOnSet(int set) {
        if (set <= 0 || set >= dot_list.size()) return false;
        return !dot_list.get(set).equals(dot_list.get(set - 1));
    }
    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i <= set_length; i++) {
            ret = ret + DotCalculations.sideToSide(getSideToSide(i)) + "\t"
                + DotCalculations.frontToBack(getFrontToBack(i)) + "\n";
        }
        return ret;
    }
}
