/**
 * This is a static class for making dot calculations
 * @author JT Herndon
 * @version 1.0
 */
public final class DotCalculations {
    public static final int PIXELS_PER_FEET = 3;
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
    public static double pixelsToSteps(double pixels) {
        return feetToSteps(pixels / PIXELS_PER_FEET);
    }
    public static double stepsToPixels(double steps) {
        return stepsToFeet(steps) * PIXELS_PER_FEET;
    }
    public static int yardline(double x) {
        int ret = (int) ((x - 45 * 3 / 2) / 45);
        if (ret < 0) {
            return 0;
        } else if (ret > 20) {
            ret = 20;
        }
        return ret * 5;
    }

    public static double steps(double x) {
        double ret = x - (90 + 9 * yardline(x));
        return pixelsToSteps(ret);
    }
    /**
     * This method takes an x-coordinate on the screen and converts it to a
     * coordinate on a dot sheet
     * @param x the x-coordinate
     * @return the description of this x-coordinate using five-yard lines
     */
    public static String sideToSide(double x) {
        int yardline = yardline(x);
        double steps = steps(x);;
        int side;
        /*yardline = (int) ((x - 45 * 3 / 2) / 45);
        if (yardline < 0) {
            yardline = 0;
        } else if (yardline > 20) {
            yardline = 20;
        }
        steps = x - (90 +  45 * yardline);
        yardline *= 5;
        steps = pixelsToSteps(steps);
        */
        if (yardline > 50) {
            yardline = 100 - yardline;
            side = 2;
        } else if (yardline == 50) {
            if (steps > 0) {
                side = 2;
            } else if (steps < 0) {
                side = 1;
            } else {
                return ("On 50 yard line\t\t\t");
            }
        } else {
            side = 1;
        }
        String yardside;
        if (side == 1 && steps < 0 || side == 2 && steps > 0) {
            yardside = String.format("%1.2f outside ", Math.abs(steps));
        } else if (side == 1 && steps > 0 || side == 2 && steps < 0) {
            yardside = String.format("%1.2f inside ", Math.abs(steps));
        } else {
            yardside = "On ";
        }
        String ret;
        if (yardline == 0) {
            ret = yardside + "side " + side + " Goal line\t";
        } else {
            ret = yardside + "side " + side + " " + yardline + " yard line ";
        }
        if (yardside.equals("On ")) {
            ret = ret + "\t\t";
        }
        return ret;
    }

    public static String frontToBack(double y) {
        String hash;
        double totalFeet = stepsToFeet(pixelsToSteps(y));
        double feet;
        if (totalFeet < SIDELINE_TO_HASH / 2) {
            hash = " back sideline";
            feet = totalFeet;
        } else if (totalFeet < SIDELINE_TO_HASH + HASH_TO_HASH / 2) {
            hash = " back hash";
            feet = totalFeet - SIDELINE_TO_HASH;
        } else if (totalFeet < HASH_TO_HASH + SIDELINE_TO_HASH * 3 / 2) {
            hash = " front hash";
            feet = totalFeet - SIDELINE_TO_HASH - HASH_TO_HASH;
        } else {
            hash = " front sideline";
            feet = totalFeet - 2 * SIDELINE_TO_HASH - HASH_TO_HASH;
        }
        String ret;
        if (feet < 0) {
            ret = String.format("%2.2f", -feetToSteps(feet)) + " behind";
        } else if (feet > 0) {
            ret = String.format("%2.2f", feetToSteps(feet)) + " in front of";
        } else {
            ret = "On";
        }
        return ret + hash;
    }

    public static double x(int yardline, double steps) {
        return 90 + 9*yardline + stepsToPixels(steps);
    }
    /*public static double y(int hashline, double steps) {
    }*/
    public static double snapX(double x) {
        int yl = yardline(x);
        double steps = steps(x);
        steps = steps > 0 ? steps + 0.5 : steps - 0.5;
        int next = (int) steps;
        return x(yl, next);
    }
    public static double snapY(double y) {
        double ret = (int) ((y + 3) / 6);
        ret *= 6;
        if (ret < 0) {
            ret = 0;
        } else if (ret > PIXELS_PER_FEET * (HASH_TO_HASH + 2
            * SIDELINE_TO_HASH)) {
            ret = HASH_TO_HASH + 2 * SIDELINE_TO_HASH;
        }
        return ret;
    }
}
