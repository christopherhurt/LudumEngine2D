package ludumEngine2D;

/**
 * Utility class that tracks the time passed since last frame. This is used for synchronizing time-dependent properties,
 * such as velocities.
 *
 * @author Chris Hurt
 * @version 10.01.19
 */
public final class Time {

    private static double mDelta = 0d;
    private static double mLastTime;

    static {
        mLastTime = currentTime();
    }

    /**
     * @return the amount of time in seconds since last frame
     */
    public static double getDelta() {
        return mDelta;
    }

    /**
     * Updates the amount of time passed since last frame.
     */
    static void update() {
        double currentTime = currentTime();
        mDelta = currentTime - mLastTime;
        mLastTime = currentTime;
    }

    /**
     * @return the current time in seconds
     */
    static double currentTime() {
        return System.nanoTime() / 1000000000d;
    }

    private Time() {
        // Static class, do not initialize
    }

}
