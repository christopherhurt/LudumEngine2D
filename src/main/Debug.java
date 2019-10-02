package main;

/**
 * Utility class that provides debug tools and debug configurations for developers.
 *
 * @author Chris Hurt
 * @version 10.01.19
 */
public final class Debug {

    private static boolean sEnabled = false;

    /**
     * @return whether the debug tools are enabled
     */
    public static boolean isEnabled() {
        return sEnabled;
    }

    /**
     * Sets whether the debug tools are enabled.
     *
     * @param pEnabled whether the debug tools are to be enabled
     */
    public static void setEnabled(boolean pEnabled) {
        sEnabled = pEnabled;
    }

    /**
     * Logs a message to the console.
     *
     * @param pMessage the message to be logged
     */
    public static void log(String pMessage) {
        if (sEnabled) {
            System.out.println("DEBUG: " + pMessage);
        }
    }

    /**
     * Logs a warning message to the console.
     *
     * @param pMessage the warning message to be logged
     */
    public static void warn(String pMessage) {
        if (sEnabled) {
            System.out.println("WARNING: " + pMessage);
        }
    }

    /**
     * Logs an error message to the console.
     *
     * @param pMessage the error message to be logged
     */
    public static void error(String pMessage) {
        if (sEnabled) {
            System.err.println("ERROR: " + pMessage);
        }
    }

    private Debug() {
        // Static class, do not initialize
    }

}
