/**
 *
 *
 *
 */
public final class DotCalculations {
    //measured in feet;
    public static final int SIDELINE_TO_HASH = 60;
    public static final int HASH_TO_HASH = 40;
    public static final int YARDLINE_SPACE = 15;
    public static final int ENDZONE_WIDTH = 30;
    /**
     * Constructor for DotCalculations made private to simulate a static class
     */
    private DotCalculations() {
    }
    public static double distance(Dot dot1, Dot dot2, int set) {
        //TODO: add exception checking for IndexOutOfBoundsException
        double ftb1 = dot1.getFrontToBack(set);
        double ftb2 = dot2.getFrontToBack(set);
        double sts1 = dot1.getSideToSide(set);
        double sts2 = dot2.getSideToSide(set);
        double ftb = ftb1 - ftb2;
        double sts = sts1 - sts2;
        return Math.sqrt(ftb * ftb + sts * sts);
    }
    public static double distance(double frontToBack, double sideToSide,
        Dot dot, int set) {
        //TODO: add exception checking for IndexOutOfBoundsException
        double ftb = frontToBack - dot.getFrontToBack(set);
        double sts = sideToSide - dot.getSideToSide(set);
        return Math.sqrt(ftb * ftb + sts * sts);
    }
    public static double convertFeetToSteps(double feet) {
        return feet / 15 * 8;
    }
    public static double convertStepsToFeet(double steps) {
        return steps / 8 * 15;
    }
}
