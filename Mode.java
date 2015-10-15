/**
 * An enum for the mode of the production
 */
public enum Mode {
    /**
     * Used for selecting multiple dots
     */
    SELECT,
    /**
     * Used for selecting multiple dots by drawing a box
     */
    BOXSELECT,
    /**
     * Used for moving a single dot
     */
    MOVEDOT,
    /**
     * Used when adding dots in opening set
     */
    ADDDOT,
    /**
     * Used when animating sets
     */
    PLAY
}
