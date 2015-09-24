/**
 * This is a static class for making dot calculations
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
    /**
     * Gets the distance between two dots at a given set
     * @param dot1 the first dot
     * @param dot2 the second dot
     * @param set the set to calculate the distance between the two dots
     * @return the distance between the dots at the input set
     */
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
    /**
     * Returns the distance between a pair of coordinates and a dot at a set
     * @param frontToBack the front-to-back coordinate
     * @param sideToSide the side-to-side coordinate
     * @param dot the dot
     * @param set the set number
     * @return the distance between the coordinates and the dot at the given set
     */
    public static double distance(double frontToBack, double sideToSide,
        Dot dot, int set) {
        //TODO: add exception checking for IndexOutOfBoundsException
        double ftb = frontToBack - dot.getFrontToBack(set);
        double sts = sideToSide - dot.getSideToSide(set);
        return Math.sqrt(ftb * ftb + sts * sts);
    }
    /**
     * Converts feet to 8-to-5 steps
     * @param feet a distance in feet
     * @return the distance in 8-to-5 steps
     */
    public static double feetToSteps(double feet) {
        return feet / 15 * 8;
    }
    /**
     * Converts 8-to-5 steps to feet
     * @param steps a number of 8-to-5 steps
     * @return the distance in feet
     */
    public static double stepsToFeet(double steps) {
        return steps / 8 * 15;
    }
}
