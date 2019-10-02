package main;

/**
 * Utility class that tracks the time passed since last frame. This is used for synchronizing time-dependent properties,
 * such as velocities.
 *
 * @author Chris Hurt
 * @version 10.01.19
 */
public final class Time {

    private static float mDelta = 0.0f;
    private static float mLastTime;

    static {
        mLastTime = currentTimeFloat();
    }

    /**
     * @return the amount of time in seconds since last frame
     */
    public static float getDelta() {
        return mDelta;
    }

    /**
     * Updates the amount of time passed since last frame.
     */
    static void update() {
        float currentTime = currentTimeFloat();
        mDelta = currentTime - mLastTime;
        mLastTime = currentTime;
    }

    /**
     * @return the current system time in seconds
     */
    private static float currentTimeFloat() {
        return System.currentTimeMillis() / 1000.0f;
    }

}
