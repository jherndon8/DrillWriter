import java.util.ArrayList;
/**
 * This is a class that contains all the dot information and count structures
 * for a visual production.
 * @author JT Herndon
 * @version 1.0
 */
public class Production {
    private ArrayList<Dot> dotList;
    private ArrayList<Integer> countStructure;
    public Production() {
        dotList = new ArrayList<>();
        countStructure = new ArrayList<>();
    }
    public void addDot() {
        dotList.add(new Dot());
    }
    public Dot selectClosestDot(int set, double frontToBack, double sideToSide)
    {
        if (dotList.size() == 0) {
            return null;
        }
        Dot closestDot = null;
        double distance;
        for (Dot dot: dotList) {
            if (closestDot == null) {
                closestDot = dot;
                distance = DotCalculations.distance(frontToBack, sideToSide,
                    dot, set);
            } else {
                double tmp = DotCalculations.distance(frontToBack, sideToSide,
                    dot, set);
                if (tmp < distance) {
                    distance = tmp;
                    closestDot = dot;
                }
            }
        }
        return closestDot;
    }
}
