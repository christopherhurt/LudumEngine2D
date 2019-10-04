package main;

/**
 * Utility class that tracks the time passed since last frame. This is used for synchronizing time-dependent properties,
 * such as velocities.
 *
 * @author Chris Hurt
 * @version 10.01.19
 */
public final class Time {

    private static double mDelta = 0.0f;
    private static double mLastTime;

    static {
        mLastTime = currentTimeFloat();
    }

    /**
     * @return the amount of time in seconds since last frame
     */
    public static float getDelta() {
        return (float)mDelta;
    }

    /**
     * Updates the amount of time passed since last frame.
     */
    static void update() {
        double currentTime = currentTimeFloat();
        mDelta = currentTime - mLastTime;
        mLastTime = currentTime;
    }

    /**
     * @return the current system time in seconds
     */
    private static double currentTimeFloat() {
        return System.currentTimeMillis() / 1000d;
    }

    /**
     * @return the precise current time in seconds
     */
    static double currentTimePrecise() {
        return System.nanoTime() / 1000000000d;
    }

    private Time() {
        // Static class, do not initialize
    }

}
